package com.timeout

import cats.Applicative
import io.circe.Decoder.Result
import io.circe._
import cats.syntax.either._
import cats.instances.either._
import cats.syntax.traverse._
import cats.syntax.either._
import cats.syntax.option._
import cats.instances.list._

import scala.io.Source
import scala.language.implicitConversions

object SpecsParser extends App {
  implicit val decodePrimitiveType = new Decoder[PrimitiveType] {
    override def apply(c: HCursor): Result[PrimitiveType] =
      c.as[String].flatMap { s =>
        Either.fromOption(PrimitiveType.fromString(s), DecodingFailure(s"Unknown primitive type $s", c.history))
      }
  }

  def decodeProperty(name: String): Decoder[Property] = new Decoder[Property] {
    override def apply(c: HCursor): Result[Property] = {
      for {
        primitiveType <- c.get[Option[PrimitiveType]]("PrimitiveType").recover { case _ => None }
        required <- c.get[Boolean]("Required")
      } yield Property(name, primitiveType, required = required)
    }
  }

  implicit val decodeProperties = new Decoder[List[Property]] {
    override def apply(c: HCursor): Result[List[Property]] =
      c.focus.asObject.getOrElse(throw new Exception).toMap.toList.traverse[Result, Property] { case (name, json) =>
        decodeProperty(name).decodeJson(json)
      }
  }

  def decodeResourceType(name: String) = new Decoder[ResourceType] {
    override def apply(c: HCursor): Result[ResourceType] =
      c.get[List[Property]]("Properties").map(ResourceType(name, _))
  }

  implicit val decodeResourceTypes = new Decoder[List[ResourceType]] {
    override def apply(c: HCursor): Result[List[ResourceType]] =
      c.downField("ResourceTypes").focus.flatMap(_.asObject).getOrElse(throw new Exception).toMap.toList.traverse[Result, ResourceType] { case (name, json) =>
        decodeResourceType(name).decodeJson(json)
      }
  }

  val stream = getClass.getResourceAsStream("/cf-specs.json")
  val string = Source.fromInputStream(stream).mkString
  println(parser.decode[List[ResourceType]](string))
}
