package com.timeout

import java.time.Duration

import io.circe.Json
import enum.Enum

package object scalacloudformation {
  trait ResourceProperty

  trait HasLogicalId {
    def logicalId: String
  }

  case class ResourceSignal(Count: CfExp[Int],
                            Timeout: CfExp[Duration])

  case class AutoscalingCreationPolicy(MinSuccessfulInstancesPercent: CfExp[Int])

  case class CreationPolicy(AutoscalingCreationPolicy: Option[AutoscalingCreationPolicy] = None,
                            ResourceSignal: Option[ResourceSignal] = None) extends ResourceProperty

  sealed trait DeletionPolicy extends ResourceProperty
  object DeletionPolicy {
    case object Delete extends DeletionPolicy
    case object Retain extends DeletionPolicy
    case object Snapshot extends DeletionPolicy

    implicit val enum: Enum[DeletionPolicy] = Enum.derived[DeletionPolicy]
  }

  sealed trait UpdatePolicy extends ResourceProperty
  case class AutoScalingReplacingUpdate(WillReplace: CfExp[Boolean]) extends UpdatePolicy
  case class AutoScalingRollingUpdate(MaxBatchSize: Option[CfExp[Int]] = None,
                                      MinInstancesInService: Option[CfExp[Int]] = None,
                                      MinSuccessfulInstancesPercent: Option[CfExp[Int]] = None,
                                      PauseTime: Option[CfExp[Duration]] = None,
                                      WaitOnResourceSignals: Option[Boolean]) extends UpdatePolicy
  case class AutoScalingScheduledAction(IgnoreUnmodifiedGroupSizeProperties: CfExp[Boolean]) extends UpdatePolicy

  trait Resource extends HasLogicalId {
    def fqn: String
    def logicalId: String
    def jsonEncode: Json
    def DependsOn: Option[CfExp[String]]
    def Metadata: Option[CfExp[Json]]
    def DeletionPolicy: Option[CfExp[DeletionPolicy]]
    def CreationPolicy: Option[CfExp[CreationPolicy]]
  }

  case class Tag(key: String, value: String)
}
