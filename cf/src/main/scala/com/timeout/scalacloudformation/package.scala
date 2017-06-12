package com.timeout

import io.circe.Json

package object scalacloudformation {
  trait Resource {
    def fqn: String
    def logicalId: String
    def jsonEncode: Json
  }

  case class Tag(key: String, value: String)
}
