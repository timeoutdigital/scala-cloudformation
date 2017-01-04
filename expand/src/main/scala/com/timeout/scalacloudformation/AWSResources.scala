package com.timeout.scalacloudformation

import shapeless._

object AWSResources {

  trait CfExp[+T]

  trait Resource {
    def fqn: String
    def logicalId: String
  }

  case class Tag(key: String, value: String)


  object AWSIAMUser {

    case class LoginProfile(Password: CfExp[String], PasswordResetRequired: Option[CfExp[Boolean]])

    case class Policy(PolicyDocument: CfExp[io.circe.Json], PolicyName: CfExp[String])

    implicit val generic = LabelledGeneric[AWSIAMUser]
  }

  object AWSElasticsearchDomain {

    case class ElasticsearchClusterConfig(DedicatedMasterType: Option[CfExp[String]], DedicatedMasterEnabled: Option[CfExp[Boolean]], DedicatedMasterCount: Option[CfExp[Int]], InstanceCount: Option[CfExp[Int]], InstanceType: Option[CfExp[String]], ZoneAwarenessEnabled: Option[CfExp[Boolean]])

    case class SnapshotOptions(AutomatedSnapshotStartHour: Option[CfExp[Int]])

    case class EBSOptions(EBSEnabled: Option[CfExp[Boolean]], VolumeType: Option[CfExp[String]], VolumeSize: Option[CfExp[Int]], Iops: Option[CfExp[Int]])

    implicit val generic = LabelledGeneric[AWSElasticsearchDomain]
  }

  object AWSRDSOptionGroup {

    case class OptionSetting(Value: Option[CfExp[String]], Name: Option[CfExp[String]])

    case class OptionConfiguration(OptionName: CfExp[String], OptionSettings: Option[CfExp[OptionSetting]], Port: Option[CfExp[Int]], VpcSecurityGroupMemberships: Option[CfExp[List[String]]], DBSecurityGroupMemberships: Option[CfExp[List[String]]])

    implicit val generic = LabelledGeneric[AWSRDSOptionGroup]
  }

  object AWSOpsWorksStack {

    case class ChefConfiguration(BerkshelfVersion: Option[CfExp[String]], ManageBerkshelf: Option[CfExp[Boolean]])

    case class Source(Username: Option[CfExp[String]], Password: Option[CfExp[String]], Revision: Option[CfExp[String]], SshKey: Option[CfExp[String]], Url: Option[CfExp[String]], Type: Option[CfExp[String]])

    case class ElasticIp(Ip: CfExp[String], Name: Option[CfExp[String]])

    case class RdsDbInstance(DbUser: CfExp[String], DbPassword: CfExp[String], RdsDbInstanceArn: CfExp[String])

    case class StackConfigurationManager(Version: Option[CfExp[String]], Name: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSOpsWorksStack]
  }

  object AWSRoute53HealthCheck {

    case class HealthCheckTag(Value: CfExp[String], Key: CfExp[String])

    case class HealthCheckConfig(IPAddress: Option[CfExp[String]], ResourcePath: Option[CfExp[String]], FullyQualifiedDomainName: Option[CfExp[String]], SearchString: Option[CfExp[String]], Port: Option[CfExp[Int]], RequestInterval: Option[CfExp[Int]], FailureThreshold: Option[CfExp[Int]], Type: CfExp[String])

    implicit val generic = LabelledGeneric[AWSRoute53HealthCheck]
  }

  object AWSCodeDeployDeploymentConfig {

    case class MinimumHealthyHosts(Value: Option[CfExp[Int]], Type: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSCodeDeployDeploymentConfig]
  }

  object AWSEFSFileSystem {

    case class ElasticFileSystemTag(Value: CfExp[String], Key: CfExp[String])

    implicit val generic = LabelledGeneric[AWSEFSFileSystem]
  }

  object AWSConfigDeliveryChannel {

    case class ConfigSnapshotDeliveryProperties(DeliveryFrequency: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSConfigDeliveryChannel]
  }

  object AWSApiGatewayDeployment {

    case class MethodSetting(CachingEnabled: Option[CfExp[Boolean]], ResourcePath: Option[CfExp[String]], LoggingLevel: Option[CfExp[String]], CacheTtlInSeconds: Option[CfExp[Int]], MetricsEnabled: Option[CfExp[Boolean]], ThrottlingRateLimit: Option[CfExp[Double]], DataTraceEnabled: Option[CfExp[Boolean]], CacheDataEncrypted: Option[CfExp[Boolean]], HttpMethod: Option[CfExp[String]], ThrottlingBurstLimit: Option[CfExp[Int]])

    case class StageDescription(ClientCertificateId: Option[CfExp[String]], CachingEnabled: Option[CfExp[Boolean]], MethodSettings: Option[CfExp[List[MethodSetting]]], CacheClusterEnabled: Option[CfExp[Boolean]], StageName: Option[CfExp[String]], LoggingLevel: Option[CfExp[String]], CacheTtlInSeconds: Option[CfExp[Int]], MetricsEnabled: Option[CfExp[Boolean]], CacheClusterSize: Option[CfExp[String]], Description: Option[CfExp[String]], ThrottlingRateLimit: Option[CfExp[Double]], Variables: Option[CfExp[Map[String, String]]], DataTraceEnabled: Option[CfExp[Boolean]], CacheDataEncrypted: Option[CfExp[Boolean]], ThrottlingBurstLimit: Option[CfExp[Int]])

    implicit val generic = LabelledGeneric[AWSApiGatewayDeployment]
  }

  object AWSKinesisFirehoseDeliveryStream {

    case class S3DestinationConfiguration(CompressionFormat: CfExp[String], BufferingHints: CfExp[BufferingHints], EncryptionConfiguration: Option[CfExp[EncryptionConfiguration]], CloudWatchLoggingOptions: Option[CfExp[CloudWatchLoggingOptions]], RoleARN: CfExp[String], Prefix: CfExp[String], BucketARN: CfExp[String])

    case class KMSEncryptionConfig(AWSKMSKeyARN: CfExp[String])

    case class RedshiftDestinationConfiguration(Username: CfExp[String], ClusterJDBCURL: CfExp[String], CloudWatchLoggingOptions: Option[CfExp[CloudWatchLoggingOptions]], RoleARN: CfExp[String], Password: CfExp[String], CopyCommand: CfExp[CopyCommand], S3Configuration: CfExp[S3DestinationConfiguration])

    case class CloudWatchLoggingOptions(Enabled: Option[CfExp[Boolean]], LogStreamName: Option[CfExp[String]], LogGroupName: Option[CfExp[String]])

    case class EncryptionConfiguration(KMSEncryptionConfig: Option[CfExp[KMSEncryptionConfig]], NoEncryptionConfig: Option[CfExp[String]])

    case class ElasticsearchRetryOptions(DurationInSeconds: CfExp[Int])

    case class CopyCommand(DataTableName: CfExp[String], DataTableColumns: Option[CfExp[String]], CopyOptions: Option[CfExp[String]])

    case class ElasticsearchBufferingHints(IntervalInSeconds: CfExp[Int], SizeInMBs: CfExp[Int])

    case class ElasticsearchDestinationConfiguration(IndexRotationPeriod: CfExp[String], RetryOptions: CfExp[ElasticsearchRetryOptions], BufferingHints: CfExp[ElasticsearchBufferingHints], TypeName: CfExp[String], CloudWatchLoggingOptions: Option[CfExp[CloudWatchLoggingOptions]], RoleARN: CfExp[String], S3BackupMode: CfExp[String], S3Configuration: CfExp[S3DestinationConfiguration], DomainARN: CfExp[String], IndexName: CfExp[String])

    case class BufferingHints(IntervalInSeconds: CfExp[Int], SizeInMBs: CfExp[Int])

    implicit val generic = LabelledGeneric[AWSKinesisFirehoseDeliveryStream]
  }

  object AWSIAMRole {

    case class Policy(PolicyDocument: CfExp[io.circe.Json], PolicyName: CfExp[String])

    implicit val generic = LabelledGeneric[AWSIAMRole]
  }

  object AWSElasticBeanstalkApplicationVersion {

    case class SourceBundle(S3Key: CfExp[String], S3Bucket: CfExp[String])

    implicit val generic = LabelledGeneric[AWSElasticBeanstalkApplicationVersion]
  }

  object AWSElasticBeanstalkConfigurationTemplate {

    case class ConfigurationOptionSetting(Value: CfExp[String], Namespace: CfExp[String], OptionName: CfExp[String])

    case class SourceConfiguration(ApplicationName: CfExp[String], TemplateName: CfExp[String])

    implicit val generic = LabelledGeneric[AWSElasticBeanstalkConfigurationTemplate]
  }

  object AWSRedshiftClusterParameterGroup {

    case class Parameter(ParameterName: CfExp[String], ParameterValue: CfExp[String])

    implicit val generic = LabelledGeneric[AWSRedshiftClusterParameterGroup]
  }

  object AWSElastiCacheReplicationGroup {

    case class NodeGroupConfiguration(Slots: Option[CfExp[String]], PrimaryAvailabilityZone: Option[CfExp[String]], ReplicaAvailabilityZones: Option[CfExp[List[String]]], ReplicaCount: Option[CfExp[Int]])

    implicit val generic = LabelledGeneric[AWSElastiCacheReplicationGroup]
  }

  object AWSOpsWorksInstance {

    case class TimeBasedAutoScaling(Wednesday: Option[CfExp[Map[String, String]]], Monday: Option[CfExp[Map[String, String]]], Saturday: Option[CfExp[Map[String, String]]], Thursday: Option[CfExp[Map[String, String]]], Tuesday: Option[CfExp[Map[String, String]]], Friday: Option[CfExp[Map[String, String]]], Sunday: Option[CfExp[Map[String, String]]])

    case class EbsBlockDevice(VolumeType: Option[CfExp[String]], SnapshotId: Option[CfExp[String]], VolumeSize: Option[CfExp[Int]], Iops: Option[CfExp[Int]], DeleteOnTermination: Option[CfExp[Boolean]])

    case class BlockDeviceMapping(Ebs: Option[CfExp[EbsBlockDevice]], DeviceName: Option[CfExp[String]], VirtualName: Option[CfExp[String]], NoDevice: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSOpsWorksInstance]
  }

  object AWSApplicationAutoScalingScalingPolicy {

    case class StepAdjustment(MetricIntervalUpperBound: Option[CfExp[Double]], MetricIntervalLowerBound: Option[CfExp[Double]], ScalingAdjustment: CfExp[Int])

    case class StepScalingPolicyConfiguration(MetricAggregationType: Option[CfExp[String]], Cooldown: Option[CfExp[Int]], StepAdjustments: Option[CfExp[List[StepAdjustment]]], MinAdjustmentMagnitude: Option[CfExp[Int]], AdjustmentType: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSApplicationAutoScalingScalingPolicy]
  }

  object AWSAutoScalingLaunchConfiguration {

    case class BlockDevice(VolumeType: Option[CfExp[String]], SnapshotId: Option[CfExp[String]], Encrypted: Option[CfExp[Boolean]], VolumeSize: Option[CfExp[String]], Iops: Option[CfExp[Int]], DeleteOnTermination: Option[CfExp[Boolean]])

    case class BlockDeviceMapping(Ebs: Option[CfExp[BlockDevice]], DeviceName: CfExp[String], VirtualName: Option[CfExp[String]], NoDevice: Option[CfExp[Boolean]])

    implicit val generic = LabelledGeneric[AWSAutoScalingLaunchConfiguration]
  }

  object AWSEMRStep {

    case class HadoopJarStepConfig(Args: Option[CfExp[List[String]]], Jar: CfExp[String], MainClass: Option[CfExp[String]], StepProperties: Option[CfExp[List[KeyValue]]])

    case class KeyValue(Value: Option[CfExp[String]], Key: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSEMRStep]
  }

  object AWSApiGatewayApiKey {

    case class StageKey(StageName: Option[CfExp[String]], RestApiId: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSApiGatewayApiKey]
  }

  object AWSCloudFrontDistribution {

    case class CustomOriginConfig(OriginProtocolPolicy: CfExp[String], OriginSSLProtocols: Option[CfExp[List[String]]], HTTPPort: Option[CfExp[Int]], HTTPSPort: Option[CfExp[Int]])

    case class CacheBehavior(PathPattern: CfExp[String], TrustedSigners: Option[CfExp[List[String]]], SmoothStreaming: Option[CfExp[Boolean]], MaxTTL: Option[CfExp[Long]], ForwardedValues: CfExp[ForwardedValues], AllowedMethods: Option[CfExp[List[String]]], CachedMethods: Option[CfExp[List[String]]], TargetOriginId: CfExp[String], DefaultTTL: Option[CfExp[Long]], Compress: Option[CfExp[Boolean]], MinTTL: Option[CfExp[Long]], ViewerProtocolPolicy: CfExp[String])

    case class ForwardedValues(QueryStringCacheKeys: Option[CfExp[List[String]]], Cookies: Option[CfExp[Cookies]], Headers: Option[CfExp[List[String]]], QueryString: CfExp[Boolean])

    case class Restrictions(GeoRestriction: CfExp[GeoRestriction])

    case class S3OriginConfig(OriginAccessIdentity: Option[CfExp[String]])

    case class Logging(IncludeCookies: Option[CfExp[Boolean]], Prefix: Option[CfExp[String]], Bucket: CfExp[String])

    case class DistributionConfig(WebACLId: Option[CfExp[String]], ViewerCertificate: Option[CfExp[ViewerCertificate]], Enabled: CfExp[Boolean], CacheBehaviors: Option[CfExp[List[CacheBehavior]]], PriceClass: Option[CfExp[String]], Origins: CfExp[List[Origin]], HttpVersion: Option[CfExp[String]], Logging: Option[CfExp[Logging]], DefaultRootObject: Option[CfExp[String]], CustomErrorResponses: Option[CfExp[List[CustomErrorResponse]]], Restrictions: Option[CfExp[Restrictions]], Comment: Option[CfExp[String]], DefaultCacheBehavior: CfExp[DefaultCacheBehavior], Aliases: Option[CfExp[List[String]]])

    case class CustomErrorResponse(ErrorCachingMinTTL: Option[CfExp[Long]], ResponsePagePath: Option[CfExp[String]], ErrorCode: CfExp[Int], ResponseCode: Option[CfExp[Int]])

    case class DefaultCacheBehavior(TrustedSigners: Option[CfExp[List[String]]], SmoothStreaming: Option[CfExp[Boolean]], MaxTTL: Option[CfExp[Long]], ForwardedValues: CfExp[ForwardedValues], AllowedMethods: Option[CfExp[List[String]]], CachedMethods: Option[CfExp[List[String]]], TargetOriginId: CfExp[String], DefaultTTL: Option[CfExp[Long]], Compress: Option[CfExp[Boolean]], MinTTL: Option[CfExp[Long]], ViewerProtocolPolicy: CfExp[String])

    case class GeoRestriction(Locations: Option[CfExp[List[String]]], RestrictionType: CfExp[String])

    case class ViewerCertificate(AcmCertificateArn: Option[CfExp[String]], MinimumProtocolVersion: Option[CfExp[String]], CloudFrontDefaultCertificate: Option[CfExp[Boolean]], SslSupportMethod: Option[CfExp[String]], IamCertificateId: Option[CfExp[String]])

    case class Cookies(WhitelistedNames: Option[CfExp[List[String]]], Forward: CfExp[String])

    case class Origin(Id: CfExp[String], S3OriginConfig: Option[CfExp[S3OriginConfig]], OriginCustomHeaders: Option[CfExp[List[OriginCustomHeader]]], DomainName: CfExp[String], OriginPath: Option[CfExp[String]], CustomOriginConfig: Option[CfExp[CustomOriginConfig]])

    case class OriginCustomHeader(HeaderName: CfExp[String], HeaderValue: CfExp[String])

    implicit val generic = LabelledGeneric[AWSCloudFrontDistribution]
  }

  object AWSRoute53HostedZone {

    case class HostedZoneTag(Value: CfExp[String], Key: CfExp[String])

    case class VPC(VPCId: CfExp[String], VPCRegion: CfExp[String])

    case class HostedZoneConfig(Comment: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSRoute53HostedZone]
  }

