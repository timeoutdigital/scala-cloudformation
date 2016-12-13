package com

package object timeout {
  case class PropertyType(namespace: String, name: String, properties: List[Property])

  sealed trait AwsType
  sealed trait PrimitiveType extends AwsType
  case class PropertyTypeRef(namespace: String, name: String) extends AwsType

  object PrimitiveType {
    case object String extends PrimitiveType
    case object Long extends PrimitiveType
    case object Integer extends PrimitiveType
    case object Double extends PrimitiveType
    case object Boolean extends PrimitiveType
    case object Timestamp extends PrimitiveType
    case object Json extends PrimitiveType
    val all = Set(String, Long, Integer, Double, Boolean, Timestamp, Json)
    def fromString(str: String): Option[PrimitiveType] = all.find(_.toString == str)
  }

  case class Property(
    name: String,
    awsType: AwsType,
    required: Boolean
  )

  trait Resource {
    def name: String
  }

  case class ResourceType(fqn: String, properties: List[Property])
}
