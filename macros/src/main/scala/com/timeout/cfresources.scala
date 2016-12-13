package com.timeout

import scala.collection.immutable
import scala.meta._

object cfresources {
  def cfToScalaType(primitiveType: PrimitiveType): Type.Name = primitiveType match {
    case PrimitiveType.String => Type.Name("String")
    case PrimitiveType.Long => Type.Name("Long")
    case PrimitiveType.Integer => Type.Name("Int")
    case PrimitiveType.Double => Type.Name("Double")
    case PrimitiveType.Boolean => Type.Name("Boolean")
    case PrimitiveType.Timestamp => Type.Name("java.time.ZonedDateTime")
    case PrimitiveType.Json => Type.Name("io.circe.Json")
  }
}
import cfresources._

class cfresources extends scala.annotation.StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    defn match {
      case q"object $name {..$stats}" =>
        val imports = immutable.Seq(
          q"import java.time.ZonedDateTime",
          q"import io.circe.Json"
        )
        val stats = SpecsParser.resourceTypes.map { rt =>
          val className = Type.Name(s"`${rt.fqn}`")

          val properties = rt.properties.collect { case Property(name, Some(primitiveType), required) =>
            val paramName = Term.Name(name)
            val typeName = cfToScalaType(primitiveType)
            if (required) {
              param"$paramName: $typeName"
            } else {
              param"$paramName: Option[$typeName]"
            }
          }

          q"case class $className (..$properties)"
        }
        q"..${imports ++ stats}"
      case _ =>
        abort("@cf-resources must be used on an object")
    }
  }
}
