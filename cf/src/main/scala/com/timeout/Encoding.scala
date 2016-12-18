package com.timeout

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import com.timeout.scalacloudformation.AWSResources.Resource
import io.circe.{Encoder, Json, JsonObject, ObjectEncoder}
import io.circe.syntax._
import com.timeout.scalacloudformation.CfExp._
import com.timeout.scalacloudformation.CfExp
import io.circe.Json.JString
import shapeless.{HList, HNil, LabelledGeneric}
import shapeless.LabelledGeneric.Aux
import shapeless._
import shapeless.labelled.FieldType

object Encoding {
  implicit val encodeZonedDateTime: Encoder[ZonedDateTime] =
    implicitly[Encoder[String]].contramap[ZonedDateTime](_.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))

  implicit def encodeLit[T: Encoder]: Encoder[Lit[T]] =
    implicitly[Encoder[T]].contramap[Lit[T]](_.value)

  implicit def encodeCfExp[T](implicit ev: Encoder[Lit[T]]): Encoder[CfExp[T]] = Encoder.instance[CfExp[T]] {
    case l: Lit[T] => l.asJson
    case FnBase64(exp) => Json.obj("Fn::Base64" -> exp.asJson)
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

    Map(
      r.logicalId -> Map(
        "Type" -> Json.fromString(r.fqn),
        "Properties" -> encode(gen.to(r)).asJson
      )
    ).asJson
  }
}
