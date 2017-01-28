name := "SimpleJSONParser"

version := "1.0"

scalaVersion := "2.11.8"

mainClass in assembly := Option("shubham1556.SimpleJSONParser")

libraryDependencies ++= Seq(
  "net.liftweb" % "lift-json_2.11" % "2.6.3"

)
    