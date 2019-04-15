name := "experiments"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += "com.github.cb372" %% "scalacache-redis" % "0.27.0"
libraryDependencies += "org.typelevel"    %% "cats-effect"      % "1.2.0"
libraryDependencies += "org.typelevel"    %% "cats-core"        % "1.6.0"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick"           % "3.3.0",
  "com.h2database"      % "h2"              % "1.4.185",
  "ch.qos.logback"      % "logback-classic" % "1.1.2"
)