  object AWSDirectoryServiceMicrosoftAD {

    case class VpcSettings(VpcId: CfExp[String], SubnetIds: CfExp[List[String]])

    implicit val generic = LabelledGeneric[AWSDirectoryServiceMicrosoftAD]
  }

  object AWSGameLiftBuild {

    case class S3Location(RoleArn: CfExp[String], Bucket: CfExp[String], Key: CfExp[String])

    implicit val generic = LabelledGeneric[AWSGameLiftBuild]
  }

  object AWSOpsWorksApp {

    case class EnvironmentVariable(Value: CfExp[String], Secure: Option[CfExp[Boolean]], Key: CfExp[String])

    case class DataSource(Arn: Option[CfExp[String]], DatabaseName: Option[CfExp[String]], Type: Option[CfExp[String]])

    case class Source(Username: Option[CfExp[String]], Password: Option[CfExp[String]], Revision: Option[CfExp[String]], SshKey: Option[CfExp[String]], Url: Option[CfExp[String]], Type: Option[CfExp[String]])

    case class SslConfiguration(Certificate: Option[CfExp[String]], PrivateKey: Option[CfExp[String]], Chain: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSOpsWorksApp]
  }

  object AWSLogsMetricFilter {

    case class MetricTransformation(MetricName: CfExp[String], MetricValue: CfExp[String], MetricNamespace: CfExp[String])

    implicit val generic = LabelledGeneric[AWSLogsMetricFilter]
  }

  object AWSSSMAssociation {

    case class ParameterValues(ParameterValues: CfExp[List[String]])

    case class Target(Values: CfExp[List[String]], Key: CfExp[String])

    implicit val generic = LabelledGeneric[AWSSSMAssociation]
  }

  object AWSIAMGroup {

    case class Policy(PolicyDocument: CfExp[io.circe.Json], PolicyName: CfExp[String])

    implicit val generic = LabelledGeneric[AWSIAMGroup]
  }

  object AWSCodePipelineCustomActionType {

    case class Settings(RevisionUrlTemplate: Option[CfExp[String]], ThirdPartyConfigurationUrl: Option[CfExp[String]], ExecutionUrlTemplate: Option[CfExp[String]], EntityUrlTemplate: Option[CfExp[String]])

    case class ConfigurationProperties(Name: CfExp[String], Description: Option[CfExp[String]], Secret: CfExp[Boolean], Required: CfExp[Boolean], Queryable: Option[CfExp[Boolean]], Type: Option[CfExp[String]], Key: CfExp[Boolean])

    case class ArtifactDetails(MaximumCount: CfExp[Int], MinimumCount: CfExp[Int])

    implicit val generic = LabelledGeneric[AWSCodePipelineCustomActionType]
  }

  object AWSEC2NetworkAclEntry {

    case class Icmp(Type: Option[CfExp[Int]], Code: Option[CfExp[Int]])

    case class PortRange(To: Option[CfExp[Int]], From: Option[CfExp[Int]])

    implicit val generic = LabelledGeneric[AWSEC2NetworkAclEntry]
  }

  object AWSElasticLoadBalancingV2LoadBalancer {

    case class LoadBalancerAttribute(Value: Option[CfExp[String]], Key: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSElasticLoadBalancingV2LoadBalancer]
  }

  object AWSRDSDBSecurityGroup {

    case class Ingress(EC2SecurityGroupOwnerId: Option[CfExp[String]], EC2SecurityGroupName: Option[CfExp[String]], EC2SecurityGroupId: Option[CfExp[String]], CDIRIP: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSRDSDBSecurityGroup]
  }

  object AWSOpsWorksLayer {

    case class LoadBasedAutoScaling(UpScaling: Option[CfExp[AutoScalingThresholds]], Enable: Option[CfExp[Boolean]], DownScaling: Option[CfExp[AutoScalingThresholds]])

    case class AutoScalingThresholds(ThresholdsWaitTime: Option[CfExp[Int]], CpuThreshold: Option[CfExp[Double]], InstanceCount: Option[CfExp[Int]], MemoryThreshold: Option[CfExp[Double]], IgnoreMetricsTime: Option[CfExp[Int]], LoadThreshold: Option[CfExp[Double]])

    case class Recipes(Shutdown: Option[CfExp[List[String]]], Deploy: Option[CfExp[List[String]]], Setup: Option[CfExp[List[String]]], Undeploy: Option[CfExp[List[String]]], Configure: Option[CfExp[List[String]]])

    case class ShutdownEventConfiguration(DelayUntilElbConnectionsDrained: Option[CfExp[Boolean]], ExecutionTimeout: Option[CfExp[Int]])

    case class VolumeConfiguration(VolumeType: Option[CfExp[String]], RaidLevel: Option[CfExp[Int]], Size: Option[CfExp[Int]], Iops: Option[CfExp[Int]], NumberOfDisks: Option[CfExp[Int]], MountPoint: Option[CfExp[String]])

    case class LifecycleEventConfiguration(ShutdownEventConfiguration: Option[CfExp[ShutdownEventConfiguration]])

    implicit val generic = LabelledGeneric[AWSOpsWorksLayer]
  }

  object AWSDataPipelinePipeline {

    case class ParameterValue(StringValue: CfExp[String], Id: CfExp[String])

    case class ParameterObject(Id: CfExp[String], Attributes: CfExp[List[ParameterAttribute]])

    case class Field(RefValue: Option[CfExp[String]], StringValue: Option[CfExp[String]], Key: CfExp[String])

    case class PipelineObject(Fields: CfExp[List[Field]], Id: CfExp[String], Name: CfExp[String])

    case class ParameterAttribute(StringValue: CfExp[String], Key: CfExp[String])

    case class PipelineTag(Value: CfExp[String], Key: CfExp[String])

    implicit val generic = LabelledGeneric[AWSDataPipelinePipeline]
  }

  object AWSSNSTopic {

    case class Subscription(Protocol: CfExp[String], Endpoint: CfExp[String])

    implicit val generic = LabelledGeneric[AWSSNSTopic]
  }

  object AWSElasticLoadBalancingV2TargetGroup {

    case class Matcher(HttpCode: CfExp[String])

    case class TargetGroupAttribute(Value: Option[CfExp[String]], Key: Option[CfExp[String]])

    case class TargetDescription(Port: Option[CfExp[Int]], Id: CfExp[String])

    implicit val generic = LabelledGeneric[AWSElasticLoadBalancingV2TargetGroup]
  }

  object AWSApiGatewayRestApi {

    case class S3Location(Version: Option[CfExp[String]], ETag: Option[CfExp[String]], Bucket: Option[CfExp[String]], Key: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSApiGatewayRestApi]
  }

  object AWSDynamoDBTable {

    case class KeySchema(KeyType: CfExp[String], AttributeName: CfExp[String])

    case class ProvisionedThroughput(WriteCapacityUnits: CfExp[Int], ReadCapacityUnits: CfExp[Int])

    case class AttributeDefinition(AttributeType: CfExp[String], AttributeName: CfExp[String])

    case class Projection(ProjectionType: Option[CfExp[String]], NonKeyAttributes: Option[CfExp[List[String]]])

    case class LocalSecondaryIndex(Projection: CfExp[Projection], IndexName: CfExp[String], KeySchema: CfExp[List[KeySchema]])

    case class GlobalSecondaryIndex(ProvisionedThroughput: CfExp[ProvisionedThroughput], Projection: CfExp[Projection], IndexName: CfExp[String], KeySchema: CfExp[List[KeySchema]])

    case class StreamSpecification(StreamViewType: CfExp[String])

    implicit val generic = LabelledGeneric[AWSDynamoDBTable]
  }

  object AWSCertificateManagerCertificate {

    case class DomainValidationOption(ValidationDomain: CfExp[String], DomainName: CfExp[String])

    implicit val generic = LabelledGeneric[AWSCertificateManagerCertificate]
  }

  object AWSECSTaskDefinition {

    case class LogConfiguration(Options: Option[CfExp[Map[String, String]]], LogDriver: CfExp[String])

    case class HostEntry(Hostname: CfExp[String], IpAddress: CfExp[String])

    case class ContainerDefinition(DockerLabels: Option[CfExp[Map[String, String]]], PortMappings: Option[CfExp[List[PortMapping]]], WorkingDirectory: Option[CfExp[String]], Environment: Option[CfExp[List[KeyValuePair]]], Name: Option[CfExp[String]], Memory: Option[CfExp[Int]], DnsServers: Option[CfExp[List[String]]], Essential: Option[CfExp[Boolean]], ExtraHosts: Option[CfExp[List[HostEntry]]], Command: Option[CfExp[List[String]]], Links: Option[CfExp[List[String]]], DnsSearchDomains: Option[CfExp[List[String]]], Cpu: Option[CfExp[Int]], Privileged: Option[CfExp[Boolean]], Ulimits: Option[CfExp[List[Ulimit]]], DisableNetworking: Option[CfExp[Boolean]], DockerSecurityOptions: Option[CfExp[List[String]]], MemoryReservation: Option[CfExp[Int]], VolumesFrom: Option[CfExp[List[VolumeFrom]]], EntryPoint: Option[CfExp[List[String]]], Hostname: Option[CfExp[String]], User: Option[CfExp[String]], LogConfiguration: Option[CfExp[LogConfiguration]], Image: Option[CfExp[String]], ReadonlyRootFilesystem: Option[CfExp[Boolean]], MountPoints: Option[CfExp[List[MountPoint]]])

    case class HostVolumeProperties(SourcePath: Option[CfExp[String]])

    case class MountPoint(ReadOnly: Option[CfExp[Boolean]], SourceVolume: Option[CfExp[String]], ContainerPath: Option[CfExp[String]])

    case class KeyValuePair(Value: Option[CfExp[String]], Name: Option[CfExp[String]])

    case class VolumeFrom(ReadOnly: Option[CfExp[Boolean]], SourceContainer: Option[CfExp[String]])

    case class Volume(Name: Option[CfExp[String]], Host: Option[CfExp[HostVolumeProperties]])

    case class PortMapping(Protocol: Option[CfExp[String]], ContainerPort: Option[CfExp[Int]], HostPort: Option[CfExp[Int]])

    case class Ulimit(HardLimit: CfExp[Int], SoftLimit: CfExp[Int], Name: CfExp[String])

    implicit val generic = LabelledGeneric[AWSECSTaskDefinition]
  }

  object AWSWAFRule {

    case class Predicate(DataId: CfExp[String], Type: CfExp[String], Negated: CfExp[Boolean])

    implicit val generic = LabelledGeneric[AWSWAFRule]
  }

  object AWSS3Bucket {

    case class FilterRule(Value: CfExp[String], Name: CfExp[String])

    case class WebsiteConfiguration(IndexDocument: Option[CfExp[String]], RoutingRules: Option[CfExp[List[RoutingRule]]], ErrorDocument: Option[CfExp[String]], RedirectAllRequestsTo: Option[CfExp[RedirectAllRequestsTo]])

    case class S3KeyFilter(Rules: CfExp[List[FilterRule]])

    case class RoutingRule(RoutingRuleCondition: Option[CfExp[RoutingRuleCondition]], RedirectRule: CfExp[RedirectRule])

    case class LambdaConfiguration(Filter: Option[CfExp[NotificationFilter]], Function: CfExp[String], Event: CfExp[String])

    case class ReplicationDestination(StorageClass: Option[CfExp[String]], Bucket: CfExp[String])

    case class LoggingConfiguration(DestinationBucketName: Option[CfExp[String]], LogFilePrefix: Option[CfExp[String]])

    case class ReplicationRule(Prefix: CfExp[String], Destination: CfExp[ReplicationDestination], Id: Option[CfExp[String]], Status: CfExp[String])

    case class ReplicationConfiguration(Rules: CfExp[List[ReplicationRule]], Role: CfExp[String])

    case class RedirectAllRequestsTo(Protocol: Option[CfExp[String]], HostName: CfExp[String])

    case class NotificationFilter(S3Key: CfExp[S3KeyFilter])

    case class LifecycleConfiguration(Rules: CfExp[List[Rule]])

    case class Rule(Transition: Option[CfExp[Transition]], Transitions: Option[CfExp[Transition]], Id: Option[CfExp[String]], NoncurrentVersionTransitions: Option[CfExp[NoncurrentVersionTransition]], NoncurrentVersionExpirationInDays: Option[CfExp[Int]], ExpirationDate: Option[CfExp[java.time.ZonedDateTime]], NoncurrentVersionTransition: Option[CfExp[NoncurrentVersionTransition]], ExpirationInDays: Option[CfExp[Int]], Prefix: Option[CfExp[String]], Status: CfExp[String])

    case class QueueConfiguration(Queue: CfExp[String], Filter: Option[CfExp[NotificationFilter]], Event: CfExp[String])

    case class TopicConfiguration(Topic: CfExp[String], Filter: Option[CfExp[NotificationFilter]], Event: CfExp[String])

    case class RoutingRuleCondition(KeyPrefixEquals: Option[CfExp[String]], HttpErrorCodeReturnedEquals: Option[CfExp[String]])

    case class NotificationConfiguration(LambdaConfigurations: Option[CfExp[List[LambdaConfiguration]]], QueueConfigurations: Option[CfExp[List[QueueConfiguration]]], TopicConfigurations: Option[CfExp[List[TopicConfiguration]]])

    case class Transition(TransitionInDays: Option[CfExp[Int]], StorageClass: CfExp[String], TransitionDate: Option[CfExp[java.time.ZonedDateTime]])

    case class RedirectRule(ReplaceKeyWith: Option[CfExp[String]], HttpRedirectCode: Option[CfExp[String]], HostName: Option[CfExp[String]], ReplaceKeyPrefixWith: Option[CfExp[String]], Protocol: Option[CfExp[String]])

    case class CorsRule(Id: Option[CfExp[String]], ExposedHeaders: Option[CfExp[List[String]]], AllowedHeaders: Option[CfExp[List[String]]], AllowedMethods: CfExp[List[String]], MaxAge: Option[CfExp[Int]], AllowedOrigins: CfExp[List[String]])

    case class NoncurrentVersionTransition(TransitionInDays: CfExp[Int], StorageClass: CfExp[String])

    case class VersioningConfiguration(Status: CfExp[String])

    case class CorsConfiguration(CorsRules: CfExp[List[CorsRule]])

    implicit val generic = LabelledGeneric[AWSS3Bucket]
  }

  object AWSWAFByteMatchSet {

    case class ByteMatchTuple(PositionalConstraint: CfExp[String], TextTransformation: CfExp[String], TargetStringBase64: Option[CfExp[String]], FieldToMatch: CfExp[FieldToMatch], TargetString: Option[CfExp[String]])

    case class FieldToMatch(Type: CfExp[String], Data: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSWAFByteMatchSet]
  }

  object AWSElasticBeanstalkEnvironment {

    case class OptionSettings(Value: CfExp[String], Namespace: CfExp[String], OptionName: CfExp[String])

    case class Tier(Type: Option[CfExp[String]], Version: Option[CfExp[String]], Name: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSElasticBeanstalkEnvironment]
  }

  object AWSEventsRule {

    case class Target(Arn: CfExp[String], InputPath: Option[CfExp[String]], Id: CfExp[String], Input: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSEventsRule]
  }

  object AWSIoTThing {

    case class AttributePayload(Attributes: Option[CfExp[Map[String, String]]])

    implicit val generic = LabelledGeneric[AWSIoTThing]
  }

  object AWSRoute53RecordSet {

    case class GeoLocation(ContinentCode: Option[CfExp[String]], SubdivisionCode: Option[CfExp[String]], CountryCode: Option[CfExp[String]])

    case class AliasTarget(EvaluateTargetHealth: Option[CfExp[Boolean]], DNSName: CfExp[String], HostedZoneId: CfExp[String])

    implicit val generic = LabelledGeneric[AWSRoute53RecordSet]
  }

  object AWSIoTTopicRule {

    case class TopicRulePayload(Sql: CfExp[String], Description: Option[CfExp[String]], RuleDisabled: CfExp[Boolean], AwsIotSqlVersion: Option[CfExp[String]], Actions: CfExp[List[Action]])

    case class SqsAction(QueueUrl: CfExp[String], RoleArn: CfExp[String], UseBase64: Option[CfExp[Boolean]])

