package com.timeout

import kantan.csv._
import kantan.csv.ops._
import scala.io.Source

object GetAttSupport {
  case class Attribute(resourceTypeFqn: String, attributes: List[String])

  lazy val attributesByResourceType: Map[String, Attribute] = {
    val res = getClass.getResource("/com/timeout/getatt-properties.csv")
    val csvS = Source.fromURL(res).mkString

    csvS
      .asUnsafeCsvReader[(String, String)](rfc.withHeader)
      .toVector.groupBy(_._1).map { case (fqn, vs) =>
      fqn -> Attribute(fqn, vs.map(_._2).toList)
    }
  }
}
