package com.timeout

import com.timeout.CodeGen.Config

import scala.meta._

class CloudformationGen extends scala.annotation.StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    val gen = new CodeGen(Config(excludePrefixes = Set("AWS::Cognito")))

    defn match {
      case q"object $_ {..$_}" =>
        q"..${gen.classStats ++ gen.objectStats}"
      case _ =>
        abort("@CloudformationGen must be used on an object")
    }
  }
}
