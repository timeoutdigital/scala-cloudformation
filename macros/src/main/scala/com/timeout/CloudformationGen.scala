package com.timeout

import scala.meta.Defn
import scala.meta.Term
import scala.meta._

object CloudformationGen {
  def cfToScalaType(primitiveType: PrimitiveType): Type.Name = primitiveType match {
    case PrimitiveType.String => Type.Name("String")
    case PrimitiveType.Long => Type.Name("Long")
    case PrimitiveType.Integer => Type.Name("Int")
    case PrimitiveType.Double => Type.Name("Double")
    case PrimitiveType.Boolean => Type.Name("Boolean")
    case PrimitiveType.Timestamp => Type.Name("java.time.ZonedDateTime")
    case PrimitiveType.Json => Type.Name("io.circe.Json")
  }

  def normalize(str: String): String =
    str.filter(_.isLetterOrDigit)

  val propertyStats: List[Defn.Object] =
    SpecsParser.propertyTypes.toList.map { case (namespace, props) =>
      val objectName = Term.Name(normalize(namespace))

      val classDefs = props.map { prop =>
        val className = Type.Name(prop.name)

        val properties = prop.properties.map(mkField(_, None)).collect { case Some(p) => p }

        q"case class $className (..$properties)"
      }

      q"""object $objectName {
            ..$classDefs
         }
       """
    }

  val resourceStats: List[Defn.Class] =
    SpecsParser.resourceTypes.map { rt =>
      val className = Type.Name(normalize(rt.fqn))

      val namespace = normalize(rt.fqn.takeWhile(_ != "."))
      val properties = rt.properties.map(mkField(_, Some(namespace))).collect { case Some(p) => p }
      val fqn = Term.Name("\"" + rt.fqn + "\"")

      q"""case class $className (..$properties) extends Resource {
            override def fqn: String = $fqn
         }
       """
    }

  // TODO this returns an option until list and map types are implemented
  private def mkField(property: Property, namespace: Option[Namespace]): Option[Term.Param] = {
    property.awsType match {
      case PropertyTypeRef(_, "List") | PropertyTypeRef(_, "Map") => None
      case _ =>
        val paramName = Term.Name(property.name)

        val typeName = property.awsType match {
          case p: PrimitiveType => cfToScalaType(p)
          case TagType => Type.Name("Tag")
          case PropertyTypeRef(_, name) =>
            val fqn = namespace.fold(name) { ns => s"$ns.$name" }
            Type.Name(fqn)
        }

        val param = if (property.required) {
          param"$paramName: $typeName"
        } else {
          param"$paramName: Option[$typeName]"
        }

        Some(param)
    }

  }
}
import CloudformationGen._

class CloudformationGen extends scala.annotation.StaticAnnotation {

  inline def apply(defn: Any): Any = meta {
    defn match {
      case q"object $_ {..$_}" =>
        val imports = List(
          q"import java.time.ZonedDateTime",
          q"import io.circe.Json"
        )

        val resourceTrait = List(q"""
          trait Resource {
            def fqn: String
          }
        """)

        q"..${imports ++ resourceTrait ++ propertyStats ++ resourceStats}"
      case _ =>
        abort("@cf-resources must be used on an object")
    }
  }
}
