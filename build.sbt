import Dependencies._
import common._

name := "scala-katas"

ThisBuild / scalaVersion := "3.2.1"

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
