package com.timeout.scalacloudformation

import java.time.ZonedDateTime

import io.circe.Json

trait CfExp[+T]

object CfExp {
  type E[+T] = CfExp[T]

  trait IsLit[A]

  object IsLit {
    implicit val stringLit = new IsLit[String] {}
    implicit val IntLit = new IsLit[Int] {}
    implicit val longLit = new IsLit[Long] {}
    implicit val doubleLit = new IsLit[Double] {}
    implicit val boolLit = new IsLit[Boolean] {}
    implicit val dateTimeLit = new IsLit[ZonedDateTime] {}
    implicit val jsonLit = new IsLit[Json] {}
    implicit def propertyLit[T <: ResourceProperty] = new IsLit[T] {}
    implicit def listLit[A: IsLit] = new IsLit[List[A]]{}
  }

  case class Lit[T: IsLit](value: T) extends E[T]

  /** The return value for ref depends on the resource
    * All these return types could be typed better (e.g. IP address VS URL)
    * but they are all treated as String.
    * For a more refined behaviour, it seems reasonable to use type members instead
    */
  case class ResourceRef(value: Resource) extends E[String]
  case class PseudoParameterRef(value: PseudoParameter) extends E[String]

  case class FnBase64(exp: E[String]) extends E[String]

  case class FnAnd(cond1: E[Boolean], cond2: E[Boolean]) extends E[Boolean]
  case class FnEquals[T](left: E[T], right: E[T]) extends E[Boolean]
  case class FnIf[T](cond: E[Boolean], ifTrue: E[T], ifFalse: E[T]) extends E[T]
  case class FnNot(cond: E[Boolean]) extends E[Boolean]
  case class FnOr(conds: E[Boolean]*) extends E[Boolean]
}
