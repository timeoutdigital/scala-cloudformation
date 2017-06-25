package com.timeout.scalacloudformation

import java.time.{Duration, ZonedDateTime}

import com.timeout.scalacloudformation.Template.Mapping
import io.circe.{Encoder, Json}
import io.circe.syntax._
import shapeless.Witness

trait CfExp[+T]
object CfExp {
  type E[+T] = CfExp[T]

  trait IsLit[T]

  trait Ref[T] {
    def value: T
  }

  object IsLit {
    implicit val stringLit: IsLit[String] = new IsLit[String] {}
    implicit val IntLit: IsLit[Int] = new IsLit[Int] {}
    implicit val longLit: IsLit[Long] = new IsLit[Long] {}
    implicit val doubleLit: IsLit[Double] = new IsLit[Double] {}
    implicit val boolLit: IsLit[Boolean] = new IsLit[Boolean] {}
    implicit val dateTimeLit: IsLit[ZonedDateTime] = new IsLit[ZonedDateTime] {}
    implicit val jsonLit: IsLit[Json] = new IsLit[Json] {}
    implicit val durationLit: IsLit[Duration] = new IsLit[Duration] {}
    implicit def propertyLit[T <: ResourceProperty]: IsLit[T] = new IsLit[T] {}
    implicit def listLit[A: IsLit]: IsLit[List[A]] = new IsLit[List[A]]{}
  }

  case class Lit[T: IsLit](value: T) extends E[T]

  /** The return value for ref depends on the resource
    * All these return types could be typed better (e.g. IP address VS URL)
    * but they are all treated as String.
    * For a more refined behaviour, it seems reasonable to use type members instead
    */
  case class ResourceRef(value: Resource) extends Ref[Resource] with E[String]
  case class ParameterRef(value: Parameter) extends Ref[Parameter] with E[String]
  case class PseudoParameterRef(value: PseudoParameter) extends Ref[PseudoParameter] with E[String]

  case class FnBase64(exp: E[String]) extends E[String]

  case class FnAnd(cond1: E[Boolean], cond2: E[Boolean]) extends E[Boolean]
  case class FnEquals[T](left: E[T], right: E[T]) extends E[Boolean]
  case class FnIf[T](cond: E[Boolean], ifTrue: E[T], ifFalse: E[T]) extends E[T]
  case class FnNot(cond: E[Boolean]) extends E[Boolean]
  case class FnOr(conds: E[Boolean]*) extends E[Boolean]

  case class FnGetAttr private [scalacloudformation](logicalId: String,
                                                     attributeName: String) extends E[String]

  object FnGetAttr {
    def unsafe(v: HasLogicalId, attributeName: String) = FnGetAttr(v.logicalId, attributeName)

    def apply[R <: Resource](resource: R, w: Witness)
                            (implicit hasGetAtt: HasGetAtt[R, w.T]): FnGetAttr =
      FnGetAttr(resource.logicalId, hasGetAtt.attributeName)
  }

  case class FnFindInMap(mapName: Mapping,
                         topLevelKey: CfExp[String],
                         secondLevelKey: CfExp[String]) extends E[String]

  case class FnJoin private[scalacloudformation](delimiter: String,
                                                 values: List[Json]) extends E[String]

  object FnJoin {
    import shapeless._
    import shapeless.ops.hlist._
    import Encoding._

    object encodeExp extends Poly1 {
      implicit def exp2Json[A: Encoder : IsLit] = at[CfExp[A]](_.asJson)
    }

    def apply[T <: Product, L1 <: HList, L2 <: HList](separator: String, tuple: T)(
      implicit
      gen: Generic.Aux[T, L1],
      mapper: Mapper.Aux[encodeExp.type, L1, L2],
      traversable: ToTraversable.Aux[L2, List, Json])
    : CfExp[String] =
      FnJoin(separator, gen.to(tuple).map(encodeExp).toList)
  }
}
