package com.timeout.scalacloudformation

import com.timeout.Encoding._
import com.timeout.scalacloudformation.AWSResources.Resource
import com.timeout.scalacloudformation.CfExp.LitString
import io.circe.parser._
import io.circe.syntax._
import org.scalatest.{FreeSpec, Matchers}
import shapeless.LabelledGeneric

case class TestResource(logicalId: String, foo: CfExp[String]) extends Resource {
  override def fqn: String = "AWS::Test::TestResource"
}

class CfExpTest extends FreeSpec with Matchers {
  "Literals are handled" in {
    val exp =
      """
        |{
        |  "Logical ID": {
        |    "Type": "AWS::Test::TestResource",
        |    "Properties": {
        |      "foo": "bar"
        |    }
        |  }
        |}
      """.stripMargin
    val expJson = parse(exp)

    implicit val genResource = LabelledGeneric[TestResource]

    val actual = TestResource(
      logicalId = "Logical ID", foo = LitString("bar")
    ).asJson

    Right(actual) should ===(expJson)
  }
}
