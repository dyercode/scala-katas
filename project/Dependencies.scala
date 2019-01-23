import sbt._
import Keys._

object Dependencies {
  // Versions

  // Libraries
  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  val parserCombinators = "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.1"

  // Projects
  val fizzbuzzDependencies: Seq[ModuleID] = Seq(scalaTest % Test)

  val evercraftDependencies: Seq[ModuleID] = Seq(scalaTest % Test)

  val evercraftFDependencies: Seq[ModuleID] = Seq(scalaTest % Test)

  val bowlingDependencies: Seq[ModuleID] = Seq(parserCombinators, scalaTest % Test)

  val leapDependencies: Seq[ModuleID] = Seq(scalaTest % Test)

  val chopDependencies: Seq[ModuleID] = Seq(scalaTest % Test)

  val triangleDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
}
