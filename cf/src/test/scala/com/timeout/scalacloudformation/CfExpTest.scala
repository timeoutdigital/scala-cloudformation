package com.timeout.scalacloudformation

import com.timeout.scalacloudformation.AWSResources.{CfExp, Resource}
import com.timeout.scalacloudformation.CfExp._
import io.circe.parser._
import io.circe.syntax._
import org.scalatest.{FreeSpec, Matchers}
import Encoding._

object CfExpTest {
  case class TestResource(logicalId: String, foo: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::Test::TestResource"
  }
}

import com.timeout.scalacloudformation.CfExpTest._

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

    val actual = TestResource(
      logicalId = "Logical ID", foo = LitString("bar")
    ).asJson

    Right(actual) should ===(expJson)
  }

  "Fn::Base64 is handled" in {
    val exp =
      """
        |{
        |  "Logical ID": {
        |    "Type": "AWS::Test::TestResource",
        |    "Properties": {
        |      "foo": { "Fn::Base64": "bar" }
        |    }
        |  }
        |}
      """.stripMargin
    val expJson = parse(exp)

    val actual = TestResource(
      logicalId = "Logical ID", foo = FnBase64(LitString("bar"))
    ).asJson

    Right(actual) should ===(expJson)
  }

  "Condition functions are handled" in {
    val exp =
      """
        |{
        |  "Logical ID" : {
        |    "Type" : "AWS::Test::TestResource",
        |    "Properties" : {
        |      "foo" : {
        |        "Fn::If" : [
        |          {
        |            "Fn::Equals" : [
        |              {
        |                "Fn::And" : [
        |                  true,
        |                  {
        |                    "Fn::Not" : [
        |                      true
        |                    ]
        |                  }
        |                ]
        |              },
        |              {
        |                "Fn::Or" : [
        |                  true,
        |                  false
        |                ]
        |              }
        |            ]
        |          },
        |          "a",
        |          "b"
        |        ]
        |      }
        |    }
        |  }
        |}
      """.stripMargin
    val expJson = parse(exp)

    val actual = TestResource(
      logicalId = "Logical ID", foo = FnIf(
        FnEquals(
          FnAnd(
            LitBoolean(true),
            FnNot(
              LitBoolean(true)
            )
          ),
          FnOr(LitBoolean(true), LitBoolean(false))
        ),
        LitString("a"),
        LitString("b")
      )
    ).asJson

    Right(actual) should ===(expJson)
  }

  "Resource Ref is handled" in {
    val resource = TestResource("referenced", LitString("foo"))

    val actual = TestResource("ID", ResourceRef(resource)).asJson

    val expJson = parse(
      """
        |{
        |  "ID" : {
        |    "Type" : "AWS::Test::TestResource",
        |    "Properties" : {
        |      "foo" : {
        |        "Ref" : "referenced"
        |      }
        |    }
        |  }
        |}
      """.stripMargin)

    Right(actual) should ===(expJson)
  }

  "Pseudo Parameter Ref is handled" in {
    val actual = TestResource("ID", PseudoParameterRef(PseudoParameter.AccountId)).asJson

    val expJson = parse(
      """
        |{
        |  "ID" : {
        |    "Type" : "AWS::Test::TestResource",
        |    "Properties" : {
        |      "foo" : {
        |        "Ref" : "AWS::AccountId"
        |      }
        |    }
        |  }
        |}
      """.stripMargin)

    Right(actual) should ===(expJson)
  }
}
