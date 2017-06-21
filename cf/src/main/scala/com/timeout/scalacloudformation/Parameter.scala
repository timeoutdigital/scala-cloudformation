package com.timeout.scalacloudformation
import enum.Enum

sealed trait Parameter extends HasLogicalId {
  def logicalId: String
  def Type: Parameter.DataType
  def Description: Option[String]
  def NoEcho: Option[Boolean]
  def ConstraintDescription: Option[String]
}

object Parameter {
  sealed trait AwsType

  object AwsType {
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

    implicit val enum: Enum[AwsType] = Enum.derived[AwsType]
  }

  sealed trait DataType
  object DataType {
    case object String extends DataType
    case object Number extends DataType
    case object `List<Number>` extends DataType
    case object CommaDelimitedList extends DataType
    case class AwsType(tpe: Parameter.AwsType) extends DataType
    case class AwsTypeList(tpe: Parameter.AwsType) extends DataType
  }

  case class Str(logicalId: String,
                 MaxLength: Option[Int] = None,
                 MinLength: Option[Int] = None,
                 Description: Option[String] = None,
                 NoEcho: Option[Boolean] = None,
                 AllowedValues: Option[Set[String]] = None,
                 AllowedPattern: Option[String] = None,
                 Default: Option[String] = None,
                 ConstraintDescription: Option[String] = None) extends Parameter {
    override def Type: DataType = DataType.String
  }

  case class Double(logicalId: String,
                    MaxValue: Option[scala.Double] = None,
                    MinValue: Option[scala.Double] = None,
                    Description: Option[String] = None,
                    NoEcho: Option[Boolean] = None,
                    Default: Option[scala.Double] = None,
                    ConstraintDescription: Option[String] = None) extends Parameter {
    override val Type = DataType.Number
  }
  case class Integer(logicalId: String,
                    MaxValue: Option[Int] = None,
                    MinValue: Option[Int] = None,
                    Description: Option[String] = None,
                    NoEcho: Option[Boolean] = None,
                    Default: Option[Int] = None,
                    ConstraintDescription: Option[String] = None) extends Parameter {
    override val Type = DataType.Number
  }

  case class CommaDelimited(logicalId: String,
                            AllowedValues: Option[Set[String]] = None,
                            Description: Option[String] = None,
                            NoEcho: Option[Boolean] = None,
                            Default: Option[Set[String]] = None,
                            ConstraintDescription: Option[String] = None) extends Parameter {
    override val Type = DataType.CommaDelimitedList
  }

  case class Aws(logicalId: String,
                 awsType: Parameter.AwsType,
                 Description: Option[String] = None,
                 NoEcho: Option[Boolean] = None,
                 Default: Option[String] = None,
                 ConstraintDescription: Option[String] = None) extends Parameter {
    override def Type = DataType.AwsType(awsType)
  }

  case class AwsList(logicalId: String,
                     awsType: Parameter.AwsType,
                     Description: Option[String] = None,
                     NoEcho: Option[Boolean] = None,
                     Default: Option[String] = None,
                     ConstraintDescription: Option[String] = None) extends Parameter {
    override def Type = DataType.AwsTypeList(awsType)
  }
}
