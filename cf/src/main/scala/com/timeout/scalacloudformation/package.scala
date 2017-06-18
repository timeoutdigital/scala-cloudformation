package com.timeout

import io.circe.Json

package object scalacloudformation {
  trait Resource {
    def fqn: String
    def logicalId: String
    def jsonEncode: Json
  }

  trait ResourceProperty

  case class Tag(key: String, value: String)

  trait HasLogicalId {
    def logicalId: String
  }
}
