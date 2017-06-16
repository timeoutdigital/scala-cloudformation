package com.timeout.scalacloudformation

object Parameter {
  sealed trait AwsType
  case object `AWS::EC2::Instance` extends AwsType
  case object `AWS::EC2::AvailabilityZone::Name` extends AwsType
  case object `AWS::EC2::Instance::Id` extends AwsType
  case object `AWS::EC2::Image::Id` extends AwsType
  case object `AWS::EC2::KeyPair::KeyName` extends AwsType
  case object `AWS::EC2::SecurityGroup::GroupName` extends AwsType
  case object `AWS::EC2::SecurityGroup::Id` extends AwsType
  case object `AWS::EC2::Subnet::Id` extends AwsType
  case object `AWS::EC2::Volume::Id` extends AwsType
  case object `AWS::EC2::VPC::Id` extends AwsType
  case object `AWS::Route53::HostedZone::Id` extends AwsType

  sealed trait DataType
  object DataType {
    case object String extends DataType
    case object Number extends DataType
    case object `List<Number>` extends DataType
    case object CommaDelimitedList extends DataType
    case class AwsType(tpe: Parameter.AwsType) extends DataType
    case class AwsListType(tpe: Parameter.AwsType) extends DataType
  }
}

import Parameter._

sealed trait Parameter[A] {
  def Type: DataType
  def Description: Option[String]
  def NoEcho: Boolean
  def Default: Option[A]
}

case class StringParam(MaxLength: Option[Int] = None,
                       MinLength: Option[Int] = None,
                       Description: Option[String] = None,
                       NoEcho: Boolean = false,
                       AllowedValues: Option[Set[String]] = None,
                       AllowedPattern: Option[String] = None,
                       Default: Option[String]) extends Parameter[String] {
  override def Type: DataType = DataType.String
}

case class NumberParam[T: Numeric](MaxValue: Option[T] = None,
                                   MinValue: Option[T] = None,
                                   Description: Option[String] = None,
                                   NoEcho: Boolean = false,
                                   Default: Option[T] = None) extends Parameter[T] {
  override val Type = DataType.Number
}

case class CommaDelimited(AllowedValues: Option[Set[String]] = None,
                          Description: Option[String] = None,
                          NoEcho: Boolean = false,
                          DefaultValues: Option[Set[String]]) extends Parameter[String] {
  override val Type = DataType.CommaDelimitedList
  override def Default = DefaultValues.map(_.mkString(","))
}

case class AwsType(awsType: Parameter.AwsType,
                   Description: Option[String] = None,
                   NoEcho: Boolean = false,
                   Default: Option[String]) extends Parameter[String] {
  override def Type = DataType.AwsType(awsType)
}

case class AwsListType(awsType: Parameter.AwsType,
                       Description: Option[String] = None,
                       NoEcho: Boolean = false,
                       Default: Option[String]) extends Parameter[String] {
  override def Type = DataType.AwsListType(awsType)
}
