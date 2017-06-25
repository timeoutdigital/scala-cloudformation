package com.timeout.scalacloudformation

import io.circe.Json


object Template {
  case class Mapping(logicalId: String,
                     value: Map[String, Map[String, String]]) extends HasLogicalId
}
case class Template(Description: Option[String] = None,
                    Metadata: Option[Json] = None,
                    Mappings: List[Template.Mapping] = Nil,
                    Parameters: List[Parameter] = Nil,
                    Conditions: List[Condition] = Nil,
                    Outputs: List[Output] = Nil,
                    Resources: List[Resource])
