package com.timeout.scalacloudformation

case class Output(logicalId: String,
                  Value: CfExp[String],
                  Condition: Option[Condition] = None,
                  Export: Option[CfExp[String]] = None,
                  Description: Option[String] = None) extends HasLogicalId
