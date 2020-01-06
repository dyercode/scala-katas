import Dependencies._
import common._

name := "scala-katas"

scalaVersion in ThisBuild := "2.13.1"

lazy val fizzbuzz = (
  KataProject("fizzbuzz")
    settings (libraryDependencies ++= fizzbuzzDependencies)
)

lazy val evercraft = (
  KataProject("evercraft")
    settings (libraryDependencies ++= evercraftDependencies)
)

lazy val evercraftF = (
  KataProject("evercraftF")
    settings (libraryDependencies ++= evercraftFDependencies)
)

lazy val evercraftFr = (
  KataProject("evercraftFR")
    settings (libraryDependencies ++= evercraftFrDependencies)
    settings (scalacOptions += "-Ymacro-annotations")
)

lazy val fibonacci = (
  KataProject("fibonacci")
    settings (libraryDependencies ++= fibonacciDependencies)
)

lazy val bowling = (
  KataProject("bowling")
    settings (libraryDependencies ++= bowlingDependencies)
)

lazy val leap = (
  KataProject("leap")
    settings (libraryDependencies ++= leapDependencies)
)

lazy val chop = (
  KataProject("chop")
    settings (libraryDependencies ++= leapDependencies)
)

lazy val triangle = (
  KataProject("triangle")
    settings (libraryDependencies ++= triangleDependencies)
)
