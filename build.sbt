name := "experiments"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.github.cb372"           %% "scalacache-redis"       % "0.27.0",
  "org.typelevel"              %% "cats-effect"            % "1.2.0",
  "org.typelevel"              %% "cats-core"              % "1.6.0",
  "com.typesafe.slick"         %% "slick"                  % "3.3.0",
  "com.github.alexarchambault" %% "argonaut-shapeless_6.2" % "1.2.0-M4",
  "com.beachape"               %% "enumeratum"             % "1.5.13"
)

resolvers += Resolver.sonatypeRepo("releases")

