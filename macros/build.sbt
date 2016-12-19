val circeVersion = "0.7.0-M1"

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
    val temp = System.getProperty("java.io.tmpdir")
    val tempFile = new File(s"$temp/cf-specs.json")
    println(s"Trying to get specs file from $tempFile")
    // Having a cache outside of the project is handy when using `sbt clean` often
    if (!tempFile.exists) {
      println("Getting specifications from AWS...")
      IO.download(new URL("https://d3teyb21fexa9r.cloudfront.net/latest/CloudFormationResourceSpecification.json"), tempFile)
    }
    IO.copyFile(tempFile, targetFile)
  }

  Seq(targetFile)
}.taskValue