    case class ElasticsearchAction(Id: CfExp[String], Endpoint: CfExp[String], RoleArn: CfExp[String], Index: CfExp[String], Type: CfExp[String])

    case class Action(Lambda: Option[CfExp[LambdaAction]], Republish: Option[CfExp[RepublishAction]], Sns: Option[CfExp[SnsAction]], Kinesis: Option[CfExp[KinesisAction]], DynamoDB: Option[CfExp[DynamoDBAction]], S3: Option[CfExp[S3Action]], Firehose: Option[CfExp[FirehoseAction]], Sqs: Option[CfExp[SqsAction]], CloudwatchAlarm: Option[CfExp[CloudwatchAlarmAction]], CloudwatchMetric: Option[CfExp[CloudwatchMetricAction]], Elasticsearch: Option[CfExp[ElasticsearchAction]])

    case class S3Action(BucketName: CfExp[String], RoleArn: CfExp[String], Key: CfExp[String])

    case class FirehoseAction(DeliveryStreamName: CfExp[String], Separator: Option[CfExp[String]], RoleArn: CfExp[String])

    case class CloudwatchAlarmAction(StateReason: CfExp[String], AlarmName: CfExp[String], StateValue: CfExp[String], RoleArn: CfExp[String])

    case class LambdaAction(FunctionArn: CfExp[String])

    case class RepublishAction(Topic: CfExp[String], RoleArn: CfExp[String])

    case class SnsAction(MessageFormat: Option[CfExp[String]], RoleArn: CfExp[String], TargetArn: CfExp[String])

    case class DynamoDBAction(RangeKeyValue: CfExp[String], RangeKeyField: CfExp[String], PayloadField: Option[CfExp[String]], HashKeyValue: CfExp[String], RoleArn: CfExp[String], TableName: CfExp[String], HashKeyField: CfExp[String])

    case class KinesisAction(PartitionKey: Option[CfExp[String]], StreamName: CfExp[String], RoleArn: CfExp[String])

    case class CloudwatchMetricAction(MetricValue: CfExp[String], MetricUnit: CfExp[String], MetricNamespace: CfExp[String], RoleArn: CfExp[String], MetricTimestamp: Option[CfExp[String]], MetricName: CfExp[String])

    implicit val generic = LabelledGeneric[AWSIoTTopicRule]
  }

  object AWSCodeBuildProject {

    case class SourceAuth(Type: Option[CfExp[String]], Resource: Option[CfExp[String]])

    case class Artifacts(Location: Option[CfExp[String]], Name: Option[CfExp[String]], Path: Option[CfExp[String]], NamespaceType: Option[CfExp[String]], Packaging: Option[CfExp[String]], Type: Option[CfExp[String]])

    case class Environment(Image: Option[CfExp[String]], Type: Option[CfExp[String]], ComputeType: Option[CfExp[String]], EnvironmentVariables: Option[CfExp[List[EnvironmentVariable]]])

    case class EnvironmentVariable(Value: Option[CfExp[String]], Name: Option[CfExp[String]])

    case class Source(Auth: Option[CfExp[SourceAuth]], BuildSpec: Option[CfExp[String]], Type: Option[CfExp[String]], Location: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSCodeBuildProject]
  }

  object AWSWAFXssMatchSet {

    case class XssMatchTuple(FieldToMatch: CfExp[FieldToMatch], TextTransformation: CfExp[String])

    case class FieldToMatch(Type: CfExp[String], Data: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSWAFXssMatchSet]
  }

  object AWSConfigConfigRule {

    case class Scope(TagValue: Option[CfExp[String]], ComplianceResourceTypes: Option[CfExp[List[String]]], ComplianceResourceId: Option[CfExp[String]], TagKey: Option[CfExp[String]])

    case class Source(SourceDetails: Option[CfExp[List[SourceDetail]]], SourceIdentifier: CfExp[String], Owner: CfExp[String])

    case class SourceDetail(EventSource: CfExp[String], MessageType: CfExp[String])

    implicit val generic = LabelledGeneric[AWSConfigConfigRule]
  }

  object AWSWAFIPSet {

    case class IPSetDescriptor(Value: CfExp[String], Type: CfExp[String])

    implicit val generic = LabelledGeneric[AWSWAFIPSet]
  }

  object AWSCodePipelinePipeline {

    case class StageTransition(StageName: CfExp[String], Reason: CfExp[String])

    case class BlockerDeclaration(Type: CfExp[String], Name: CfExp[String])

    case class ActionDeclaration(InputArtifacts: Option[CfExp[List[InputArtifact]]], Name: CfExp[String], RunOrder: Option[CfExp[Int]], Configuration: Option[CfExp[io.circe.Json]], ActionTypeId: CfExp[ActionTypeId], OutputArtifacts: Option[CfExp[List[OutputArtifact]]], RoleArn: Option[CfExp[String]])

    case class InputArtifact(Name: CfExp[String])

    case class StageDeclaration(Actions: CfExp[List[ActionDeclaration]], Name: CfExp[String], Blockers: Option[CfExp[List[BlockerDeclaration]]])

    case class OutputArtifact(Name: CfExp[String])

    case class ActionTypeId(Provider: CfExp[String], Version: CfExp[String], Owner: CfExp[String], Category: CfExp[String])

    case class EncryptionKey(Type: CfExp[String], Id: CfExp[String])

    case class ArtifactStore(Type: CfExp[String], Location: CfExp[String], EncryptionKey: Option[CfExp[EncryptionKey]])

    implicit val generic = LabelledGeneric[AWSCodePipelinePipeline]
  }

  object AWSElasticLoadBalancingV2Listener {

    case class Action(TargetGroupArn: CfExp[String], Type: CfExp[String])

    case class Certificate(CertificateArn: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSElasticLoadBalancingV2Listener]
  }

  object AWSApiGatewayMethod {

    case class MethodResponse(StatusCode: Option[CfExp[String]], ResponseParameters: Option[CfExp[Map[String, Boolean]]], ResponseModels: Option[CfExp[Map[String, String]]])

    case class Integration(RequestTemplates: Option[CfExp[Map[String, String]]], PassthroughBehavior: Option[CfExp[String]], CacheNamespace: Option[CfExp[String]], RequestParameters: Option[CfExp[Map[String, String]]], IntegrationHttpMethod: Option[CfExp[String]], Uri: Option[CfExp[String]], CacheKeyParameters: Option[CfExp[List[String]]], Credentials: Option[CfExp[String]], IntegrationResponses: Option[CfExp[List[IntegrationResponse]]], Type: Option[CfExp[String]])

    case class IntegrationResponse(SelectionPattern: Option[CfExp[String]], StatusCode: Option[CfExp[String]], ResponseTemplates: Option[CfExp[Map[String, String]]], ResponseParameters: Option[CfExp[Map[String, String]]])

    implicit val generic = LabelledGeneric[AWSApiGatewayMethod]
  }

  object AWSApiGatewayStage {

    case class MethodSetting(CachingEnabled: Option[CfExp[Boolean]], ResourcePath: Option[CfExp[String]], LoggingLevel: Option[CfExp[String]], CacheTtlInSeconds: Option[CfExp[Int]], MetricsEnabled: Option[CfExp[Boolean]], ThrottlingRateLimit: Option[CfExp[Double]], DataTraceEnabled: Option[CfExp[Boolean]], CacheDataEncrypted: Option[CfExp[Boolean]], HttpMethod: Option[CfExp[String]], ThrottlingBurstLimit: Option[CfExp[Int]])

    implicit val generic = LabelledGeneric[AWSApiGatewayStage]
  }

  object AWSAutoScalingScalingPolicy {

    case class StepAdjustment(MetricIntervalUpperBound: Option[CfExp[Double]], MetricIntervalLowerBound: Option[CfExp[Double]], ScalingAdjustment: CfExp[Int])

    implicit val generic = LabelledGeneric[AWSAutoScalingScalingPolicy]
  }

  object AWSGameLiftAlias {

    case class RoutingStrategy(Message: Option[CfExp[String]], Type: CfExp[String], FleetId: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSGameLiftAlias]
  }

  object AWSCodeCommitRepository {

    case class RepositoryTrigger(Name: Option[CfExp[String]], DestinationArn: Option[CfExp[String]], CustomData: Option[CfExp[String]], Events: Option[CfExp[List[String]]], Branches: Option[CfExp[List[String]]])

    implicit val generic = LabelledGeneric[AWSCodeCommitRepository]
  }

  object AWSApiGatewayUsagePlan {

    case class QuotaSettings(Limit: Option[CfExp[Int]], Period: Option[CfExp[String]], Offset: Option[CfExp[Int]])

    case class ThrottleSettings(BurstLimit: Option[CfExp[Int]], RateLimit: Option[CfExp[Double]])

    case class ApiStage(ApiId: Option[CfExp[String]], Stage: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSApiGatewayUsagePlan]
  }

  object AWSRoute53RecordSetGroup {

    case class AliasTarget(EvaluateTargetHealth: Option[CfExp[Boolean]], DNSName: CfExp[String], HostedZoneId: CfExp[String])

    case class GeoLocation(ContinentCode: Option[CfExp[String]], SubdivisionCode: Option[CfExp[String]], CountryCode: Option[CfExp[String]])

    case class RecordSet(Name: CfExp[String], AliasTarget: Option[CfExp[AliasTarget]], HealthCheckId: Option[CfExp[String]], Region: Option[CfExp[String]], HostedZoneName: Option[CfExp[String]], Weight: Option[CfExp[Int]], ResourceRecords: Option[CfExp[List[String]]], Failover: Option[CfExp[String]], GeoLocation: Option[CfExp[GeoLocation]], SetIdentifier: Option[CfExp[String]], TTL: Option[CfExp[String]], Comment: Option[CfExp[String]], HostedZoneId: Option[CfExp[String]], Type: CfExp[String])

    implicit val generic = LabelledGeneric[AWSRoute53RecordSetGroup]
  }

  object AWSEC2NetworkInterface {

    case class PrivateIpAddressSpecification(Primary: CfExp[Boolean], PrivateIpAddress: CfExp[String])

    case class InstanceIpv6Address(Ipv6Address: CfExp[String])

    implicit val generic = LabelledGeneric[AWSEC2NetworkInterface]
  }

  object AWSEC2Instance {

    case class Ebs(VolumeType: Option[CfExp[String]], SnapshotId: Option[CfExp[String]], Encrypted: Option[CfExp[Boolean]], VolumeSize: Option[CfExp[Int]], Iops: Option[CfExp[Int]], DeleteOnTermination: Option[CfExp[Boolean]])

    case class Volume(VolumeId: CfExp[String], Device: CfExp[String])

    case class NetworkInterface(Ipv6Addresses: Option[CfExp[List[InstanceIpv6Address]]], PrivateIpAddress: Option[CfExp[String]], Description: Option[CfExp[String]], PrivateIpAddresses: Option[CfExp[List[PrivateIpAddressSpecification]]], Ipv6AddressCount: Option[CfExp[Int]], DeviceIndex: CfExp[String], DeleteOnTermination: Option[CfExp[Boolean]], AssociatePublicIpAddress: Option[CfExp[Boolean]], NetworkInterfaceId: Option[CfExp[String]], GroupSet: Option[CfExp[List[String]]], SecondaryPrivateIpAddressCount: Option[CfExp[Int]], SubnetId: Option[CfExp[String]])

    case class AssociationParameter(Value: CfExp[List[String]], Key: CfExp[String])

    case class NoDevice()

    case class InstanceIpv6Address(Ipv6Address: CfExp[String])

    case class SsmAssociation(DocumentName: CfExp[String], AssociationParameters: Option[CfExp[List[AssociationParameter]]])

    case class BlockDeviceMapping(Ebs: Option[CfExp[Ebs]], DeviceName: CfExp[String], VirtualName: Option[CfExp[String]], NoDevice: Option[CfExp[NoDevice]])

    case class PrivateIpAddressSpecification(Primary: CfExp[Boolean], PrivateIpAddress: CfExp[String])

    implicit val generic = LabelledGeneric[AWSEC2Instance]
  }

  object AWSElasticLoadBalancingLoadBalancer {

    case class ConnectionSettings(IdleTimeout: CfExp[Int])

    case class Policies(PolicyType: CfExp[String], LoadBalancerPorts: Option[CfExp[List[String]]], Attributes: CfExp[List[io.circe.Json]], InstancePorts: Option[CfExp[List[String]]], PolicyName: CfExp[String])

    case class AppCookieStickinessPolicy(CookieName: CfExp[String], PolicyName: CfExp[String])

    case class HealthCheck(Target: CfExp[String], UnhealthyThreshold: CfExp[String], HealthyThreshold: CfExp[String], Interval: CfExp[String], Timeout: CfExp[String])

    case class Listeners(InstancePort: CfExp[String], PolicyNames: Option[CfExp[List[String]]], LoadBalancerPort: CfExp[String], SSLCertificateId: Option[CfExp[String]], Protocol: CfExp[String], InstanceProtocol: Option[CfExp[String]])

    case class AccessLoggingPolicy(Enabled: CfExp[Boolean], S3BucketPrefix: Option[CfExp[String]], S3BucketName: CfExp[String], EmitInterval: Option[CfExp[Int]])

    case class LBCookieStickinessPolicy(CookieExpirationPeriod: Option[CfExp[String]], PolicyName: Option[CfExp[String]])

    case class ConnectionDrainingPolicy(Enabled: CfExp[Boolean], Timeout: Option[CfExp[Int]])

    implicit val generic = LabelledGeneric[AWSElasticLoadBalancingLoadBalancer]
  }

  object AWSGameLiftFleet {

    case class IpPermission(Protocol: CfExp[String], IpRange: CfExp[String], FromPort: CfExp[Int], ToPort: CfExp[Int])

    implicit val generic = LabelledGeneric[AWSGameLiftFleet]
  }

  object AWSCloudWatchAlarm {

    case class Dimension(Value: CfExp[String], Name: CfExp[String])

    implicit val generic = LabelledGeneric[AWSCloudWatchAlarm]
  }

  object AWSECSService {

    case class DeploymentConfiguration(MinimumHealthyPercent: Option[CfExp[Int]], MaximumPercent: Option[CfExp[Int]])

    case class LoadBalancer(ContainerName: Option[CfExp[String]], TargetGroupArn: Option[CfExp[String]], ContainerPort: CfExp[Int], LoadBalancerName: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSECSService]
  }

  object AWSWAFWebACL {

    case class ActivatedRule(Action: CfExp[WafAction], Priority: CfExp[Int], RuleId: CfExp[String])

    case class WafAction(Type: CfExp[String])

    implicit val generic = LabelledGeneric[AWSWAFWebACL]
  }

  object AWSElasticLoadBalancingV2ListenerRule {

    case class RuleCondition(Values: Option[CfExp[List[String]]], Field: Option[CfExp[String]])

    case class Action(TargetGroupArn: CfExp[String], Type: CfExp[String])

    implicit val generic = LabelledGeneric[AWSElasticLoadBalancingV2ListenerRule]
  }

  object AWSWAFSqlInjectionMatchSet {

    case class FieldToMatch(Type: CfExp[String], Data: Option[CfExp[String]])

    case class SqlInjectionMatchTuple(FieldToMatch: CfExp[FieldToMatch], TextTransformation: CfExp[String])

    implicit val generic = LabelledGeneric[AWSWAFSqlInjectionMatchSet]
  }

  object AWSCodeDeployDeploymentGroup {

    case class Revision(GitHubLocation: Option[CfExp[GitHubLocation]], RevisionType: Option[CfExp[String]], S3Location: Option[CfExp[S3Location]])

    case class Deployment(IgnoreApplicationStopFailures: Option[CfExp[Boolean]], Revision: CfExp[Revision], Description: Option[CfExp[String]])

    case class S3Location(Bucket: CfExp[String], Version: Option[CfExp[String]], BundleType: CfExp[String], ETag: Option[CfExp[String]], Key: CfExp[String])

    case class OnPremisesInstanceTagFilter(Value: Option[CfExp[String]], Type: Option[CfExp[String]], Key: Option[CfExp[String]])

    case class GitHubLocation(Repository: CfExp[String], CommitId: CfExp[String])

