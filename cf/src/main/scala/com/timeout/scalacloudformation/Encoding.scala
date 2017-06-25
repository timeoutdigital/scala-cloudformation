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
import ResourceAttributes._

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
    case FnJoin(delimiter, chunks) =>
      Json.obj("Fn::Join" -> Json.arr(delimiter.asJson, chunks.asJson))
    case FnOr(conds@_*) =>
      Json.obj("Fn::Or" -> conds.asJson)
    case FnFindInMap(m, key1, key2) =>
      Json.obj("Fn::FindInMap" -> Json.arr(
        m.logicalId.asJson,
        key1.asJson,
        key2.asJson
      ))
    case FnGetAttr(logicalId, attr) =>
      Json.obj("Fn::GetAtt" -> Json.arr(
        Json.fromString(logicalId),
        Json.fromString(attr)))
    case ResourceRef(resource) =>
      Json.obj("Ref" -> Json.fromString(resource.logicalId))
    case ParameterRef(resource) =>
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
        case t: AwsTypeList => s"List<${awsEnum.encode(t.tpe)}>"
        case t: AwsType => awsEnum.encode(t.tpe)
      }
      Json.fromString(s)
    }
  }

  implicit val autoscalingPolicy: Encoder[AutoscalingCreationPolicy] =
    deriveEncoder[AutoscalingCreationPolicy]

  implicit val resourceSignal: Encoder[ResourceSignal] =
    deriveEncoder[ResourceSignal]

  implicit val creationPolicy: Encoder[CreationPolicy] =
    deriveEncoder[CreationPolicy]

  implicit val autoScalingReplacingUpdate: Encoder[AutoScalingReplacingUpdate] =
    deriveEncoder[AutoScalingReplacingUpdate]

  implicit val autoScalingRollingUpdate: Encoder[AutoScalingRollingUpdate] =
    deriveEncoder[AutoScalingRollingUpdate]

  implicit val autoScalingScheduledAction: Encoder[AutoScalingScheduledAction] =
    deriveEncoder[AutoScalingScheduledAction]

  implicit val updatePolicy: Encoder[UpdatePolicy] =
    deriveEncoder[UpdatePolicy]

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
            "ConstraintDescription" -> sp.ConstraintDescription.asJson,
            "AllowedValues" -> sp.AllowedValues.asJson,
            "Default" -> sp.Default.asJson
          )
        case np: Parameter.Integer =>
          Json.obj(
            "MaxValue" -> np.MaxValue.asJson,
            "MinValue" -> np.MinValue.asJson,
            "Default" -> np.Default.asJson
          )
        case np: Parameter.Double =>
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

  implicit val condition: Encoder[Condition] = deriveEncoder[Condition]

  implicit val output: Encoder[Output] = Encoder.instance { o =>
    Json.obj(o.logicalId -> Json.obj(
      "Description" -> o.Description.asJson,
      "Value" -> o.Value.asJson,
      "Condition" -> o.Condition.map(_.logicalId).asJson,
      "Export" -> Json.obj(
        "Name" -> o.Export.asJson
      )
    ))
  }

  implicit val mapping: Encoder[Template.Mapping] = Encoder.instance { o =>
    Json.obj(o.logicalId -> o.value.asJson)
  }

  private def fold[A: Encoder](objects: List[A]): Json =
    objects.foldLeft(Json.obj()) { case (o, item) =>
      o.deepMerge(item.asJson)
    }

  implicit val template: Encoder[Template] = Encoder.instance { t =>
    Json.obj(
      "AWSTemplateFormatVersion" -> Json.fromString("2010-09-09"),
      "Description" -> t.Description.asJson,
      "Metadata" -> t.Metadata.asJson,
      "Parameters" -> fold(t.Parameters),
      "Mappings" -> fold(t.Mappings),
      "Conditions" -> fold(t.Conditions),
      "Resources" -> fold(t.Resources.map(_.jsonEncode)),
      "Outputs" -> fold(t.Outputs)
    )
  }
}
