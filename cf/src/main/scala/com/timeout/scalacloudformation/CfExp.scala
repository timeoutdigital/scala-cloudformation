package com.timeout.scalacloudformation

import java.time.ZonedDateTime

import io.circe.Json

sealed trait CfExp[+T]

object CfExp {
  type E[+T] = CfExp[T]
  sealed trait Lit[+T] extends E[T] {
    def value: T
  }
  case class LitString(value: String) extends Lit[String]
  case class LitLong(value: Long) extends Lit[Long]
  case class LitInteger(value: Integer) extends Lit[Integer]
  case class LitDouble(value: Double) extends Lit[Double]
  case class LitBoolean(value: Boolean) extends Lit[Boolean]
  case class LitTimestamp(value: ZonedDateTime) extends Lit[ZonedDateTime]
  case class LitJson(value: Json) extends Lit[Json]
//  case class Ref(exp: CFExp) extends CFExp

  case class FnBase64(exp: E[String]) extends E[String]

  case class FnAnd(cond1: E[Boolean], cond2: E[Boolean]) extends E[Boolean]
  case class FnEquals[T](left: E[T], right: E[T]) extends E[Boolean]
  case class FnIf[T](cond: E[Boolean], ifTrue: E[T], ifFalse: E[T]) extends E[T]
  case class FnNot(cond: E[Boolean]) extends E[Boolean]
  case class FnOr(conds: E[Boolean]*) extends E[Boolean]
}
