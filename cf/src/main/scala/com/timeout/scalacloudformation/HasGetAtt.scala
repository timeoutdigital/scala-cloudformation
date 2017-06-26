package com.timeout.scalacloudformation
import shapeless.Witness

import scala.annotation.implicitNotFound

@implicitNotFound(msg = "FnGetAtt: ${T} is not a known property of ${R}. Please review docs http://amzn.to/1hHXfM4, and consider using FnGetAtt.unsafe if needed.")
trait HasGetAtt[R <: Resource, T] {
  def attributeName: String
}

object HasGetAtt {
  def mk[R <: Resource](w: Witness.Lt[String]): HasGetAtt[R, w.T] = new HasGetAtt[R, w.T] {
    override val attributeName = w.value
  }

  def apply[R <: Resource](w: Witness)(
    implicit
    ev: HasGetAtt[R, w.T]): HasGetAtt[R, w.T] = ev
}
