package com.timeout

import scala.meta._
import com.timeout.scalacloudformation.HasGetAtt

object CodeGen {
  case class Config(excludePrefixes: Set[String] = Set.empty)
}

class CodeGen(conf: CodeGen.Config) {
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

  private val resourceTypesByFqn =
    SpecsParser.resourceTypes(conf.excludePrefixes).map(r => r.fqn -> r).toMap

  private val propertyTypesByFqn =
    SpecsParser.propertyTypes(conf.excludePrefixes)

  // Contains custom properties and generic derivations
  val objectStats: List[Defn.Object] = propertyTypesByFqn.toList.map { case (namespace, props) =>
      val objectName = Term.Name(normalize(namespace))
      val typeName = Type.Name(normalize(namespace))

      val classDefs = props.map { prop =>
        val className = Type.Name(prop.name)
        val properties = prop.properties.map(mkField(_, None))
        q"case class $className (..$properties) extends ResourceProperty"
      }

      val objDefs = props.map { prop =>
        val objName = Term.Name(prop.name)
        val typeName = Type.Name(prop.name)

        q"""object $objName {
              implicit val encoder: Encoder[$typeName] = deriveEncoder[$typeName]
            }
        """
      }
      val getAttInstances =
        GetAttSupport.attributesByResourceType
          .get(namespace).map(mkGetAttInstance)
          .getOrElse(Nil)

      val encoder =
        q"implicit val encoder = Encoder.instance[$typeName](_.jsonEncode)"

      val classObjDefs = intersperse[Stat](classDefs, objDefs)

      q"""object $objectName {
            ..$classObjDefs
            $encoder
            ..$getAttInstances
         }
       """
    }

  val classStats: List[Defn] =
    resourceTypesByFqn.values.toList.map { rt =>
      val normalized = normalize(rt.fqn)
      val typeName = Type.Name(normalized)
      val namespace = normalize(rt.fqn.takeWhile(_ != "."))

      val logicalIdProp: Term.Param = param"logicalId: String"
      val dependsOn: Term.Param = param"DependsOn: Option[Resource] = None"
      val deletionPolicy: Term.Param = param"DeletionPolicy: Option[DeletionPolicy] = None"
      val creationPolicy: Term.Param = param"CreationPolicy: Option[CreationPolicy] = None"
      val updatePolicy: Term.Param = param"UpdatePolicy: Option[UpdatePolicy] = None"

      val commonProps =
        logicalIdProp :: dependsOn :: creationPolicy :: deletionPolicy :: updatePolicy :: Nil

      val properties = commonProps ++ rt.properties.map(mkField(_, Some(namespace)))
      val fqn = Term.Name("\"" + rt.fqn + "\"")

      val jsonFields = rt.properties.map { prop =>
        q"${Lit(prop.name)} -> ${Term.Name(prop.name)}.asJson"
      }

      val declaration = q"""
         case class $typeName (..$properties) extends Resource {
           override def fqn: String = $fqn
           override def jsonEncode: io.circe.Json =
             Json.obj(
               logicalId -> Json.obj(
                 "Type" -> Json.fromString(fqn),
                 "DependsOn" -> DependsOn.map(_.logicalId).asJson,
                 "UpdatePolicy" -> UpdatePolicy.asJson,
                 "DeletionPolicy" -> DeletionPolicy.asJson,
                 "CreationPolicy" -> CreationPolicy.asJson,
                 "Properties" -> Json.obj(..$jsonFields)
               )
             )
         }
       """

      declaration
    }

  private def mkGetAttInstance(attr: GetAttSupport.Attribute): List[Stat] =
    attr.attributes.map { name =>
      val normalisedName = name.replace(".", "Dot")
      val valName = Pat.Var.Term(Term.Name(s"attr$normalisedName"))
      val attrName = Lit(normalisedName)
      val typeName = Type.Name(normalize(attr.resourceTypeFqn))
      q"""implicit val $valName = HasGetAtt.mk[$typeName]($attrName)"""
    }

  private def mkField(property: Property, namespace: Option[Namespace]): Term.Param = {
    val paramName = Term.Name(property.name)
    val typeName = mapAwsType(namespace)(property.awsType)

    if (property.required) {
      param"$paramName: CfExp[$typeName]"
    } else {
      param"$paramName: Option[CfExp[$typeName]] = None"
    }
  }

  private def intersperse[A](a : List[A], b : List[A]): List[A] = a match {
    case first :: rest => first :: intersperse(b, rest)
    case _ => b
  }
}
