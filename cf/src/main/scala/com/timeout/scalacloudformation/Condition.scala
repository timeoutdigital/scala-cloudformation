package com.timeout.scalacloudformation

case class Condition(logicalId: String,
                     value: CfExp[Boolean]) extends HasLogicalId
