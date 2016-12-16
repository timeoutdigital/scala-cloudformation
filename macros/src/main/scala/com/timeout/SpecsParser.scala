package com.timeout

import io.circe.Decoder.Result
import io.circe._
import cats.instances.either._
import cats.syntax.traverse._
import cats.syntax.either._
import cats.instances.list._

import scala.io.Source
import scala.language.implicitConversions

object SpecsParser {
  implicit private val decodePrimitiveType = new Decoder[PrimitiveType] {
    override def apply(c: HCursor): Result[PrimitiveType] =
      c.as[String].flatMap { s =>
        Either.fromOption(PrimitiveType.fromString(s), DecodingFailure(s"Unknown primitive type $s", c.history))
      }
  }

  private def decodeListType(namespace: String): Decoder[ListType] = Decoder.instance[ListType] { c =>
    for {
      _ <- c.get[String]("Type").ensure(DecodingFailure(s"Not a List", c.history))(_ == "List")
      itemType <- c.get[PrimitiveType]("PrimitiveItemType")(decodePrimitiveType).map(identity[AwsType]) orElse c.as[AwsType](decodeAwsType(namespace, "ItemType"))
    } yield ListType(itemType)
  }

  private def decodeMapType(namespace: String): Decoder[MapType] = Decoder.instance[MapType] { c =>
    for {
      _ <- c.get[String]("Type").ensure(DecodingFailure(s"Not a Map", c.history))(_ == "Map")
      itemType <- c.get[PrimitiveType]("PrimitiveItemType")(decodePrimitiveType).map(identity[AwsType]) orElse c.as[AwsType](decodeAwsType(namespace, "ItemType"))
    } yield MapType(itemType)
  }

  private def decodePropertyTypeRef(namespace: Namespace): Decoder[PropertyTypeRef] = Decoder.instance[PropertyTypeRef] { c =>
      c.as[String]
        .ensure(DecodingFailure(s"Should not be a List, a Map or a Tag: ${c.as[String]}", c.history)) { s =>
          !Set("List", "Map").contains(s)
        }.map(PropertyTypeRef(namespace, _))
  }

  private def decodePrimitiveTypeProperty(name: String): Decoder[Property] = new Decoder[Property] {
    override def apply(c: HCursor): Result[Property] = {
      for {
        primitiveType <- c.get[PrimitiveType]("PrimitiveType")
        required <- c.get[Boolean]("Required")
      } yield Property(name, primitiveType, required = required)
    }
  }

  private implicit val decodeTag = Decoder.instance[TagType.type] { c =>
    c.as[String].ensure(DecodingFailure("Expected the string Tag", c.history))(_ == "Tag").map(_ => TagType)
  }

  private def decodeAwsType(namespace: Namespace, key: String): Decoder[AwsType] = Decoder.instance[AwsType] { c =>
    c.get[TagType.type](key)(decodeTag).map(identity[AwsType]) orElse
      c.get[PropertyTypeRef](key)(decodePropertyTypeRef(namespace)).map(identity[AwsType]) orElse
    c.as[AwsType](decodeListType(namespace).map(identity[AwsType])) orElse
      c.as[AwsType](decodeMapType(namespace).map(identity[AwsType]))
  }

  private def decodeAwsProperty(namespace: Namespace, name: String): Decoder[Property] = Decoder.instance[Property] { c =>
    for {
      awsType <- c.as[AwsType](decodeAwsType(namespace, "Type"))
      required <- c.get[Boolean]("Required")
    } yield Property(name, awsType, required = required)
  }

  private def decodeProperties(namespace: String) = new Decoder[List[Property]] {
    override def apply(c: HCursor): Result[List[Property]] =
      c.focus.asObject.getOrElse(throw new Exception(s"Expected property to be an object at ${c.history}"))
        .toMap.toList.traverse[Result, Property] { case (name, json) =>
          val decoder = decodePrimitiveTypeProperty(name) or decodeAwsProperty(namespace, name)
          decoder.decodeJson(json)
      }
  }

  private def decodeResourceType(name: String) = new Decoder[ResourceType] {
    override def apply(c: HCursor): Result[ResourceType] =
      c.get[List[Property]]("Properties")(decodeProperties(name)).map(ResourceType(name, _))
  }

  private def decodePropertyType(fqn: String) = new Decoder[Option[PropertyType]] {
    override def apply(c: HCursor): Result[Option[PropertyType]] =
      fqn.split("\\.").toList match {
        case List(namespace, name) =>
          c.get[List[Property]]("Properties")(decodeProperties(namespace)).map { props =>
            Some(PropertyType(namespace, name, props))
          }
        case List("Tag") =>
          Right(None)
      }
  }

  implicit private val decodeResourceTypes = new Decoder[List[ResourceType]] {
    override def apply(c: HCursor): Result[List[ResourceType]] =
      c.downField("ResourceTypes").focus
        .flatMap(_.asObject).getOrElse(throw new Exception(s"Expected resource to be an object at ${c.history}"))
        .toMap.toList.traverse[Result, ResourceType] { case (name, json) =>
          decodeResourceType(name).decodeJson(json)
      }
  }

  implicit private val decodePropertyTypes = new Decoder[List[PropertyType]] {
    override def apply(c: HCursor): Result[List[PropertyType]] =
      c.downField("PropertyTypes").focus
        .flatMap(_.asObject).getOrElse(throw new Exception(s"Expected property to be an object at ${c.history}"))
        .toMap.toList.traverse[Result, Option[PropertyType]] { case (fqn, json) =>
          decodePropertyType(fqn).decodeJson(json)
        }.map(_.flatten)
  }

  private val stream = getClass.getResourceAsStream("/cf-specs.json")

  private val string = Source.fromInputStream(stream).mkString

  val resourceTypes: List[ResourceType] = parser.decode[List[ResourceType]](string).valueOr(err =>
    throw new Exception(s"Failed while parsing the cloud formation specs: $err"))

  val propertyTypes: Map[Namespace, List[PropertyType]] =
    parser.decode[List[PropertyType]](string)
      .valueOr(err => throw new Exception(s"Failed while parsing the cloud formation specs: $err"))
      .groupBy(_.namespace)
}
