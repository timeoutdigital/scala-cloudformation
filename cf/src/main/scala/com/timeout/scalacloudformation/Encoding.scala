package com.timeout.scalacloudformation

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import com.timeout.scalacloudformation.CfExp._
import com.timeout.scalacloudformation.Parameter.CommaDelimited
import io.circe.syntax._
import io.circe.generic.semiauto.deriveEncoder
import io.circe.{Encoder, Json}
import enum.Enum

import java.time.Duration

object Encoding {
  implicit final val encodeDuration: Encoder[Duration] =
    Encoder.instance(duration => Json.fromString(duration.toString))

  implicit val encodeZonedDateTime: Encoder[ZonedDateTime] =
    implicitly[Encoder[String]].contramap[ZonedDateTime](_.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))

  implicit val encodeTagExp: Encoder[Tag] =
    Encoder.instance[Tag] { t =>
      Json.obj(
        "Key" -> Json.fromString(t.key),
        "Value" -> Json.fromString(t.value)
      )
    }

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
    case ResourceRef(resource) =>
      Json.obj("Ref" -> Json.fromString(resource.logicalId))
    case PseudoParameterRef(param) =>
      Json.obj("Ref" -> Json.fromString(param.toString))
    case x =>
      throw new Exception(s"Unexpected expression $x")
  }

  implicit def encodeEnum[T: Enum]
    : Encoder[T] = Encoder.instance[T] { t =>
    Json.fromString(implicitly[Enum[T]].encode(t))
  }

  implicit val encodeParamDataType: Encoder[Parameter.DataType] = {
    import Parameter.DataType._
    val awsEnum = implicitly[Enum[Parameter.AwsType]]

    Encoder.instance[Parameter.DataType] { dt =>
      val s = dt match {
        case String => "String"
        case Number => "Number"
        case `List<Number>` => "List<Number>"
        case CommaDelimitedList => "CommaDelimitedList"
        case t: AwsListType => s"List<${awsEnum.encode(t.tpe)}>"
        case t: AwsType => awsEnum.encode(t.tpe)
      }
      Json.fromString(s)
    }
  }

  implicit val encodeParam: Encoder[Parameter] =
    Encoder.instance[Parameter] { p =>
      val common = Json.obj(
        "Type" -> p.Type.asJson,
        "Description" -> p.Description.asJson,
        "NoEcho" -> p.NoEcho.asJson
      )

      val specific = p match {
        case sp: Parameter.Str =>
          Json.obj(
            "MaxLength" -> sp.MaxLength.asJson,
            "MinLength" -> sp.MinLength.asJson,
            "AllowedPattern" -> sp.AllowedPattern.asJson,
            "AllowedValues" -> sp.AllowedValues.asJson,
            "Default" -> sp.Default.asJson
          )
        case np: Parameter.Number =>
          Json.obj(
            "MaxValue" -> np.MaxValue.asJson,
            "MinValue" -> np.MinValue.asJson,
            "Default" -> np.Default.asJson
          )
        case p: CommaDelimited =>
          Json.obj(
            "AllowedValues" -> p.AllowedValues.map(_.mkString(",")).asJson,
            "Default" -> p.Default.map(_.mkString(",")).asJson
          )
        case _ => Json.obj()
      }

      Json.obj(p.logicalId -> common.deepMerge(specific))
    }

  implicit val autoscalingPolicy: Encoder[AutoscalingCreationPolicy] = deriveEncoder[AutoscalingCreationPolicy]
  implicit val resourceSignal: Encoder[ResourceSignal] = deriveEncoder[ResourceSignal]
  implicit val creationPolicy: Encoder[CreationPolicy] = deriveEncoder[CreationPolicy]
}
