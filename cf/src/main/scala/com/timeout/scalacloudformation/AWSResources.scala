package com.timeout.scalacloudformation

import com.timeout.CloudformationGen

/**
  * Literal - OK
  * ParamRef(p.1376)
  * ResourceRef(p.1376)
  * ExternalRef(p.1376)
  * Fn::Base64
  * Fn::FindInMap(p.1356) -> 2 levels of nesting!
  * Fn::GetAtt(p.1359)
  * Fn::GetAZs(p.1366)
  * Fn::ImportValue(p.1368)
  * Fn::Join(p.1370)
  * Fn::Select(p.1372)
  * Fn::Sub(p.1374)
  * Fn::And(p.1341)
  * Fn::Equals(p.1342)
  * Fn::If(p.1343)
  * Fn::Not(p.1346)
  * Fn::Or(p.1347)
  * Condition
  */

object AWSResources {
  import shapeless._

  trait Resource {
    def fqn: String
    def logicalId: String
  }

  case class Tag(key: String, value: String)

  @CloudformationGen
  object Gen
}
