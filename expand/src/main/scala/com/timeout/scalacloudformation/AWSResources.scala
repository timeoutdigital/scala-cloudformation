package com.timeout.scalacloudformation

import com.timeout.CloudformationGen
import shapeless._

object AWSResources {

  trait CfExp[+T]

  trait Resource {
    def fqn: String
    def logicalId: String
  }

  case class Tag(key: String, value: String)

  @CloudformationGen()
  object Gen
}