    case class Ec2TagFilter(Value: Option[CfExp[String]], Type: CfExp[String], Key: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSCodeDeployDeploymentGroup]
  }

  object AWSConfigConfigurationRecorder {

    case class RecordingGroup(ResourceTypes: Option[CfExp[List[String]]], IncludeGlobalResourceTypes: Option[CfExp[Boolean]], AllSupported: Option[CfExp[Boolean]])

    implicit val generic = LabelledGeneric[AWSConfigConfigurationRecorder]
  }

  object AWSAutoScalingAutoScalingGroup {

    case class TagProperty(Value: CfExp[String], PropagateAtLaunch: CfExp[Boolean], Key: CfExp[String])

    case class MetricsCollection(Metrics: Option[CfExp[List[String]]], Granularity: CfExp[String])

    case class NotificationConfigurations(TopicARN: CfExp[String], NotificationTypes: Option[CfExp[List[String]]])

    implicit val generic = LabelledGeneric[AWSAutoScalingAutoScalingGroup]
  }

  object AWSLambdaFunction {

    case class Environment(Variables: Option[CfExp[Map[String, String]]])

    case class Code(S3Key: Option[CfExp[String]], ZipFile: Option[CfExp[String]], S3Bucket: Option[CfExp[String]], S3ObjectVersion: Option[CfExp[String]])

    case class VpcConfig(SubnetIds: CfExp[List[String]], SecurityGroupIds: CfExp[List[String]])

    implicit val generic = LabelledGeneric[AWSLambdaFunction]
  }

  object AWSEMRCluster {

    case class JobFlowInstancesConfig(CoreInstanceGroup: CfExp[InstanceGroupConfig], TerminationProtected: Option[CfExp[Boolean]], AdditionalSlaveSecurityGroups: Option[CfExp[List[String]]], Placement: Option[CfExp[PlacementType]], EmrManagedMasterSecurityGroup: Option[CfExp[String]], MasterInstanceGroup: CfExp[InstanceGroupConfig], EmrManagedSlaveSecurityGroup: Option[CfExp[String]], Ec2KeyName: Option[CfExp[String]], ServiceAccessSecurityGroup: Option[CfExp[String]], Ec2SubnetId: Option[CfExp[String]], AdditionalMasterSecurityGroups: Option[CfExp[List[String]]], HadoopVersion: Option[CfExp[String]])

    case class ScriptBootstrapActionConfig(Args: Option[CfExp[List[String]]], Path: CfExp[String])

    case class PlacementType(AvailabilityZone: CfExp[String])

    case class EbsConfiguration(EbsOptimized: Option[CfExp[Boolean]], EbsBlockDeviceConfigs: Option[CfExp[List[EbsBlockDeviceConfig]]])

    case class EbsBlockDeviceConfig(VolumesPerInstance: Option[CfExp[Int]], VolumeSpecification: CfExp[VolumeSpecification])

    case class VolumeSpecification(VolumeType: CfExp[String], SizeInGB: CfExp[Int], Iops: Option[CfExp[Int]])

    case class Configuration(Classification: Option[CfExp[String]], Configurations: Option[CfExp[List[Configuration]]], ConfigurationProperties: Option[CfExp[Map[String, String]]])

    case class Application(Args: Option[CfExp[List[String]]], Version: Option[CfExp[String]], AdditionalInfo: Option[CfExp[Map[String, String]]], Name: Option[CfExp[String]])

    case class BootstrapActionConfig(ScriptBootstrapAction: CfExp[ScriptBootstrapActionConfig], Name: CfExp[String])

    case class InstanceGroupConfig(EbsConfiguration: Option[CfExp[EbsConfiguration]], Name: Option[CfExp[String]], InstanceCount: CfExp[Int], Configurations: Option[CfExp[List[Configuration]]], BidPrice: Option[CfExp[String]], Market: Option[CfExp[String]], InstanceType: CfExp[String])

    implicit val generic = LabelledGeneric[AWSEMRCluster]
  }

  object AWSWAFSizeConstraintSet {

    case class FieldToMatch(Type: CfExp[String], Data: Option[CfExp[String]])

    case class SizeConstraint(ComparisonOperator: CfExp[String], FieldToMatch: CfExp[FieldToMatch], TextTransformation: CfExp[String], Size: CfExp[Int])

    implicit val generic = LabelledGeneric[AWSWAFSizeConstraintSet]
  }

  object AWSEC2SpotFleet {

    case class SecurityGroups(GroupId: Option[CfExp[String]])

    case class InstanceIpv6Address(Ipv6Address: CfExp[String])

    case class NetworkInterfaces(Ipv6Addresses: Option[CfExp[InstanceIpv6Address]], Description: Option[CfExp[String]], PrivateIpAddresses: Option[CfExp[List[PrivateIpAddresses]]], Ipv6AddressCount: Option[CfExp[Int]], Groups: Option[CfExp[List[String]]], DeviceIndex: CfExp[Int], DeleteOnTermination: Option[CfExp[Boolean]], AssociatePublicIpAddress: Option[CfExp[Boolean]], NetworkInterfaceId: Option[CfExp[String]], SecondaryPrivateIpAddressCount: Option[CfExp[Int]], SubnetId: Option[CfExp[String]])

    case class IamInstanceProfile(Arn: Option[CfExp[String]])

    case class LaunchSpecifications(NetworkInterfaces: Option[CfExp[List[NetworkInterfaces]]], RamdiskId: Option[CfExp[String]], BlockDeviceMappings: Option[CfExp[List[BlockDeviceMappings]]], ImageId: CfExp[String], WeightedCapacity: Option[CfExp[Double]], EbsOptimized: Option[CfExp[Boolean]], Placement: Option[CfExp[Placement]], UserData: Option[CfExp[String]], SecurityGroups: Option[CfExp[List[SecurityGroups]]], KernelId: Option[CfExp[String]], KeyName: Option[CfExp[String]], InstanceType: CfExp[String], Monitoring: Option[CfExp[Monitoring]], SpotPrice: Option[CfExp[String]], IamInstanceProfile: Option[CfExp[IamInstanceProfile]], SubnetId: Option[CfExp[String]])

    case class SpotFleetRequestConfigData(ExcessCapacityTerminationPolicy: Option[CfExp[String]], IamFleetRole: CfExp[String], LaunchSpecifications: CfExp[List[LaunchSpecifications]], ValidUntil: Option[CfExp[String]], TerminateInstancesWithExpiration: Option[CfExp[Boolean]], AllocationStrategy: Option[CfExp[String]], ValidFrom: Option[CfExp[String]], SpotPrice: CfExp[String], TargetCapacity: CfExp[Int])

    case class BlockDeviceMappings(Ebs: Option[CfExp[Ebs]], DeviceName: CfExp[String], VirtualName: Option[CfExp[String]], NoDevice: Option[CfExp[Boolean]])

    case class Monitoring(Enabled: Option[CfExp[Boolean]])

    case class PrivateIpAddresses(Primary: Option[CfExp[Boolean]], PrivateIpAddress: CfExp[String])

    case class Ebs(VolumeType: Option[CfExp[String]], SnapshotId: Option[CfExp[String]], Encrypted: Option[CfExp[Boolean]], VolumeSize: Option[CfExp[Int]], Iops: Option[CfExp[Int]], DeleteOnTermination: Option[CfExp[Boolean]])

    case class Placement(GroupName: Option[CfExp[String]], AvailabilityZone: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSEC2SpotFleet]
  }

  object AWSEMRInstanceGroupConfig {

    case class EbsConfiguration(EbsOptimized: Option[CfExp[Boolean]], EbsBlockDeviceConfigs: Option[CfExp[List[EbsBlockDeviceConfig]]])

    case class VolumeSpecification(VolumeType: CfExp[String], SizeInGB: CfExp[Int], Iops: Option[CfExp[Int]])

    case class EbsBlockDeviceConfig(VolumesPerInstance: Option[CfExp[Int]], VolumeSpecification: CfExp[VolumeSpecification])

    case class Configuration(Classification: Option[CfExp[String]], Configurations: Option[CfExp[List[Configuration]]], ConfigurationProperties: Option[CfExp[Map[String, String]]])

    implicit val generic = LabelledGeneric[AWSEMRInstanceGroupConfig]
  }

  object AWSEC2SecurityGroup {

    case class Rule(CidrIp: Option[CfExp[String]], SourceSecurityGroupName: Option[CfExp[String]], ToPort: Option[CfExp[Int]], FromPort: Option[CfExp[Int]], IpProtocol: CfExp[String], SourceSecurityGroupId: Option[CfExp[String]], SourceSecurityGroupOwnerId: Option[CfExp[String]])

    implicit val generic = LabelledGeneric[AWSEC2SecurityGroup]
  }

  object AWSDirectoryServiceSimpleAD {

    case class VpcSettings(VpcId: CfExp[String], SubnetIds: CfExp[List[String]])

    implicit val generic = LabelledGeneric[AWSDirectoryServiceSimpleAD]
  }

  case class AWSEC2VPCGatewayAttachment(logicalId: String, VpcId: CfExp[String], InternetGatewayId: Option[CfExp[String]], VpnGatewayId: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::EC2::VPCGatewayAttachment"
  }

  case class AWSOpsWorksElasticLoadBalancerAttachment(logicalId: String, ElasticLoadBalancerName: CfExp[String], LayerId: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::OpsWorks::ElasticLoadBalancerAttachment"
  }

  case class AWSIAMUserToGroupAddition(logicalId: String, GroupName: CfExp[String], Users: CfExp[List[String]]) extends Resource {
    override def fqn: String = "AWS::IAM::UserToGroupAddition"
  }

  case class AWSIAMUser(logicalId: String, UserName: Option[CfExp[String]], Path: Option[CfExp[String]], Policies: Option[CfExp[List[AWSIAMUser.Policy]]], Groups: Option[CfExp[List[String]]], LoginProfile: Option[CfExp[AWSIAMUser.LoginProfile]], ManagedPolicyArns: Option[CfExp[List[String]]]) extends Resource {
    override def fqn: String = "AWS::IAM::User"
  }

  case class AWSOpsWorksUserProfile(logicalId: String, AllowSelfManagement: Option[CfExp[Boolean]], IamUserArn: CfExp[String], SshPublicKey: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::OpsWorks::UserProfile"
  }

  case class AWSLogsLogStream(logicalId: String, LogStreamName: Option[CfExp[String]], LogGroupName: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::Logs::LogStream"
  }

  case class AWSElasticsearchDomain(logicalId: String, AccessPolicies: Option[CfExp[io.circe.Json]], ElasticsearchClusterConfig: Option[CfExp[AWSElasticsearchDomain.ElasticsearchClusterConfig]], AdvancedOptions: Option[CfExp[Map[String, String]]], DomainName: Option[CfExp[String]], EBSOptions: Option[CfExp[AWSElasticsearchDomain.EBSOptions]], Tags: Option[CfExp[List[Tag]]], SnapshotOptions: Option[CfExp[AWSElasticsearchDomain.SnapshotOptions]], ElasticsearchVersion: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::Elasticsearch::Domain"
  }

  case class AWSRedshiftClusterSecurityGroupIngress(logicalId: String, EC2SecurityGroupOwnerId: Option[CfExp[String]], ClusterSecurityGroupName: CfExp[String], CIDRIP: Option[CfExp[String]], EC2SecurityGroupName: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::Redshift::ClusterSecurityGroupIngress"
  }

  case class AWSRDSOptionGroup(logicalId: String, MajorEngineVersion: CfExp[String], EngineName: CfExp[String], Tags: Option[CfExp[List[Tag]]], OptionConfigurations: CfExp[List[AWSRDSOptionGroup.OptionConfiguration]], OptionGroupDescription: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::RDS::OptionGroup"
  }

  case class AWSCloudFormationCustomResource(logicalId: String, ServiceToken: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::CloudFormation::CustomResource"
  }

  case class AWSOpsWorksStack(logicalId: String, DefaultSubnetId: Option[CfExp[String]], DefaultSshKeyName: Option[CfExp[String]], HostnameTheme: Option[CfExp[String]], Name: CfExp[String], DefaultRootDeviceType: Option[CfExp[String]], CloneAppIds: Option[CfExp[List[String]]], UseCustomCookbooks: Option[CfExp[Boolean]], Attributes: Option[CfExp[Map[String, String]]], ClonePermissions: Option[CfExp[Boolean]], CustomCookbooksSource: Option[CfExp[AWSOpsWorksStack.Source]], RdsDbInstances: Option[CfExp[List[AWSOpsWorksStack.RdsDbInstance]]], AgentVersion: Option[CfExp[String]], CustomJson: Option[CfExp[io.circe.Json]], DefaultInstanceProfileArn: CfExp[String], ServiceRoleArn: CfExp[String], ElasticIps: Option[CfExp[List[AWSOpsWorksStack.ElasticIp]]], SourceStackId: Option[CfExp[String]], EcsClusterArn: Option[CfExp[String]], DefaultOs: Option[CfExp[String]], ChefConfiguration: Option[CfExp[AWSOpsWorksStack.ChefConfiguration]], UseOpsworksSecurityGroups: Option[CfExp[Boolean]], ConfigurationManager: Option[CfExp[AWSOpsWorksStack.StackConfigurationManager]], DefaultAvailabilityZone: Option[CfExp[String]], VpcId: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::OpsWorks::Stack"
  }

  case class AWSRoute53HealthCheck(logicalId: String, HealthCheckConfig: CfExp[AWSRoute53HealthCheck.HealthCheckConfig], HealthCheckTags: Option[CfExp[List[AWSRoute53HealthCheck.HealthCheckTag]]]) extends Resource {
    override def fqn: String = "AWS::Route53::HealthCheck"
  }

  case class AWSEC2InternetGateway(logicalId: String, Tags: Option[CfExp[List[Tag]]]) extends Resource {
    override def fqn: String = "AWS::EC2::InternetGateway"
  }

  case class AWSCodeDeployDeploymentConfig(logicalId: String, DeploymentConfigName: Option[CfExp[String]], MinimumHealthyHosts: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::CodeDeploy::DeploymentConfig"
  }

  case class AWSCloudTrailTrail(logicalId: String, CloudWatchLogsRoleArn: Option[CfExp[String]], KMSKeyId: Option[CfExp[String]], CloudWatchLogsLogGroupArn: Option[CfExp[String]], IsLogging: CfExp[Boolean], IsMultiRegionTrail: Option[CfExp[Boolean]], S3BucketName: CfExp[String], SnsTopicName: Option[CfExp[String]], S3KeyPrefix: Option[CfExp[String]], EnableLogFileValidation: Option[CfExp[Boolean]], IncludeGlobalServiceEvents: Option[CfExp[Boolean]]) extends Resource {
    override def fqn: String = "AWS::CloudTrail::Trail"
  }

  case class AWSEC2VPC(logicalId: String, EnableDnsSupport: Option[CfExp[Boolean]], CidrBlock: CfExp[String], Tags: Option[CfExp[List[Tag]]], EnableDnsHostnames: Option[CfExp[Boolean]], InstanceTenancy: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::EC2::VPC"
  }

  case class AWSEFSFileSystem(logicalId: String, PerformanceMode: Option[CfExp[String]], FileSystemTags: Option[CfExp[List[AWSEFSFileSystem.ElasticFileSystemTag]]]) extends Resource {
    override def fqn: String = "AWS::EFS::FileSystem"
  }

  case class AWSConfigDeliveryChannel(logicalId: String, ConfigSnapshotDeliveryProperties: Option[CfExp[AWSConfigDeliveryChannel.ConfigSnapshotDeliveryProperties]], Name: Option[CfExp[String]], SnsTopicARN: Option[CfExp[String]], S3BucketName: CfExp[String], S3KeyPrefix: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::Config::DeliveryChannel"
  }

  case class AWSEC2RouteTable(logicalId: String, VpcId: CfExp[String], Tags: Option[CfExp[List[Tag]]]) extends Resource {
    override def fqn: String = "AWS::EC2::RouteTable"
  }

  case class AWSApiGatewayDeployment(logicalId: String, StageName: Option[CfExp[String]], RestApiId: CfExp[String], Description: Option[CfExp[String]], StageDescription: Option[CfExp[AWSApiGatewayDeployment.StageDescription]]) extends Resource {
    override def fqn: String = "AWS::ApiGateway::Deployment"
  }

