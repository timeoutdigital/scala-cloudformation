package com.timeout.scalacloudformation

import java.time.ZonedDateTime

import io.circe.Json

sealed trait CfExp[T]

object CfExp {
  sealed trait Lit[T] extends CfExp[T] {
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
}
