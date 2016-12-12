package com.timeout

sealed trait PrimitiveType

object PrimitiveType {
  case object String extends PrimitiveType
  case object Long extends PrimitiveType
  case object Integer extends PrimitiveType
  case object Double extends PrimitiveType
  case object Boolean extends PrimitiveType
  case object Timestamp extends PrimitiveType
  val all = Set(String, Long, Integer, Double, Boolean, Timestamp)
  def fromString(str: String): Option[PrimitiveType] = all.find(_.toString == str)
}

case class Property(
  name: String,
  primitiveType: Option[PrimitiveType],
  required: Boolean
)

case class ResourceType(fqn: String, properties: List[Property])
