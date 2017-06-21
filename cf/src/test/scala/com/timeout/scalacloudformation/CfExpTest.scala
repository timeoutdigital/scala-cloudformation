package com.timeout.scalacloudformation

import com.timeout.scalacloudformation.CfExp._
import io.circe.parser._
import io.circe.syntax._
import org.scalatest.{FreeSpec, Matchers}
import Encoding._
import io.circe.{Encoder, Json}

object CfExpTest {
  case class TestResource(logicalId: String, foo: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::Test::TestResource"
    override def jsonEncode: Json =
      Json.obj(
        logicalId -> Json.obj(
          "Type" -> Json.fromString(fqn),
          "Properties" -> Json.obj("foo" -> foo.asJson)
        )
      )

    override val DependsOn = None
    override val UpdatePolicy = None
    override val DeletionPolicy = None
    override val CreationPolicy = None
  }

  implicit val enc = Encoder.instance[TestResource](_.jsonEncode)
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
      logicalId = "Logical ID", foo = Lit("bar")
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
      logicalId = "Logical ID", foo = FnBase64(Lit("bar"))
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
            Lit(true),
            FnNot(
              Lit(true)
            )
          ),
          FnOr(Lit(true), Lit(false))
        ),
        Lit("a"),
        Lit("b")
      )
    ).asJson

    Right(actual) should ===(expJson)
  }

  "Resource Ref is handled" in {
    val resource = TestResource("referenced", Lit("foo"))

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