  case class AWSECSCluster(logicalId: String, ClusterName: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::ECS::Cluster"
  }

  case class AWSKinesisFirehoseDeliveryStream(logicalId: String, DeliveryStreamName: Option[CfExp[String]], ElasticsearchDestinationConfiguration: Option[CfExp[AWSKinesisFirehoseDeliveryStream.ElasticsearchDestinationConfiguration]], S3DestinationConfiguration: Option[CfExp[AWSKinesisFirehoseDeliveryStream.S3DestinationConfiguration]], RedshiftDestinationConfiguration: Option[CfExp[AWSKinesisFirehoseDeliveryStream.RedshiftDestinationConfiguration]]) extends Resource {
    override def fqn: String = "AWS::KinesisFirehose::DeliveryStream"
  }

  case class AWSIAMRole(logicalId: String, Path: Option[CfExp[String]], Policies: Option[CfExp[List[AWSIAMRole.Policy]]], AssumeRolePolicyDocument: CfExp[io.circe.Json], RoleName: Option[CfExp[String]], ManagedPolicyArns: Option[CfExp[List[String]]]) extends Resource {
    override def fqn: String = "AWS::IAM::Role"
  }

  case class AWSElasticBeanstalkApplicationVersion(logicalId: String, ApplicationName: CfExp[String], Description: Option[CfExp[String]], SourceBundle: CfExp[AWSElasticBeanstalkApplicationVersion.SourceBundle]) extends Resource {
    override def fqn: String = "AWS::ElasticBeanstalk::ApplicationVersion"
  }

  case class AWSEC2NatGateway(logicalId: String, AllocationId: CfExp[String], SubnetId: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EC2::NatGateway"
  }

  case class AWSElasticBeanstalkConfigurationTemplate(logicalId: String, Description: Option[CfExp[String]], EnvironmentId: Option[CfExp[String]], SourceConfiguration: Option[CfExp[AWSElasticBeanstalkConfigurationTemplate.SourceConfiguration]], SolutionStackName: Option[CfExp[String]], OptionSettings: Option[CfExp[List[AWSElasticBeanstalkConfigurationTemplate.ConfigurationOptionSetting]]], ApplicationName: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::ElasticBeanstalk::ConfigurationTemplate"
  }

  case class AWSRDSDBSubnetGroup(logicalId: String, Tags: Option[CfExp[List[Tag]]], SubnetIds: CfExp[List[String]], DBSubnetGroupDescription: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::RDS::DBSubnetGroup"
  }

  case class AWSRedshiftClusterParameterGroup(logicalId: String, ParameterGroupFamily: CfExp[String], Description: CfExp[String], Parameters: Option[CfExp[List[AWSRedshiftClusterParameterGroup.Parameter]]]) extends Resource {
    override def fqn: String = "AWS::Redshift::ClusterParameterGroup"
  }

  case class AWSRDSDBClusterParameterGroup(logicalId: String, Tags: Option[CfExp[List[Tag]]], Family: CfExp[String], Description: CfExp[String], Parameters: CfExp[io.circe.Json]) extends Resource {
    override def fqn: String = "AWS::RDS::DBClusterParameterGroup"
  }

  case class AWSECRRepository(logicalId: String, RepositoryName: Option[CfExp[String]], RepositoryPolicyText: Option[CfExp[io.circe.Json]]) extends Resource {
    override def fqn: String = "AWS::ECR::Repository"
  }

  case class AWSElastiCacheReplicationGroup(logicalId: String, PreferredMaintenanceWindow: Option[CfExp[String]], CacheParameterGroupName: Option[CfExp[String]], ReplicationGroupId: Option[CfExp[String]], Engine: Option[CfExp[String]], ReplicasPerNodeGroup: Option[CfExp[Int]], NumNodeGroups: Option[CfExp[Int]], CacheNodeType: Option[CfExp[String]], SnapshotName: Option[CfExp[String]], NodeGroupConfiguration: Option[CfExp[List[AWSElastiCacheReplicationGroup.NodeGroupConfiguration]]], SnapshotWindow: Option[CfExp[String]], NumCacheClusters: Option[CfExp[Int]], CacheSecurityGroupNames: Option[CfExp[List[String]]], AutomaticFailoverEnabled: Option[CfExp[Boolean]], PreferredCacheClusterAZs: Option[CfExp[List[String]]], AutoMinorVersionUpgrade: Option[CfExp[Boolean]], SecurityGroupIds: Option[CfExp[List[String]]], EngineVersion: Option[CfExp[String]], NotificationTopicArn: Option[CfExp[String]], Port: Option[CfExp[Int]], CacheSubnetGroupName: Option[CfExp[String]], PrimaryClusterId: Option[CfExp[String]], Tags: Option[CfExp[List[Tag]]], ReplicationGroupDescription: CfExp[String], SnapshotArns: Option[CfExp[List[String]]], SnapshotRetentionLimit: Option[CfExp[Int]], SnapshottingClusterId: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::ElastiCache::ReplicationGroup"
  }

  case class AWSOpsWorksInstance(logicalId: String, Architecture: Option[CfExp[String]], BlockDeviceMappings: Option[CfExp[List[AWSOpsWorksInstance.BlockDeviceMapping]]], EbsOptimized: Option[CfExp[Boolean]], TimeBasedAutoScaling: Option[CfExp[AWSOpsWorksInstance.TimeBasedAutoScaling]], Tenancy: Option[CfExp[String]], StackId: CfExp[String], AgentVersion: Option[CfExp[String]], AvailabilityZone: Option[CfExp[String]], Volumes: Option[CfExp[List[String]]], RootDeviceType: Option[CfExp[String]], InstanceType: CfExp[String], ElasticIps: Option[CfExp[List[String]]], Hostname: Option[CfExp[String]], Os: Option[CfExp[String]], VirtualizationType: Option[CfExp[String]], AmiId: Option[CfExp[String]], LayerIds: CfExp[List[String]], InstallUpdatesOnBoot: Option[CfExp[Boolean]], AutoScalingType: Option[CfExp[String]], SshKeyName: Option[CfExp[String]], SubnetId: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::OpsWorks::Instance"
  }

  case class AWSApplicationAutoScalingScalingPolicy(logicalId: String, PolicyType: CfExp[String], ResourceId: Option[CfExp[String]], StepScalingPolicyConfiguration: Option[CfExp[AWSApplicationAutoScalingScalingPolicy.StepScalingPolicyConfiguration]], PolicyName: CfExp[String], ScalableDimension: Option[CfExp[String]], ServiceNamespace: Option[CfExp[String]], ScalingTargetId: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::ApplicationAutoScaling::ScalingPolicy"
  }

  case class AWSIAMAccessKey(logicalId: String, Serial: Option[CfExp[Int]], UserName: CfExp[String], Status: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::IAM::AccessKey"
  }

  case class AWSCloudFormationWaitCondition(logicalId: String, Timeout: CfExp[String], Count: Option[CfExp[Int]], Handle: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::CloudFormation::WaitCondition"
  }

  case class AWSAutoScalingLaunchConfiguration(logicalId: String, BlockDeviceMappings: Option[CfExp[List[AWSAutoScalingLaunchConfiguration.BlockDeviceMapping]]], ClassicLinkVPCId: Option[CfExp[String]], ImageId: CfExp[String], EbsOptimized: Option[CfExp[Boolean]], RamDiskId: Option[CfExp[String]], UserData: Option[CfExp[String]], SecurityGroups: Option[CfExp[List[String]]], KernelId: Option[CfExp[String]], ClassicLinkVPCSecurityGroups: Option[CfExp[List[String]]], KeyName: Option[CfExp[String]], InstanceType: CfExp[String], InstanceId: Option[CfExp[String]], AssociatePublicIpAddress: Option[CfExp[Boolean]], SpotPrice: Option[CfExp[String]], PlacementTenancy: Option[CfExp[String]], IamInstanceProfile: Option[CfExp[String]], InstanceMonitoring: Option[CfExp[Boolean]]) extends Resource {
    override def fqn: String = "AWS::AutoScaling::LaunchConfiguration"
  }

  case class AWSEMRStep(logicalId: String, JobFlowId: CfExp[String], ActionOnFailure: CfExp[String], HadoopJarStep: CfExp[AWSEMRStep.HadoopJarStepConfig], Name: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EMR::Step"
  }

  case class AWSLambdaEventSourceMapping(logicalId: String, Enabled: Option[CfExp[Boolean]], FunctionName: CfExp[String], StartingPosition: CfExp[String], EventSourceArn: CfExp[String], BatchSize: Option[CfExp[Int]]) extends Resource {
    override def fqn: String = "AWS::Lambda::EventSourceMapping"
  }

  case class AWSApiGatewayApiKey(logicalId: String, StageKeys: Option[CfExp[List[AWSApiGatewayApiKey.StageKey]]], Enabled: Option[CfExp[Boolean]], Description: Option[CfExp[String]], Name: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::ApiGateway::ApiKey"
  }

  case class AWSEC2Subnet(logicalId: String, CidrBlock: CfExp[String], AvailabilityZone: Option[CfExp[String]], Tags: Option[CfExp[List[Tag]]], MapPublicIpOnLaunch: Option[CfExp[Boolean]], VpcId: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EC2::Subnet"
  }

  case class AWSCloudFrontDistribution(logicalId: String, DistributionConfig: CfExp[AWSCloudFrontDistribution.DistributionConfig]) extends Resource {
    override def fqn: String = "AWS::CloudFront::Distribution"
  }

  case class AWSApiGatewayModel(logicalId: String, Name: Option[CfExp[String]], RestApiId: CfExp[String], Description: Option[CfExp[String]], Schema: Option[CfExp[io.circe.Json]], ContentType: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::ApiGateway::Model"
  }

  case class AWSEC2Host(logicalId: String, AvailabilityZone: CfExp[String], InstanceType: CfExp[String], AutoPlacement: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::EC2::Host"
  }

  case class AWSRoute53HostedZone(logicalId: String, VPCs: Option[CfExp[List[AWSRoute53HostedZone.VPC]]], HostedZoneTags: Option[CfExp[List[AWSRoute53HostedZone.HostedZoneTag]]], HostedZoneConfig: Option[CfExp[AWSRoute53HostedZone.HostedZoneConfig]], Name: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::Route53::HostedZone"
  }

  case class AWSLambdaAlias(logicalId: String, FunctionVersion: CfExp[String], FunctionName: CfExp[String], Description: Option[CfExp[String]], Name: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::Lambda::Alias"
  }

  case class AWSDirectoryServiceMicrosoftAD(logicalId: String, Name: CfExp[String], VpcSettings: CfExp[AWSDirectoryServiceMicrosoftAD.VpcSettings], ShortName: Option[CfExp[String]], EnableSso: Option[CfExp[Boolean]], Password: CfExp[String], CreateAlias: Option[CfExp[Boolean]]) extends Resource {
    override def fqn: String = "AWS::DirectoryService::MicrosoftAD"
  }

  case class AWSGameLiftBuild(logicalId: String, Version: Option[CfExp[String]], Name: Option[CfExp[String]], StorageLocation: Option[CfExp[AWSGameLiftBuild.S3Location]]) extends Resource {
    override def fqn: String = "AWS::GameLift::Build"
  }

  case class AWSOpsWorksApp(logicalId: String, Environment: Option[CfExp[List[AWSOpsWorksApp.EnvironmentVariable]]], Name: CfExp[String], AppSource: Option[CfExp[AWSOpsWorksApp.Source]], Domains: Option[CfExp[List[String]]], DataSources: Option[CfExp[List[AWSOpsWorksApp.DataSource]]], Attributes: Option[CfExp[Map[String, String]]], Description: Option[CfExp[String]], EnableSsl: Option[CfExp[Boolean]], StackId: CfExp[String], SslConfiguration: Option[CfExp[AWSOpsWorksApp.SslConfiguration]], Shortname: Option[CfExp[String]], Type: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::OpsWorks::App"
  }

  case class AWSLogsMetricFilter(logicalId: String, FilterPattern: CfExp[String], LogGroupName: CfExp[String], MetricTransformations: CfExp[List[AWSLogsMetricFilter.MetricTransformation]]) extends Resource {
    override def fqn: String = "AWS::Logs::MetricFilter"
  }

  case class AWSElastiCacheSubnetGroup(logicalId: String, SubnetIds: CfExp[List[String]], Description: CfExp[String], CacheSubnetGroupName: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::ElastiCache::SubnetGroup"
  }

  case class AWSSSMAssociation(logicalId: String, Name: CfExp[String], Parameters: Option[CfExp[Map[String, AWSSSMAssociation.ParameterValues]]], DocumentVersion: Option[CfExp[String]], InstanceId: Option[CfExp[String]], ScheduleExpression: Option[CfExp[String]], Targets: Option[CfExp[List[AWSSSMAssociation.Target]]]) extends Resource {
    override def fqn: String = "AWS::SSM::Association"
  }

  case class AWSIAMGroup(logicalId: String, ManagedPolicyArns: Option[CfExp[List[String]]], Path: Option[CfExp[String]], Policies: Option[CfExp[List[AWSIAMGroup.Policy]]], GroupName: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::IAM::Group"
  }

  case class AWSCodePipelineCustomActionType(logicalId: String, Settings: Option[CfExp[AWSCodePipelineCustomActionType.Settings]], Category: CfExp[String], ConfigurationProperties: Option[CfExp[List[AWSCodePipelineCustomActionType.ConfigurationProperties]]], Provider: CfExp[String], Version: Option[CfExp[String]], OutputArtifactDetails: CfExp[AWSCodePipelineCustomActionType.ArtifactDetails], InputArtifactDetails: CfExp[AWSCodePipelineCustomActionType.ArtifactDetails]) extends Resource {
    override def fqn: String = "AWS::CodePipeline::CustomActionType"
  }

  case class AWSEC2NetworkAclEntry(logicalId: String, Egress: Option[CfExp[Boolean]], Ipv6CidrBlock: Option[CfExp[String]], PortRange: Option[CfExp[AWSEC2NetworkAclEntry.PortRange]], CidrBlock: CfExp[String], NetworkAclId: CfExp[String], Icmp: Option[CfExp[AWSEC2NetworkAclEntry.Icmp]], RuleNumber: CfExp[Int], RuleAction: CfExp[String], Protocol: CfExp[Int]) extends Resource {
    override def fqn: String = "AWS::EC2::NetworkAclEntry"
  }

  case class AWSEC2VPNGatewayRoutePropagation(logicalId: String, RouteTableIds: CfExp[List[String]], VpnGatewayId: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EC2::VPNGatewayRoutePropagation"
  }

  case class AWSAutoScalingLifecycleHook(logicalId: String, NotificationMetadata: Option[CfExp[String]], AutoScalingGroupName: CfExp[String], NotificationTargetARN: CfExp[String], RoleARN: CfExp[String], HeartbeatTimeout: Option[CfExp[Int]], LifecycleTransition: CfExp[String], DefaultResult: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::AutoScaling::LifecycleHook"
  }

  case class AWSEC2VPCCidrBlock(logicalId: String, VpcId: CfExp[String], AmazonProvidedIpv6CidrBlock: Option[CfExp[Boolean]]) extends Resource {
    override def fqn: String = "AWS::EC2::VPCCidrBlock"
  }

  case class AWSIAMInstanceProfile(logicalId: String, Path: CfExp[String], Roles: CfExp[List[String]]) extends Resource {
    override def fqn: String = "AWS::IAM::InstanceProfile"
  }

  case class AWSElasticLoadBalancingV2LoadBalancer(logicalId: String, Name: Option[CfExp[String]], Subnets: Option[CfExp[List[String]]], SecurityGroups: Option[CfExp[List[String]]], IpAddressType: Option[CfExp[String]], LoadBalancerAttributes: Option[CfExp[List[AWSElasticLoadBalancingV2LoadBalancer.LoadBalancerAttribute]]], Tags: Option[CfExp[List[Tag]]], Scheme: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::ElasticLoadBalancingV2::LoadBalancer"
  }

  case class AWSEC2NetworkAcl(logicalId: String, VpcId: CfExp[String], Tags: Option[CfExp[List[Tag]]]) extends Resource {
    override def fqn: String = "AWS::EC2::NetworkAcl"
  }

