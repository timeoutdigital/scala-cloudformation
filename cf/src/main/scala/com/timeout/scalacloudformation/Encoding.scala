package com.timeout.scalacloudformation

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import com.timeout.scalacloudformation.AWSResources.{CfExp, Resource}
import com.timeout.scalacloudformation.CfExp._
import io.circe.syntax._
import io.circe.{Encoder, Json, JsonObject, ObjectEncoder}
import shapeless.labelled.FieldType
import shapeless._

object Encoding {
  implicit val encodeZonedDateTime: Encoder[ZonedDateTime] =
    implicitly[Encoder[String]].contramap[ZonedDateTime](_.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))

  implicit def encodeLit[T: Encoder]: Encoder[Lit[T]] =
    implicitly[Encoder[T]].contramap[Lit[T]](_.value)

  implicit def encodeCfExp[T](
    implicit
    ev: Encoder[Lit[T]]
  ): Encoder[CfExp[T]] = Encoder.instance[CfExp[T]] {
    case l: Lit[T] =>
      l.asJson
    case FnBase64(exp) =>
      Json.obj("Fn::Base64" -> exp.asJson)
    case FnAnd(cond1, cond2) =>
      Json.obj("Fn::And" -> List(cond1, cond2).asJson)
    case eq: FnEquals[T @unchecked] =>
      Json.obj("Fn::Equals" -> Json.fromValues(List(eq.left.asJson, eq.right.asJson)))
    case FnIf(cond, ifTrue, ifFalse) =>
      Json.obj("Fn::If" -> Json.fromValues(List(cond.asJson, ifTrue.asJson, ifFalse.asJson)))
    case FnNot(cond) =>
      Json.obj("Fn::Not" -> List(cond).asJson) // weird but correct according to the doc
    case FnOr(conds@_*) =>
      Json.obj("Fn::Or" -> conds.asJson)
    case x =>
      throw new Exception(s"Unexpected expression $x")
  }

  implicit val encodeHNil: ObjectEncoder[HNil] =
    ObjectEncoder.instance[HNil](_ => JsonObject.empty)

  implicit def encodeHList[K <: Symbol, H, T <: HList](
    implicit
    encodeHead: Lazy[Encoder[H]],
    encodeTail: ObjectEncoder[T],
    witness: Witness.Aux[K]
  ): ObjectEncoder[FieldType[K, H] :: T] = ObjectEncoder.instance[FieldType[K, H] :: T] {
    case h :: t =>
      val obj = encodeTail.encodeObject(t).+:(witness.value.name -> encodeHead.value(h))
      obj.filterKeys(_ != "logicalId")
  }

  implicit def encodeResource[T <: Resource, LabelledGeneric, R <: HList](
    implicit
    gen: LabelledGeneric.Aux[T, R],
    encode: ObjectEncoder[R]
  ): Encoder[T] = Encoder.instance[T] { r =>
    Json.obj(
      r.logicalId -> Json.obj(
        "Type" -> Json.fromString(r.fqn),
        "Properties" -> encode(gen.to(r)).asJson
      )
    )
  }
}
