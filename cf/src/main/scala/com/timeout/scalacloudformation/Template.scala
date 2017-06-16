package com.timeout.scalacloudformation

object Template {
  sealed trait Version
  object Version {
    object `2010-09-09` extends Version
  }
}

case class Template(version: Template.Version = Template.Version.`2010-09-09`,
                    resources: List[Resource])
