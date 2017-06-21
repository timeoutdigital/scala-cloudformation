package com.timeout

import java.time.Duration

import io.circe.Json
import enum.Enum

package object scalacloudformation {
  trait ResourceProperty

  trait HasLogicalId {
    def logicalId: String
  }

  object ResourceAttributes {
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

    case class AutoScalingReplacingUpdate(WillReplace: CfExp[Boolean])
    case class AutoScalingRollingUpdate(MaxBatchSize: Option[CfExp[Int]] = None,
                                        MinInstancesInService: Option[CfExp[Int]] = None,
                                        MinSuccessfulInstancesPercent: Option[CfExp[Int]] = None,
                                        PauseTime: Option[CfExp[Duration]] = None,
                                        WaitOnResourceSignals: Option[Boolean])
    case class AutoScalingScheduledAction(IgnoreUnmodifiedGroupSizeProperties: CfExp[Boolean])

    case class UpdatePolicy(AutoScalingReplacingUpdate: Option[AutoScalingReplacingUpdate] = None,
                            AutoScalingRollingUpdate: Option[AutoScalingRollingUpdate] = None,
                            AutoScalingScheduledAction: Option[AutoScalingScheduledAction] = None) extends ResourceProperty
  }



  trait Resource extends HasLogicalId {
    def fqn: String
    def logicalId: String
    def DependsOn: Option[Resource]
    def UpdatePolicy: Option[ResourceAttributes.UpdatePolicy]
    def DeletionPolicy: Option[ResourceAttributes.DeletionPolicy]
    def CreationPolicy: Option[ResourceAttributes.CreationPolicy]
    def jsonEncode: Json
  }

  case class Tag(key: String, value: String)
}
