package com.timeout.scalacloudformation

import io.circe.Json


case class Template(Description: Option[String] = None,
                    Metadata: Option[Json] = None,
                    Mappings: Option[Map[String, Map[String, String]]] = None,
                    Parameters: List[Parameter] = Nil,
                    Conditions: List[Condition] = Nil,
                    Outputs: List[Output] = Nil,
                    Resources: List[Resource])
