name := "alphasorter"

organization := "com.delprks"

version := "0.0.1"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.specs2"        %% "specs2-core"  % "3.7.2"  % "test",
  "org.specs2"        %% "specs2-junit" % "3.7.2"  % "test",
  "org.specs2"        %% "specs2-mock"  % "3.7.2"  % "test",
  "org.scalacheck"    %% "scalacheck"   % "1.12.4" % "test",
  "com.typesafe"      %  "config"       % "1.3.1"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature"
)

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

homepage := Some(url("http://github.com/delprks/alphasorter"))
