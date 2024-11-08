import Dependencies._
import common._

name := "scala-katas"

ThisBuild / scalaVersion := "3.6.1"
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision
ThisBuild / scalacOptions += "-Wunused:all"

lazy val fizzbuzz = (
  kataProject("fizzbuzz")
    settings (libraryDependencies ++= fizzbuzzDependencies)
)

lazy val evercraft = (
  kataProject("evercraft")
    settings (libraryDependencies ++= evercraftDependencies)
)

lazy val evercraftF = (
  kataProject("evercraftF")
    settings (libraryDependencies ++= evercraftFDependencies)
)

lazy val evercraftFr = (
  kataProject("evercraftFR")
    settings (libraryDependencies ++= evercraftFrDependencies)
)

lazy val fibonacci = (
  kataProject("fibonacci")
    settings (libraryDependencies ++= fibonacciDependencies)
)

lazy val bowling = (
  kataProject("bowling")
    settings (libraryDependencies ++= bowlingDependencies)
)

lazy val leap = (
  kataProject("leap")
    settings (libraryDependencies ++= leapDependencies)
)

lazy val chop = (
  kataProject("chop")
    settings (libraryDependencies ++= leapDependencies)
)

lazy val triangle = (
  kataProject("triangle")
    settings (libraryDependencies ++= triangleDependencies)
)

lazy val digits = (
  kataProject("digits")
    settings (libraryDependencies ++= digitsDependencies)
)
