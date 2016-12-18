package com.timeout

import scala.meta.Defn
import scala.meta.Term
import scala.meta._

object CloudformationGen {
  def mapPrimitiveType(primitiveType: PrimitiveType): Type.Name = primitiveType match {
    case PrimitiveType.String => Type.Name("String")
    case PrimitiveType.Long => Type.Name("Long")
    case PrimitiveType.Integer => Type.Name("Int")
    case PrimitiveType.Double => Type.Name("Double")
    case PrimitiveType.Boolean => Type.Name("Boolean")
    case PrimitiveType.Timestamp => Type.Name("java.time.ZonedDateTime")
    case PrimitiveType.Json => Type.Name("io.circe.Json")
  }

  def mapAwsType(namespace: Option[Namespace])(awsType: AwsType): Type = awsType match {
    case p: PrimitiveType =>
      mapPrimitiveType(p)
    case TagType =>
      Type.Name("Tag")
    case PropertyTypeRef(_, name) =>
      val fqn = namespace.fold(name) { ns: String => s"$ns.$name" }
      Type.Name(fqn)
    case ListType(itemType) =>
      val typeName = mapAwsType(namespace)(itemType)
      t"List[$typeName]"
    case MapType(itemType) =>
      val typeName = mapAwsType(namespace)(itemType)
      t"Map[String, $typeName]"
  }

  def normalize(str: String): String =
    str.filter(_.isLetterOrDigit)

  // Contains custom properties and generic derivations
  val objectStats: List[Defn.Object] =
    SpecsParser.propertyTypes.toList.map { case (namespace, props) =>
      val objectName = Term.Name(normalize(namespace))
      val typeName = Type.Name(normalize(namespace))

      val classDefs = props.map { prop =>
        val className = Type.Name(prop.name)

        val properties = prop.properties.map(mkField(_, None))

        q"case class $className (..$properties)"
      }

      val generic =
        q"implicit val generic = LabelledGeneric[$typeName]"

      q"""object $objectName {
            ..$classDefs
            $generic
         }
       """
    }

  val classStats: List[Defn] =
    SpecsParser.resourceTypes.map { rt =>
      val normalized = normalize(rt.fqn)
      val typeName = Type.Name(normalized)

      val namespace = normalize(rt.fqn.takeWhile(_ != "."))
      val logicalIdProp: Term.Param = param"logicalId: String"
      val properties = logicalIdProp :: rt.properties.map(mkField(_, Some(namespace)))
      val fqn = Term.Name("\"" + rt.fqn + "\"")

      val declaration = q"""case class $typeName (..$properties) extends Resource {
            override def fqn: String = $fqn
         }
       """


       declaration
    }

  private def mkField(property: Property, namespace: Option[Namespace]): Term.Param = {
    val paramName = Term.Name(property.name)

    val typeName = mapAwsType(namespace)(property.awsType)

    if (property.required) {
      param"$paramName: CfExp[$typeName]"
    } else {
      param"$paramName: Option[CfExp[$typeName]]"
    }
  }
}
import CloudformationGen._

class CloudformationGen extends scala.annotation.StaticAnnotation {

  inline def apply(defn: Any): Any = meta {
    defn match {
      case q"object $_ {..$_}" =>
        q"..${objectStats ++ classStats}"
      case _ =>
        abort("@cf-resources must be used on an object")
    }
  }
}
