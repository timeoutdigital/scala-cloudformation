package com.timeout.scalacloudformation

import com.timeout.CloudformationGen
import io.circe.Encoder
import io.circe.generic.semiauto._
import io.circe.Json
import io.circe.syntax._
import Encoding._

object AWSResources {
  @CloudformationGen()
  object Gen
}
