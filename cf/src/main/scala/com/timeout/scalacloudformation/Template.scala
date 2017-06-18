package com.timeout.scalacloudformation


case class Template(parameters: List[Parameter],
                    resources: List[Resource])
