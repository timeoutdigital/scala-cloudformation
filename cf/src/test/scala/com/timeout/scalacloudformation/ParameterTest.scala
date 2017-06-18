package com.timeout.scalacloudformation

import org.scalatest.FreeSpec
import org.scalatest.Matchers._
import Encoding._
import io.circe._
import io.circe.syntax._
import io.circe.parser._

class ParameterTest extends FreeSpec {
  def json(s: String): Json =
    parser.parse(s).right.get

  def dropNullKeys(j: Json): Json ={
    val jsonS = j.pretty(Printer.spaces2.copy(dropNullKeys = true))
    parser.parse(jsonS).right.get
  }

  "Parameter.Str" in {
    val param: Parameter = Parameter.Str(
      logicalId = "DBPwd",
      NoEcho = Some(true),
      MinLength = Some(1),
      MaxLength = Some(41),
      Description = Some("The database admin account password"),
      AllowedPattern = Some("^[a-zA-Z0-9]*$")
    )

    dropNullKeys(param.asJson) should ===(
      json("""
        |  {
        |    "DBPwd" : {
        |      "NoEcho" : true,
        |      "Description" : "The database admin account password",
        |      "Type" : "String",
        |      "MinLength" : 1,
        |      "MaxLength" : 41,
        |      "AllowedPattern" : "^[a-zA-Z0-9]*$"
        |    }
        |  }
      """.stripMargin)

    )
  }
  "Parameter.Number" in {
    val param: Parameter = Parameter.Number(
      logicalId = "DBPort",
      Default = Some(4329),
      Description = Some("TCP/IP port for the database"),
      MinValue = Some(1150),
      MaxValue = Some(6553)
    )
     dropNullKeys(param.asJson) should ===(
      json("""
             |  {
             |    "DBPort" : {
             |      "Default" : 4329,
             |      "Description" : "TCP/IP port for the database",
             |      "Type" : "Number",
             |      "MinValue" : 1150,
             |      "MaxValue" : 6553
             |    }
             |  }
      """.stripMargin))
  }

  "Parameter.CommaDelimited" in {
    val param: Parameter = Parameter.CommaDelimited(
      logicalId = "UserRoles",
      AllowedValues = Some(Set("guest","newhire","employee")),
      Default = Some(Set("guest", "newhire"))
    )

    dropNullKeys(param.asJson) should ===(
      json("""
             |  {
             |    "UserRoles" : {
             |       "Type" : "CommaDelimitedList",
             |       "Default" : "guest,newhire",
             |       "AllowedValues" : "guest,newhire,employee"
             |    }
             |  }
      """.stripMargin))
  }

  "Parameter.AwsList" in {
     val param: Parameter = Parameter.AwsList(
      logicalId = "SubnetIds",
      Description = Some("Subnet ids"),
      awsType = Parameter.AwsType.`AWS::EC2::Subnet::Id`
    )

    dropNullKeys(param.asJson) should ===(
      json("""
             |  {
             |    "SubnetIds" : {
             |      "Type": "List<AWS::EC2::Subnet::Id>",
             |      "Description": "Subnet ids"
             |    }
             |   }
      """.stripMargin))
  }

  "Parameter.Aws" in {
    val param: Parameter = Parameter.Aws(
      logicalId = "userKeys",
      Description = Some("Amazon EC2 Key Pair"),
      awsType = Parameter.AwsType.`AWS::EC2::KeyPair::KeyName`
    )

    dropNullKeys(param.asJson) should ===(
      json(
        """
             |  {
             |    "userKeys" : {
             |      "Type": "AWS::EC2::KeyPair::KeyName",
             |      "Description": "Amazon EC2 Key Pair"
             |    }
             |   }
      """.

          stripMargin))
    }
}
