val circeVersion = "0.6.1"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
)++ Seq(
"io.circe" %% "circe-core",
"io.circe" %% "circe-generic",
"io.circe" %% "circe-parser"
).map(_ % circeVersion) ++
Seq("org.scalameta" % "scalameta_2.11" % "1.4.0")

libraryDependencies += "org.scala-lang" % "scala-reflect"  % scalaVersion.value

libraryDependencies += "org.scala-lang" % "scala-compiler" % scalaVersion.value


(resourceGenerators in Compile) += Def.task {
  val targetFile = (resourceManaged in Compile).value / "cf-specs.json"

  if (!targetFile.exists) {
    println("Getting specifications from AWS...")
    IO.download(new URL("https://d3teyb21fexa9r.cloudfront.net/latest/CloudFormationResourceSpecification.json"), targetFile)
  }

  Seq(targetFile)
}.taskValue

