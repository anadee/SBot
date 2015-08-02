name := "SBot"

version := "1.0"

scalaVersion := "2.11.7"

val akkaVersion = "2.3.12"

libraryDependencies += "net.codingwell" %% "scala-guice" % "4.0.0"

libraryDependencies += "org.json4s" %% "json4s-native" % "3.2.11"

libraryDependencies += "com.typesafe" % "config" % "1.3.0"

libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "1.1.5"

libraryDependencies += "io.spray" %%  "spray-json" % "1.3.2"

libraryDependencies += "org.ow2.asm" % "asm-debug-all" % "5.0.4"

libraryDependencies += "com.github.arnabk" % "pgslookandfeel" % "1.1.4"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-kernel" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "ch.qos.logback" % "logback-classic" % "1.0.7"
)


