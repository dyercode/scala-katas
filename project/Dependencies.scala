import sbt._
import Keys._

object Dependencies {
  // Versions

  // Libraries
  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  val parserCombinators = "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"

  // Projects
  val fizzbuzzDependencies = Seq(scalaTest % "test")

  val evercraftDependencies = Seq(scalaTest % "test")

  val evercraftFDependencies = Seq(scalaTest % "test")

  val bowlingDependencies = Seq(parserCombinators, scalaTest % "test")

  val leapDependencies = Seq(scalaTest % "test")

  val chopDependencies = Seq(scalaTest % "test")
}
