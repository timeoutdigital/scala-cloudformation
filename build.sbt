name := "scala-cf"

version := "1.0"

scalaVersion := "2.11.8"

val circeVersion = "0.6.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser",
  "io.circe" %% "circe-optics"
).map(_ % circeVersion)

lazy val commonSettings = Seq(
  scalacOptions += "-deprecation",
  organization := "com.timeout",
  libraryDependencies ++= Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser"
  ).map(_ % circeVersion) ++
    Seq("org.scalatest" %% "scalatest" % "2.2.6" % "test")
)

lazy val macros = (project in file("macros")).
  settings(commonSettings: _*)

lazy val cf = (project in file("cf")).
  settings(commonSettings: _*).
  dependsOn(macros)

lazy val root =
  (project in file(".")).
    aggregate(macros, cf)

