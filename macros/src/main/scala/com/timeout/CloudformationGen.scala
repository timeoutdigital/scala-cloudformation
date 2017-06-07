package com.timeout

import com.timeout.CodeGen.Config

import scala.meta._

class CloudformationGen extends scala.annotation.StaticAnnotation {
  inline def apply(defn: Any): Any = meta {

    val prefixes = this match {
      case q"new $_($arg)" =>
        val prefixes = arg.collect { case Lit(s: String) => s }.toSet
        System.err.println(s"Excluding AWS resource prefixes: ${prefixes.mkString(", ")}")
        prefixes
      case _ =>
        Set.empty[String]
    }

    val gen = new CodeGen(Config(excludePrefixes = prefixes))

    defn match {
      case q"object $_ {..$_}" =>
        q"..${gen.objectStats ++ gen.classStats}"
      case _ =>
        abort("@CloudformationGen must be used on an object")
    }
  }
}
