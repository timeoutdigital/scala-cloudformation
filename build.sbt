name := "scala-cloudformation"

version := "1.0.0"

scalaVersion := "2.11.8"

val circeVersion = "0.6.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

lazy val commonSettings = Seq(
  scalaVersion := "2.11.8",
  scalacOptions += "-deprecation",
  organization := "com.timeout",
  libraryDependencies ++= Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser"
  ).map(_ % circeVersion) ++
    Seq("org.scalameta" % "scalameta_2.11" % "1.4.0") ++
    Seq(
      "org.scala-lang" % "scala-reflect"  % scalaVersion.value,
      "org.scala-lang" % "scala-compiler" % scalaVersion.value
    )
  ,
  addCompilerPlugin("org.scalameta" % "paradise_2.11.8" % "3.0.0-M5")
)


lazy val macros = (project in file("macros")).
  settings(commonSettings: _*)

lazy val cf = (project in file("cf")).
  settings(commonSettings: _*).
  dependsOn(macros)

lazy val root =
  (project in file(".")).
    aggregate(macros, cf)