  case class AWSLambdaPermission(logicalId: String, Principal: CfExp[String], FunctionName: CfExp[String], SourceAccount: Option[CfExp[String]], SourceArn: Option[CfExp[String]], Action: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::Lambda::Permission"
  }

  case class AWSRedshiftClusterSecurityGroup(logicalId: String, Description: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::Redshift::ClusterSecurityGroup"
  }

  case class AWSIoTThingPrincipalAttachment(logicalId: String, Principal: CfExp[String], ThingName: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::IoT::ThingPrincipalAttachment"
  }

  case class AWSRDSDBSecurityGroup(logicalId: String, Tags: Option[CfExp[List[Tag]]], GroupDescription: CfExp[String], DBSecurityGroupIngress: CfExp[List[AWSRDSDBSecurityGroup.Ingress]], EC2VpcId: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::RDS::DBSecurityGroup"
  }

  case class AWSOpsWorksLayer(logicalId: String, UseEbsOptimizedInstances: Option[CfExp[Boolean]], Name: CfExp[String], AutoAssignPublicIps: CfExp[Boolean], LoadBasedAutoScaling: Option[CfExp[AWSOpsWorksLayer.LoadBasedAutoScaling]], Packages: Option[CfExp[List[String]]], Attributes: Option[CfExp[Map[String, String]]], StackId: CfExp[String], VolumeConfigurations: Option[CfExp[List[AWSOpsWorksLayer.VolumeConfiguration]]], CustomInstanceProfileArn: Option[CfExp[String]], CustomSecurityGroupIds: Option[CfExp[List[String]]], LifecycleEventConfiguration: Option[CfExp[AWSOpsWorksLayer.LifecycleEventConfiguration]], CustomJson: Option[CfExp[io.circe.Json]], CustomRecipes: Option[CfExp[AWSOpsWorksLayer.Recipes]], AutoAssignElasticIps: CfExp[Boolean], Shortname: CfExp[String], EnableAutoHealing: CfExp[Boolean], InstallUpdatesOnBoot: Option[CfExp[Boolean]], Type: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::OpsWorks::Layer"
  }

  case class AWSDataPipelinePipeline(logicalId: String, ParameterObjects: CfExp[List[AWSDataPipelinePipeline.ParameterObject]], PipelineTags: Option[CfExp[List[AWSDataPipelinePipeline.PipelineTag]]], Name: CfExp[String], Description: Option[CfExp[String]], ParameterValues: Option[CfExp[List[AWSDataPipelinePipeline.ParameterValue]]], Activate: Option[CfExp[Boolean]], PipelineObjects: Option[CfExp[List[AWSDataPipelinePipeline.PipelineObject]]]) extends Resource {
    override def fqn: String = "AWS::DataPipeline::Pipeline"
  }

  case class AWSCloudFormationWaitConditionHandle(logicalId: String) extends Resource {
    override def fqn: String = "AWS::CloudFormation::WaitConditionHandle"
  }

  case class AWSEC2VPNGateway(logicalId: String, Tags: Option[CfExp[List[Tag]]], Type: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EC2::VPNGateway"
  }

  case class AWSSNSTopic(logicalId: String, DisplayName: Option[CfExp[String]], TopicName: Option[CfExp[String]], Subscription: Option[CfExp[List[AWSSNSTopic.Subscription]]]) extends Resource {
    override def fqn: String = "AWS::SNS::Topic"
  }

  case class AWSApplicationAutoScalingScalableTarget(logicalId: String, MinCapacity: CfExp[Int], ResourceId: CfExp[String], MaxCapacity: CfExp[Int], RoleARN: CfExp[String], ScalableDimension: CfExp[String], ServiceNamespace: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::ApplicationAutoScaling::ScalableTarget"
  }

  case class AWSKinesisStream(logicalId: String, Tags: Option[CfExp[List[Tag]]], ShardCount: CfExp[Int], Name: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::Kinesis::Stream"
  }

  case class AWSElasticLoadBalancingV2TargetGroup(logicalId: String, HealthCheckPort: Option[CfExp[String]], Name: Option[CfExp[String]], UnhealthyThresholdCount: Option[CfExp[Int]], HealthyThresholdCount: Option[CfExp[Int]], TargetGroupAttributes: Option[CfExp[List[AWSElasticLoadBalancingV2TargetGroup.TargetGroupAttribute]]], HealthCheckProtocol: Option[CfExp[String]], HealthCheckIntervalSeconds: Option[CfExp[Int]], HealthCheckTimeoutSeconds: Option[CfExp[Int]], Port: CfExp[Int], HealthCheckPath: Option[CfExp[String]], Tags: Option[CfExp[List[Tag]]], Targets: Option[CfExp[List[AWSElasticLoadBalancingV2TargetGroup.TargetDescription]]], Protocol: CfExp[String], Matcher: Option[CfExp[AWSElasticLoadBalancingV2TargetGroup.Matcher]], VpcId: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::ElasticLoadBalancingV2::TargetGroup"
  }

  case class AWSEC2FlowLog(logicalId: String, ResourceId: CfExp[String], LogGroupName: CfExp[String], ResourceType: CfExp[String], DeliverLogsPermissionArn: CfExp[String], TrafficType: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EC2::FlowLog"
  }

  case class AWSApiGatewayRestApi(logicalId: String, Name: Option[CfExp[String]], Description: Option[CfExp[String]], Parameters: Option[CfExp[Map[String, String]]], FailOnWarnings: Option[CfExp[Boolean]], BodyS3Location: Option[CfExp[AWSApiGatewayRestApi.S3Location]], Body: Option[CfExp[io.circe.Json]], Mode: Option[CfExp[String]], CloneFrom: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::ApiGateway::RestApi"
  }

  case class AWSDynamoDBTable(logicalId: String, StreamSpecification: Option[CfExp[AWSDynamoDBTable.StreamSpecification]], LocalSecondaryIndexes: Option[CfExp[List[AWSDynamoDBTable.LocalSecondaryIndex]]], ProvisionedThroughput: CfExp[AWSDynamoDBTable.ProvisionedThroughput], AttributeDefinitions: CfExp[List[AWSDynamoDBTable.AttributeDefinition]], GlobalSecondaryIndexes: Option[CfExp[List[AWSDynamoDBTable.GlobalSecondaryIndex]]], KeySchema: CfExp[List[AWSDynamoDBTable.KeySchema]], TableName: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::DynamoDB::Table"
  }

  case class AWSCertificateManagerCertificate(logicalId: String, DomainValidationOptions: Option[CfExp[List[AWSCertificateManagerCertificate.DomainValidationOption]]], Tags: Option[CfExp[List[Tag]]], SubjectAlternativeNames: Option[CfExp[List[String]]], DomainName: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::CertificateManager::Certificate"
  }

  case class AWSEC2PlacementGroup(logicalId: String, Strategy: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::EC2::PlacementGroup"
  }

  case class AWSLogsSubscriptionFilter(logicalId: String, DestinationArn: CfExp[String], FilterPattern: CfExp[String], LogGroupName: CfExp[String], RoleArn: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::Logs::SubscriptionFilter"
  }

  case class AWSECSTaskDefinition(logicalId: String, ContainerDefinitions: Option[CfExp[List[AWSECSTaskDefinition.ContainerDefinition]]], TaskRoleArn: Option[CfExp[String]], NetworkMode: Option[CfExp[String]], Family: Option[CfExp[String]], Volumes: Option[CfExp[List[AWSECSTaskDefinition.Volume]]]) extends Resource {
    override def fqn: String = "AWS::ECS::TaskDefinition"
  }

  case class AWSWAFRule(logicalId: String, Predicates: Option[CfExp[List[AWSWAFRule.Predicate]]], MetricName: CfExp[String], Name: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::WAF::Rule"
  }

  case class AWSS3Bucket(logicalId: String, VersioningConfiguration: Option[CfExp[AWSS3Bucket.VersioningConfiguration]], NotificationConfiguration: Option[CfExp[AWSS3Bucket.NotificationConfiguration]], BucketName: Option[CfExp[String]], CorsConfiguration: Option[CfExp[AWSS3Bucket.CorsConfiguration]], LifecycleConfiguration: Option[CfExp[AWSS3Bucket.LifecycleConfiguration]], ReplicationConfiguration: Option[CfExp[AWSS3Bucket.ReplicationConfiguration]], AccessControl: Option[CfExp[String]], Tags: Option[CfExp[List[Tag]]], WebsiteConfiguration: Option[CfExp[AWSS3Bucket.WebsiteConfiguration]], LoggingConfiguration: Option[CfExp[AWSS3Bucket.LoggingConfiguration]]) extends Resource {
    override def fqn: String = "AWS::S3::Bucket"
  }

  case class AWSSDBDomain(logicalId: String, Description: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::SDB::Domain"
  }

  case class AWSWAFByteMatchSet(logicalId: String, ByteMatchTuples: Option[CfExp[List[AWSWAFByteMatchSet.ByteMatchTuple]]], Name: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::WAF::ByteMatchSet"
  }

  case class AWSElasticBeanstalkEnvironment(logicalId: String, Description: Option[CfExp[String]], VersionLabel: Option[CfExp[String]], Tier: Option[CfExp[AWSElasticBeanstalkEnvironment.Tier]], SolutionStackName: Option[CfExp[String]], OptionSettings: Option[CfExp[List[AWSElasticBeanstalkEnvironment.OptionSettings]]], TemplateName: Option[CfExp[String]], EnvironmentName: Option[CfExp[String]], CNAMEPrefix: Option[CfExp[String]], ApplicationName: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::ElasticBeanstalk::Environment"
  }

  case class AWSEventsRule(logicalId: String, Name: Option[CfExp[String]], Description: Option[CfExp[String]], EventPattern: Option[CfExp[io.circe.Json]], ScheduleExpression: Option[CfExp[String]], Targets: Option[CfExp[List[AWSEventsRule.Target]]], State: Option[CfExp[String]], RoleArn: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::Events::Rule"
  }

  case class AWSIoTThing(logicalId: String, AttributePayload: Option[CfExp[AWSIoTThing.AttributePayload]], ThingName: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::IoT::Thing"
  }

  case class AWSRDSEventSubscription(logicalId: String, Enabled: Option[CfExp[Boolean]], SourceIds: Option[CfExp[List[String]]], SnsTopicArn: CfExp[String], EventCategories: Option[CfExp[List[String]]], SourceType: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::RDS::EventSubscription"
  }

  case class AWSApiGatewayAccount(logicalId: String, CloudWatchRoleArn: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::ApiGateway::Account"
  }

  case class AWSRoute53RecordSet(logicalId: String, Name: CfExp[String], AliasTarget: Option[CfExp[AWSRoute53RecordSet.AliasTarget]], HealthCheckId: Option[CfExp[String]], Region: Option[CfExp[String]], HostedZoneName: Option[CfExp[String]], Weight: Option[CfExp[Int]], ResourceRecords: Option[CfExp[List[String]]], Failover: Option[CfExp[String]], GeoLocation: Option[CfExp[AWSRoute53RecordSet.GeoLocation]], SetIdentifier: Option[CfExp[String]], TTL: Option[CfExp[String]], Comment: Option[CfExp[String]], HostedZoneId: Option[CfExp[String]], Type: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::Route53::RecordSet"
  }

  case class AWSEC2VolumeAttachment(logicalId: String, InstanceId: CfExp[String], VolumeId: CfExp[String], Device: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EC2::VolumeAttachment"
  }

  case class AWSApiGatewayResource(logicalId: String, RestApiId: CfExp[String], ParentId: CfExp[String], PathPart: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::ApiGateway::Resource"
  }

  case class AWSSSMDocument(logicalId: String, Content: CfExp[io.circe.Json], DocumentType: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::SSM::Document"
  }

  case class AWSEC2SecurityGroupIngress(logicalId: String, CidrIp: Option[CfExp[String]], SourceSecurityGroupName: Option[CfExp[String]], GroupName: Option[CfExp[String]], CidrIpv6: Option[CfExp[String]], GroupId: Option[CfExp[String]], ToPort: Option[CfExp[Int]], FromPort: Option[CfExp[Int]], IpProtocol: CfExp[String], SourceSecurityGroupId: Option[CfExp[String]], SourceSecurityGroupOwnerId: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::EC2::SecurityGroupIngress"
  }

  case class AWSIoTTopicRule(logicalId: String, TopicRulePayload: CfExp[AWSIoTTopicRule.TopicRulePayload], RuleName: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::IoT::TopicRule"
  }

  case class AWSEC2CustomerGateway(logicalId: String, Tags: Option[CfExp[List[Tag]]], Type: CfExp[String], BgpAsn: CfExp[Int], IpAddress: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EC2::CustomerGateway"
  }

  case class AWSCodeBuildProject(logicalId: String, Environment: Option[CfExp[AWSCodeBuildProject.Environment]], Name: Option[CfExp[String]], EncryptionKey: Option[CfExp[String]], ServiceRole: Option[CfExp[String]], Description: Option[CfExp[String]], Artifacts: Option[CfExp[AWSCodeBuildProject.Artifacts]], TimeoutInMinutes: Option[CfExp[Int]], Source: Option[CfExp[AWSCodeBuildProject.Source]], Tags: Option[CfExp[List[Tag]]]) extends Resource {
    override def fqn: String = "AWS::CodeBuild::Project"
  }

  case class AWSEC2EIPAssociation(logicalId: String, PrivateIpAddress: Option[CfExp[String]], AllocationId: Option[CfExp[String]], Eip: Option[CfExp[String]], InstanceId: Option[CfExp[String]], NetworkInterfaceId: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::EC2::EIPAssociation"
  }

  case class AWSWAFXssMatchSet(logicalId: String, XssMatchTuples: CfExp[List[AWSWAFXssMatchSet.XssMatchTuple]], Name: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::WAF::XssMatchSet"
  }

  case class AWSConfigConfigRule(logicalId: String, Scope: Option[CfExp[AWSConfigConfigRule.Scope]], ConfigRuleName: Option[CfExp[String]], Description: Option[CfExp[String]], MaximumExecutionFrequency: Option[CfExp[String]], Source: CfExp[AWSConfigConfigRule.Source], InputParameters: Option[CfExp[io.circe.Json]]) extends Resource {
    override def fqn: String = "AWS::Config::ConfigRule"
  }

  case class AWSEC2VPNConnection(logicalId: String, CustomerGatewayId: CfExp[String], Tags: Option[CfExp[List[Tag]]], StaticRoutesOnly: Option[CfExp[Boolean]], VpnGatewayId: CfExp[String], Type: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EC2::VPNConnection"
  }

  case class AWSRedshiftClusterSubnetGroup(logicalId: String, SubnetIds: CfExp[List[String]], Description: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::Redshift::ClusterSubnetGroup"
  }

  case class AWSWAFIPSet(logicalId: String, Name: CfExp[String], IPSetDescriptors: Option[CfExp[List[AWSWAFIPSet.IPSetDescriptor]]]) extends Resource {
    override def fqn: String = "AWS::WAF::IPSet"
  }

  case class AWSCodePipelinePipeline(logicalId: String, Name: Option[CfExp[String]], DisableInboundStageTransitions: Option[CfExp[List[AWSCodePipelinePipeline.StageTransition]]], ArtifactStore: CfExp[AWSCodePipelinePipeline.ArtifactStore], RoleArn: CfExp[String], Stages: CfExp[List[AWSCodePipelinePipeline.StageDeclaration]], RestartExecutionOnUpdate: Option[CfExp[Boolean]]) extends Resource {
    override def fqn: String = "AWS::CodePipeline::Pipeline"
  }

  case class AWSIoTPolicy(logicalId: String, PolicyDocument: CfExp[io.circe.Json], PolicyName: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::IoT::Policy"
  }

  case class AWSElastiCacheSecurityGroup(logicalId: String, Description: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::ElastiCache::SecurityGroup"
  }

  case class AWSEC2Route(logicalId: String, DestinationCidrBlock: CfExp[String], VpcPeeringConnectionId: Option[CfExp[String]], NatGatewayId: Option[CfExp[String]], RouteTableId: CfExp[String], InstanceId: Option[CfExp[String]], NetworkInterfaceId: Option[CfExp[String]], DestinationIpv6CidrBlock: Option[CfExp[String]], GatewayId: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::EC2::Route"
  }

