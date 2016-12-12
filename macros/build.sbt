val circeVersion = "0.6.1"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
) ++ Seq(
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
) ++ Seq(
"io.circe" %% "circe-core",
"io.circe" %% "circe-generic",
"io.circe" %% "circe-parser",
"io.circe" %% "circe-optics"
).map(_ % circeVersion)

(resourceGenerators in Compile) += Def.task {
  val targetFile = (resourceManaged in Compile).value / "cf-specs.json"

  if (!targetFile.exists) {
    println("Getting specifications from AWS...")
    IO.download(new URL("https://d3teyb21fexa9r.cloudfront.net/latest/CloudFormationResourceSpecification.json"), targetFile)
  }

  Seq(targetFile)
}.taskValue

