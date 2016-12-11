name := "alphasorter"

organization := "com.delprks"

version := "0.0.1"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

homepage := Some(url("http://github.com/delprks/alphasorter"))

scalaVersion := "2.12.1"

// Dependencies
libraryDependencies ++= {
  val specs2Version = "3.8.6"
  val scalaCheckVersion = "1.12.6"
  val configVersion = "1.3.1"

  Seq(
    "org.specs2"     %% "specs2-core"  % specs2Version     % "test",
    "org.specs2"     %% "specs2-junit" % specs2Version     % "test",
    "org.specs2"     %% "specs2-mock"  % specs2Version     % "test",
    "org.scalacheck" %% "scalacheck"   % scalaCheckVersion % "test",
    "com.typesafe"   %  "config"       % configVersion
  )
}

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature"
)

// Test properties
lazy val coverageSettings = Seq(
  coverageMinimum := 99,
  coverageFailOnMinimum := true
)

parallelExecution in Test := true

testOptions in Test += Tests.Argument(TestFrameworks.Specs2, "-u", "console", "junitxml")

testOptions in Test += Tests.Setup(() => System.setProperty("specs2.junit.outDir", "target/reports"))

addCommandAlias("full-test", ";clean;scalastyle;coverage;test;coverageReport") ++ coverageSettings