  case class AWSAutoScalingScheduledAction(logicalId: String, AutoScalingGroupName: CfExp[String], MaxSize: Option[CfExp[Int]], StartTime: Option[CfExp[String]], EndTime: Option[CfExp[String]], MinSize: Option[CfExp[Int]], DesiredCapacity: Option[CfExp[Int]], Recurrence: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::AutoScaling::ScheduledAction"
  }

  case class AWSIoTCertificate(logicalId: String, CertificateSigningRequest: CfExp[String], Status: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::IoT::Certificate"
  }

  case class AWSElasticLoadBalancingV2Listener(logicalId: String, DefaultActions: CfExp[List[AWSElasticLoadBalancingV2Listener.Action]], Port: CfExp[Int], SslPolicy: Option[CfExp[String]], Protocol: CfExp[String], LoadBalancerArn: CfExp[String], Certificates: Option[CfExp[List[AWSElasticLoadBalancingV2Listener.Certificate]]]) extends Resource {
    override def fqn: String = "AWS::ElasticLoadBalancingV2::Listener"
  }

  case class AWSElasticBeanstalkApplication(logicalId: String, ApplicationName: Option[CfExp[String]], Description: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::ElasticBeanstalk::Application"
  }

  case class AWSApiGatewayMethod(logicalId: String, Integration: Option[CfExp[AWSApiGatewayMethod.Integration]], ResourceId: Option[CfExp[String]], RequestModels: Option[CfExp[Map[String, String]]], MethodResponses: Option[CfExp[List[AWSApiGatewayMethod.MethodResponse]]], RequestParameters: Option[CfExp[Map[String, Boolean]]], RestApiId: Option[CfExp[String]], AuthorizerId: Option[CfExp[String]], HttpMethod: CfExp[String], AuthorizationType: Option[CfExp[String]], ApiKeyRequired: Option[CfExp[Boolean]]) extends Resource {
    override def fqn: String = "AWS::ApiGateway::Method"
  }

  case class AWSApiGatewayStage(logicalId: String, ClientCertificateId: Option[CfExp[String]], MethodSettings: Option[CfExp[List[AWSApiGatewayStage.MethodSetting]]], CacheClusterEnabled: Option[CfExp[Boolean]], StageName: Option[CfExp[String]], RestApiId: Option[CfExp[String]], CacheClusterSize: Option[CfExp[String]], Description: Option[CfExp[String]], DeploymentId: Option[CfExp[String]], Variables: Option[CfExp[Map[String, String]]]) extends Resource {
    override def fqn: String = "AWS::ApiGateway::Stage"
  }

  case class AWSApiGatewayAuthorizer(logicalId: String, Name: Option[CfExp[String]], ProviderARNs: Option[CfExp[List[String]]], RestApiId: Option[CfExp[String]], IdentitySource: Option[CfExp[String]], AuthorizerCredentials: Option[CfExp[String]], AuthorizerResultTtlInSeconds: Option[CfExp[Int]], IdentityValidationExpression: Option[CfExp[String]], AuthorizerUri: Option[CfExp[String]], Type: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::ApiGateway::Authorizer"
  }

  case class AWSAutoScalingScalingPolicy(logicalId: String, PolicyType: Option[CfExp[String]], MetricAggregationType: Option[CfExp[String]], AutoScalingGroupName: CfExp[String], ScalingAdjustment: Option[CfExp[Int]], Cooldown: Option[CfExp[String]], StepAdjustments: Option[CfExp[List[AWSAutoScalingScalingPolicy.StepAdjustment]]], MinAdjustmentMagnitude: Option[CfExp[Int]], EstimatedInstanceWarmup: Option[CfExp[Int]], AdjustmentType: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::AutoScaling::ScalingPolicy"
  }

  case class AWSElastiCacheCacheCluster(logicalId: String, PreferredMaintenanceWindow: Option[CfExp[String]], AZMode: Option[CfExp[String]], CacheParameterGroupName: Option[CfExp[String]], Engine: CfExp[String], PreferredAvailabilityZones: Option[CfExp[List[String]]], ClusterName: Option[CfExp[String]], PreferredAvailabilityZone: Option[CfExp[String]], CacheNodeType: CfExp[String], SnapshotName: Option[CfExp[String]], SnapshotWindow: Option[CfExp[String]], CacheSecurityGroupNames: Option[CfExp[List[String]]], AutoMinorVersionUpgrade: Option[CfExp[Boolean]], VpcSecurityGroupIds: Option[CfExp[List[String]]], EngineVersion: Option[CfExp[String]], NotificationTopicArn: Option[CfExp[String]], Port: Option[CfExp[Int]], CacheSubnetGroupName: Option[CfExp[String]], Tags: Option[CfExp[List[Tag]]], NumCacheNodes: CfExp[Int], SnapshotArns: Option[CfExp[List[String]]], SnapshotRetentionLimit: Option[CfExp[Int]]) extends Resource {
    override def fqn: String = "AWS::ElastiCache::CacheCluster"
  }

  case class AWSCodeDeployApplication(logicalId: String, ApplicationName: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::CodeDeploy::Application"
  }

  case class AWSLogsLogGroup(logicalId: String, RetentionInDays: Option[CfExp[Int]], LogGroupName: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::Logs::LogGroup"
  }

  case class AWSGameLiftAlias(logicalId: String, RoutingStrategy: CfExp[AWSGameLiftAlias.RoutingStrategy], Description: Option[CfExp[String]], Name: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::GameLift::Alias"
  }

  case class AWSCodeCommitRepository(logicalId: String, RepositoryDescription: Option[CfExp[String]], RepositoryName: CfExp[String], Triggers: Option[CfExp[List[AWSCodeCommitRepository.RepositoryTrigger]]]) extends Resource {
    override def fqn: String = "AWS::CodeCommit::Repository"
  }

  case class AWSEC2VPCEndpoint(logicalId: String, VpcId: CfExp[String], PolicyDocument: Option[CfExp[io.circe.Json]], ServiceName: CfExp[String], RouteTableIds: Option[CfExp[List[String]]]) extends Resource {
    override def fqn: String = "AWS::EC2::VPCEndpoint"
  }

  case class AWSApiGatewayUsagePlan(logicalId: String, Throttle: Option[CfExp[AWSApiGatewayUsagePlan.ThrottleSettings]], Description: Option[CfExp[String]], ApiStages: Option[CfExp[List[AWSApiGatewayUsagePlan.ApiStage]]], Quota: Option[CfExp[AWSApiGatewayUsagePlan.QuotaSettings]], UsagePlanName: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::ApiGateway::UsagePlan"
  }

  case class AWSRoute53RecordSetGroup(logicalId: String, RecordSets: Option[CfExp[List[AWSRoute53RecordSetGroup.RecordSet]]], Comment: Option[CfExp[String]], HostedZoneName: Option[CfExp[String]], HostedZoneId: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::Route53::RecordSetGroup"
  }

  case class AWSEC2VPNConnectionRoute(logicalId: String, DestinationCidrBlock: CfExp[String], VpnConnectionId: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EC2::VPNConnectionRoute"
  }

  case class AWSSQSQueue(logicalId: String, VisibilityTimeout: Option[CfExp[Int]], QueueName: Option[CfExp[String]], MessageRetentionPeriod: Option[CfExp[Int]], RedrivePolicy: Option[CfExp[io.circe.Json]], ReceiveMessageWaitTimeSeconds: Option[CfExp[Int]], DelaySeconds: Option[CfExp[Int]], MaximumMessageSize: Option[CfExp[Int]]) extends Resource {
    override def fqn: String = "AWS::SQS::Queue"
  }

  case class AWSOpsWorksVolume(logicalId: String, StackId: CfExp[String], Ec2VolumeId: CfExp[String], Name: Option[CfExp[String]], MountPoint: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::OpsWorks::Volume"
  }

  case class AWSEC2NetworkInterface(logicalId: String, Ipv6Addresses: Option[CfExp[AWSEC2NetworkInterface.InstanceIpv6Address]], PrivateIpAddress: Option[CfExp[String]], Description: Option[CfExp[String]], PrivateIpAddresses: Option[CfExp[List[AWSEC2NetworkInterface.PrivateIpAddressSpecification]]], Ipv6AddressCount: Option[CfExp[Int]], Tags: Option[CfExp[List[Tag]]], SourceDestCheck: Option[CfExp[Boolean]], GroupSet: Option[CfExp[List[String]]], SecondaryPrivateIpAddressCount: Option[CfExp[Int]], SubnetId: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EC2::NetworkInterface"
  }

  case class AWSEC2Instance(logicalId: String, NetworkInterfaces: Option[CfExp[List[AWSEC2Instance.NetworkInterface]]], Ipv6Addresses: Option[CfExp[List[AWSEC2Instance.InstanceIpv6Address]]], RamdiskId: Option[CfExp[String]], BlockDeviceMappings: Option[CfExp[List[AWSEC2Instance.BlockDeviceMapping]]], ImageId: CfExp[String], InstanceInitiatedShutdownBehavior: Option[CfExp[String]], PrivateIpAddress: Option[CfExp[String]], EbsOptimized: Option[CfExp[Boolean]], DisableApiTermination: Option[CfExp[Boolean]], UserData: Option[CfExp[String]], SecurityGroups: Option[CfExp[List[String]]], PlacementGroupName: Option[CfExp[String]], Ipv6AddressCount: Option[CfExp[Int]], KernelId: Option[CfExp[String]], Tenancy: Option[CfExp[String]], Affinity: Option[CfExp[String]], AvailabilityZone: Option[CfExp[String]], Volumes: Option[CfExp[List[AWSEC2Instance.Volume]]], HostId: Option[CfExp[String]], SecurityGroupIds: Option[CfExp[List[String]]], SsmAssociations: Option[CfExp[List[AWSEC2Instance.SsmAssociation]]], KeyName: Option[CfExp[String]], InstanceType: Option[CfExp[String]], AdditionalInfo: Option[CfExp[String]], Tags: Option[CfExp[List[Tag]]], Monitoring: Option[CfExp[Boolean]], SourceDestCheck: Option[CfExp[Boolean]], IamInstanceProfile: Option[CfExp[String]], SubnetId: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::EC2::Instance"
  }

  case class AWSCloudFormationStack(logicalId: String, NotificationARNs: Option[CfExp[List[String]]], Parameters: Option[CfExp[Map[String, String]]], TimeoutInMinutes: Option[CfExp[Int]], Tags: Option[CfExp[List[Tag]]], TemplateURL: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::CloudFormation::Stack"
  }

  case class AWSElasticLoadBalancingLoadBalancer(logicalId: String, HealthCheck: Option[CfExp[AWSElasticLoadBalancingLoadBalancer.HealthCheck]], AccessLoggingPolicy: Option[CfExp[AWSElasticLoadBalancingLoadBalancer.AccessLoggingPolicy]], AppCookieStickinessPolicy: Option[CfExp[List[AWSElasticLoadBalancingLoadBalancer.AppCookieStickinessPolicy]]], CrossZone: Option[CfExp[Boolean]], Listeners: CfExp[List[AWSElasticLoadBalancingLoadBalancer.Listeners]], Policies: Option[CfExp[List[AWSElasticLoadBalancingLoadBalancer.Policies]]], Subnets: Option[CfExp[List[String]]], ConnectionDrainingPolicy: Option[CfExp[AWSElasticLoadBalancingLoadBalancer.ConnectionDrainingPolicy]], SecurityGroups: Option[CfExp[List[String]]], LoadBalancerName: Option[CfExp[String]], Instances: Option[CfExp[List[String]]], Tags: Option[CfExp[List[Tag]]], ConnectionSettings: Option[CfExp[AWSElasticLoadBalancingLoadBalancer.ConnectionSettings]], Scheme: Option[CfExp[String]], LBCookieStickinessPolicy: Option[CfExp[List[AWSElasticLoadBalancingLoadBalancer.LBCookieStickinessPolicy]]], AvailabilityZones: Option[CfExp[List[String]]]) extends Resource {
    override def fqn: String = "AWS::ElasticLoadBalancing::LoadBalancer"
  }

  case class AWSWorkSpacesWorkspace(logicalId: String, VolumeEncryptionKey: Option[CfExp[String]], DirectoryId: CfExp[String], UserName: CfExp[String], BundleId: CfExp[String], UserVolumeEncryptionEnabled: Option[CfExp[Boolean]], RootVolumeEncryptionEnabled: Option[CfExp[Boolean]]) extends Resource {
    override def fqn: String = "AWS::WorkSpaces::Workspace"
  }

  case class AWSKMSAlias(logicalId: String, TargetKeyId: CfExp[String], AliasName: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::KMS::Alias"
  }

  case class AWSElastiCacheSecurityGroupIngress(logicalId: String, EC2SecurityGroupOwnerId: Option[CfExp[String]], CacheSecurityGroupName: CfExp[String], EC2SecurityGroupName: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::ElastiCache::SecurityGroupIngress"
  }

  case class AWSKMSKey(logicalId: String, KeyUsage: Option[CfExp[String]], EnableKeyRotation: Option[CfExp[Boolean]], Enabled: Option[CfExp[Boolean]], Description: Option[CfExp[String]], KeyPolicy: CfExp[io.circe.Json]) extends Resource {
    override def fqn: String = "AWS::KMS::Key"
  }

  case class AWSSNSTopicPolicy(logicalId: String, PolicyDocument: CfExp[io.circe.Json], Topics: CfExp[List[String]]) extends Resource {
    override def fqn: String = "AWS::SNS::TopicPolicy"
  }

  case class AWSGameLiftFleet(logicalId: String, Name: CfExp[String], EC2InstanceType: CfExp[String], Description: Option[CfExp[String]], BuildId: CfExp[String], EC2InboundPermissions: Option[CfExp[List[AWSGameLiftFleet.IpPermission]]], MaxSize: Option[CfExp[Int]], DesiredEC2Instances: CfExp[Int], MinSize: Option[CfExp[Int]], ServerLaunchParameters: Option[CfExp[String]], ServerLaunchPath: CfExp[String], LogPaths: Option[CfExp[List[String]]]) extends Resource {
    override def fqn: String = "AWS::GameLift::Fleet"
  }

  case class AWSCloudWatchAlarm(logicalId: String, Threshold: CfExp[Double], Dimensions: Option[CfExp[List[AWSCloudWatchAlarm.Dimension]]], AlarmDescription: Option[CfExp[String]], EvaluationPeriods: CfExp[Double], ComparisonOperator: CfExp[String], Period: CfExp[Int], AlarmActions: Option[CfExp[List[String]]], Statistic: CfExp[String], AlarmName: Option[CfExp[String]], Namespace: CfExp[String], ActionsEnabled: Option[CfExp[Boolean]], InsufficientDataActions: Option[CfExp[List[String]]], Unit: Option[CfExp[String]], OKActions: Option[CfExp[List[String]]], MetricName: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::CloudWatch::Alarm"
  }

  case class AWSRedshiftCluster(logicalId: String, PreferredMaintenanceWindow: Option[CfExp[String]], ClusterParameterGroupName: Option[CfExp[Int]], DBName: CfExp[String], AllowVersionUpdate: Option[CfExp[Boolean]], ClusterType: CfExp[String], ClusterVersion: Option[CfExp[String]], KmsKeyId: Option[CfExp[String]], NodeType: CfExp[String], ElasticIp: Option[CfExp[String]], AutomatedSnapshotRetentionPeriod: Option[CfExp[Int]], MasterUsername: CfExp[String], AvailabilityZone: Option[CfExp[String]], ClusterSecurityGroups: Option[CfExp[List[String]]], Encrypted: Option[CfExp[Boolean]], VpcSecurityGroupIds: Option[CfExp[List[String]]], Port: Option[CfExp[Int]], NumberOfNodes: Option[CfExp[Int]], MasterUserPassword: CfExp[String], ClusterSubnetGroupName: Option[CfExp[String]], PubliclyAccessible: Option[CfExp[Boolean]], OwnerAccount: Option[CfExp[String]], SnapshotClusterIdentifier: Option[CfExp[String]], HsmClientCertificateIdentifier: Option[CfExp[String]], SnapshotIdentifier: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::Redshift::Cluster"
  }

