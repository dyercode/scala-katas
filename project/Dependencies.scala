import sbt._
import Keys._

object Dependencies {
  // Versions

  // Libraries
  val scalaTest = "org.scalatest" %% "scalatest" % "3.2.7"
  val scalacheck = "org.scalacheck" %% "scalacheck" % "1.15.3"

  // Projects
  val fizzbuzzDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val evercraftDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val evercraftFDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val evercraftFrDependencies: Seq[ModuleID] = Seq(scalaTest % Test, scalacheck % Test)
  val fibonacciDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val bowlingDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val leapDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val chopDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val triangleDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
}