  case class AWSRDSDBParameterGroup(logicalId: String, Tags: Option[CfExp[List[Tag]]], Family: CfExp[String], Description: CfExp[String], Parameters: Option[CfExp[Map[String, String]]]) extends Resource {
    override def fqn: String = "AWS::RDS::DBParameterGroup"
  }

  case class AWSEC2SecurityGroupEgress(logicalId: String, DestinationSecurityGroupId: Option[CfExp[String]], CidrIp: Option[CfExp[String]], DestinationPrefixListId: Option[CfExp[String]], CidrIpv6: Option[CfExp[String]], GroupId: CfExp[String], ToPort: Option[CfExp[Int]], FromPort: Option[CfExp[Int]], IpProtocol: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EC2::SecurityGroupEgress"
  }

  case class AWSS3BucketPolicy(logicalId: String, PolicyDocument: CfExp[io.circe.Json], Bucket: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::S3::BucketPolicy"
  }

  case class AWSECSService(logicalId: String, DesiredCount: CfExp[Int], DeploymentConfiguration: Option[CfExp[AWSECSService.DeploymentConfiguration]], Role: Option[CfExp[String]], TaskDefinition: CfExp[String], LoadBalancers: Option[CfExp[List[AWSECSService.LoadBalancer]]], Cluster: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::ECS::Service"
  }

  case class AWSEC2SubnetRouteTableAssociation(logicalId: String, RouteTableId: CfExp[String], SubnetId: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EC2::SubnetRouteTableAssociation"
  }

  case class AWSWAFWebACL(logicalId: String, Rules: Option[CfExp[List[AWSWAFWebACL.ActivatedRule]]], MetricName: CfExp[String], DefaultAction: CfExp[AWSWAFWebACL.WafAction], Name: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::WAF::WebACL"
  }

  case class AWSElasticLoadBalancingV2ListenerRule(logicalId: String, Priority: CfExp[Int], Actions: CfExp[List[AWSElasticLoadBalancingV2ListenerRule.Action]], ListenerArn: CfExp[String], Conditions: CfExp[List[AWSElasticLoadBalancingV2ListenerRule.RuleCondition]]) extends Resource {
    override def fqn: String = "AWS::ElasticLoadBalancingV2::ListenerRule"
  }

  case class AWSEC2VPCDHCPOptionsAssociation(logicalId: String, VpcId: CfExp[String], DhcpOptionsId: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EC2::VPCDHCPOptionsAssociation"
  }

  case class AWSWAFSqlInjectionMatchSet(logicalId: String, SqlInjectionMatchTuples: Option[CfExp[List[AWSWAFSqlInjectionMatchSet.SqlInjectionMatchTuple]]], Name: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::WAF::SqlInjectionMatchSet"
  }

  case class AWSCodeDeployDeploymentGroup(logicalId: String, OnPremisesInstanceTagFilters: Option[CfExp[List[AWSCodeDeployDeploymentGroup.OnPremisesInstanceTagFilter]]], AutoScalingGroups: Option[CfExp[List[String]]], Deployment: Option[CfExp[AWSCodeDeployDeploymentGroup.Deployment]], Ec2TagFilters: Option[CfExp[List[AWSCodeDeployDeploymentGroup.Ec2TagFilter]]], ServiceRoleArn: CfExp[String], ApplicationName: CfExp[String], DeploymentConfigName: Option[CfExp[String]], DeploymentGroupName: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::CodeDeploy::DeploymentGroup"
  }

  case class AWSConfigConfigurationRecorder(logicalId: String, RecordingGroup: Option[CfExp[AWSConfigConfigurationRecorder.RecordingGroup]], RoleArn: CfExp[String], Name: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::Config::ConfigurationRecorder"
  }

  case class AWSRDSDBInstance(logicalId: String, PreferredMaintenanceWindow: Option[CfExp[String]], MonitoringInterval: Option[CfExp[Int]], DBClusterIdentifier: Option[CfExp[String]], CharacterSetName: Option[CfExp[String]], Timezone: Option[CfExp[String]], DBInstanceClass: CfExp[String], Engine: Option[CfExp[String]], AllowMajorVersionUpgrade: Option[CfExp[Boolean]], OptionGroupName: Option[CfExp[String]], MultiAZ: Option[CfExp[Boolean]], DBSecurityGroups: Option[CfExp[List[String]]], DBName: Option[CfExp[String]], MonitoringRoleArn: Option[CfExp[String]], SourceDBInstanceIdentifier: Option[CfExp[String]], KmsKeyId: Option[CfExp[String]], AllocatedStorage: Option[CfExp[String]], DBInstanceIdentifier: Option[CfExp[String]], VPCSecurityGroups: Option[CfExp[List[String]]], BackupRetentionPeriod: Option[CfExp[String]], MasterUsername: Option[CfExp[String]], AvailabilityZone: Option[CfExp[String]], AutoMinorVersionUpgrade: Option[CfExp[Boolean]], DomainIAMRoleName: Option[CfExp[String]], EngineVersion: Option[CfExp[String]], DBSubnetGroupName: Option[CfExp[String]], Port: Option[CfExp[String]], MasterUserPassword: Option[CfExp[String]], PreferredBackupWindow: Option[CfExp[String]], Iops: Option[CfExp[Int]], StorageType: Option[CfExp[String]], Tags: Option[CfExp[List[Tag]]], PubliclyAccessible: Option[CfExp[Boolean]], LicenseModel: Option[CfExp[String]], DBSnapshotIdentifier: Option[CfExp[String]], DBParameterGroupName: Option[CfExp[String]], Domain: Option[CfExp[String]], StorageEncrypted: Option[CfExp[Boolean]]) extends Resource {
    override def fqn: String = "AWS::RDS::DBInstance"
  }

  case class AWSAutoScalingAutoScalingGroup(logicalId: String, PlacementGroup: Option[CfExp[String]], TerminationPolicies: Option[CfExp[List[String]]], LoadBalancerNames: Option[CfExp[List[String]]], HealthCheckType: Option[CfExp[String]], VPCZoneIdentifier: Option[CfExp[List[String]]], MaxSize: CfExp[String], NotificationConfigurations: Option[CfExp[AWSAutoScalingAutoScalingGroup.NotificationConfigurations]], Cooldown: Option[CfExp[String]], MinSize: CfExp[String], TargetGroupARNs: Option[CfExp[List[String]]], MetricsCollection: Option[CfExp[AWSAutoScalingAutoScalingGroup.MetricsCollection]], AsTags: Option[CfExp[List[AWSAutoScalingAutoScalingGroup.TagProperty]]], InstanceId: Option[CfExp[String]], DesiredCapacity: Option[CfExp[String]], LaunchConfigurationName: Option[CfExp[String]], HealthCheckGracePeriod: Option[CfExp[Int]], AvailabilityZones: Option[CfExp[List[String]]]) extends Resource {
    override def fqn: String = "AWS::AutoScaling::AutoScalingGroup"
  }

  case class AWSEC2SubnetNetworkAclAssociation(logicalId: String, NetworkAclId: CfExp[String], SubnetId: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EC2::SubnetNetworkAclAssociation"
  }

  case class AWSLambdaFunction(logicalId: String, Environment: Option[CfExp[AWSLambdaFunction.Environment]], Role: CfExp[String], KmsKeyArn: Option[CfExp[String]], Handler: CfExp[String], Code: CfExp[AWSLambdaFunction.Code], FunctionName: Option[CfExp[String]], Description: Option[CfExp[String]], MemorySize: Option[CfExp[Int]], VpcConfig: Option[CfExp[AWSLambdaFunction.VpcConfig]], Runtime: CfExp[String], Timeout: Option[CfExp[Int]]) extends Resource {
    override def fqn: String = "AWS::Lambda::Function"
  }

  case class AWSEC2EIP(logicalId: String, InstanceId: Option[CfExp[String]], Domain: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::EC2::EIP"
  }

  case class AWSEFSMountTarget(logicalId: String, SecurityGroups: CfExp[List[String]], SubnetId: CfExp[String], IpAddress: Option[CfExp[String]], FileSystemId: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EFS::MountTarget"
  }

  case class AWSEC2SubnetCidrBlock(logicalId: String, Ipv6CidrBlock: CfExp[String], SubnetId: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EC2::SubnetCidrBlock"
  }

  case class AWSEC2Volume(logicalId: String, VolumeType: Option[CfExp[String]], Size: Option[CfExp[Int]], SnapshotId: Option[CfExp[String]], KmsKeyId: Option[CfExp[String]], AutoEnableIO: Option[CfExp[Boolean]], AvailabilityZone: CfExp[String], Encrypted: Option[CfExp[Boolean]], Iops: Option[CfExp[Int]], Tags: Option[CfExp[List[Tag]]]) extends Resource {
    override def fqn: String = "AWS::EC2::Volume"
  }

  case class AWSEMRCluster(logicalId: String, LogUri: Option[CfExp[String]], Name: CfExp[String], Applications: Option[CfExp[List[AWSEMRCluster.Application]]], ReleaseLabel: Option[CfExp[String]], ServiceRole: CfExp[String], Configurations: Option[CfExp[List[AWSEMRCluster.Configuration]]], JobFlowRole: CfExp[String], Instances: CfExp[AWSEMRCluster.JobFlowInstancesConfig], AdditionalInfo: Option[CfExp[io.circe.Json]], Tags: Option[CfExp[List[Tag]]], VisibleToAllUsers: Option[CfExp[Boolean]], BootstrapActions: Option[CfExp[List[AWSEMRCluster.BootstrapActionConfig]]]) extends Resource {
    override def fqn: String = "AWS::EMR::Cluster"
  }

  case class AWSApiGatewayBasePathMapping(logicalId: String, RestApiId: Option[CfExp[String]], BasePath: Option[CfExp[String]], Stage: Option[CfExp[String]], DomainName: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::ApiGateway::BasePathMapping"
  }

  case class AWSIoTPolicyPrincipalAttachment(logicalId: String, Principal: CfExp[String], PolicyName: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::IoT::PolicyPrincipalAttachment"
  }

  case class AWSWAFSizeConstraintSet(logicalId: String, SizeConstraints: CfExp[List[AWSWAFSizeConstraintSet.SizeConstraint]], Name: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::WAF::SizeConstraintSet"
  }

  case class AWSSNSSubscription(logicalId: String, Protocol: Option[CfExp[String]], TopicArn: Option[CfExp[String]], Endpoint: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::SNS::Subscription"
  }

  case class AWSEC2DHCPOptions(logicalId: String, DomainNameServers: Option[CfExp[List[String]]], DomainName: Option[CfExp[String]], Tags: Option[CfExp[List[Tag]]], NtpServers: Option[CfExp[String]], NetbiosNameServers: Option[CfExp[List[String]]], NetbiosNodeType: Option[CfExp[Int]]) extends Resource {
    override def fqn: String = "AWS::EC2::DHCPOptions"
  }

  case class AWSIAMManagedPolicy(logicalId: String, Users: Option[CfExp[List[String]]], Path: Option[CfExp[String]], Description: Option[CfExp[String]], PolicyDocument: Option[CfExp[io.circe.Json]], Groups: Option[CfExp[List[String]]], Roles: Option[CfExp[List[String]]]) extends Resource {
    override def fqn: String = "AWS::IAM::ManagedPolicy"
  }

  case class AWSLogsDestination(logicalId: String, DestinationName: CfExp[String], DestinationPolicy: CfExp[String], RoleArn: CfExp[String], TargetArn: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::Logs::Destination"
  }

  case class AWSIAMPolicy(logicalId: String, Users: Option[CfExp[List[String]]], PolicyDocument: CfExp[io.circe.Json], Groups: Option[CfExp[List[String]]], PolicyName: CfExp[String], Roles: Option[CfExp[List[String]]]) extends Resource {
    override def fqn: String = "AWS::IAM::Policy"
  }

  case class AWSEC2VPCPeeringConnection(logicalId: String, VpcId: CfExp[String], Tags: Option[CfExp[List[Tag]]], PeerVpcId: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EC2::VPCPeeringConnection"
  }

  case class AWSEC2SpotFleet(logicalId: String, SpotFleetRequestConfigData: CfExp[AWSEC2SpotFleet.SpotFleetRequestConfigData]) extends Resource {
    override def fqn: String = "AWS::EC2::SpotFleet"
  }

  case class AWSElastiCacheParameterGroup(logicalId: String, CacheParameterGroupFamily: CfExp[String], Properties: Option[CfExp[Map[String, String]]], Description: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::ElastiCache::ParameterGroup"
  }

  case class AWSEC2NetworkInterfaceAttachment(logicalId: String, InstanceId: CfExp[String], DeleteOnTermination: Option[CfExp[Boolean]], NetworkInterfaceId: CfExp[String], DeviceIndex: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EC2::NetworkInterfaceAttachment"
  }

  case class AWSRDSDBSecurityGroupIngress(logicalId: String, CIDRIP: Option[CfExp[String]], EC2SecurityGroupId: Option[CfExp[String]], EC2SecurityGroupName: Option[CfExp[String]], DBSecurityGroupName: CfExp[String], EC2SecurityGroupOwnerId: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::RDS::DBSecurityGroupIngress"
  }

  case class AWSEMRInstanceGroupConfig(logicalId: String, EbsConfiguration: Option[CfExp[AWSEMRInstanceGroupConfig.EbsConfiguration]], Name: Option[CfExp[String]], JobFlowId: CfExp[String], InstanceCount: CfExp[Int], Configurations: Option[CfExp[List[AWSEMRInstanceGroupConfig.Configuration]]], BidPrice: Option[CfExp[String]], Market: Option[CfExp[String]], InstanceType: CfExp[String], InstanceRole: CfExp[String]) extends Resource {
    override def fqn: String = "AWS::EMR::InstanceGroupConfig"
  }

  case class AWSEC2SecurityGroup(logicalId: String, SecurityGroupIngress: Option[CfExp[List[AWSEC2SecurityGroup.Rule]]], GroupDescription: CfExp[String], Tags: Option[CfExp[List[Tag]]], SecurityGroupEgress: Option[CfExp[List[AWSEC2SecurityGroup.Rule]]], VpcId: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::EC2::SecurityGroup"
  }

  case class AWSApiGatewayClientCertificate(logicalId: String, Description: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::ApiGateway::ClientCertificate"
  }

  case class AWSRDSDBCluster(logicalId: String, PreferredMaintenanceWindow: Option[CfExp[String]], Engine: CfExp[String], DBClusterParameterGroupName: Option[CfExp[String]], DatabaseName: Option[CfExp[String]], KmsKeyId: Option[CfExp[String]], BackupRetentionPeriod: Option[CfExp[Int]], MasterUsername: Option[CfExp[String]], VpcSecurityGroupIds: Option[CfExp[List[String]]], EngineVersion: Option[CfExp[String]], DBSubnetGroupName: Option[CfExp[String]], Port: Option[CfExp[Int]], MasterUserPassword: Option[CfExp[String]], PreferredBackupWindow: Option[CfExp[String]], Tags: Option[CfExp[List[Tag]]], StorageEncrypted: Option[CfExp[Boolean]], SnapshotIdentifier: Option[CfExp[String]], AvailabilityZones: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::RDS::DBCluster"
  }

  case class AWSLambdaVersion(logicalId: String, FunctionName: CfExp[String], Description: Option[CfExp[String]], CodeSha256: Option[CfExp[String]]) extends Resource {
    override def fqn: String = "AWS::Lambda::Version"
  }

  case class AWSSQSQueuePolicy(logicalId: String, Queues: CfExp[List[String]], PolicyDocument: CfExp[io.circe.Json]) extends Resource {
    override def fqn: String = "AWS::SQS::QueuePolicy"
  }

  case class AWSDirectoryServiceSimpleAD(logicalId: String, Name: CfExp[String], VpcSettings: CfExp[AWSDirectoryServiceSimpleAD.VpcSettings], ShortName: Option[CfExp[String]], EnableSso: Option[CfExp[Boolean]], Size: CfExp[String], Description: Option[CfExp[String]], Password: CfExp[String], CreateAlias: Option[CfExp[Boolean]]) extends Resource {
    override def fqn: String = "AWS::DirectoryService::SimpleAD"
  }

}
