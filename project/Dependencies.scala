import sbt._
import Keys._

object Dependencies {
  // Versions
  object Versions {
    val scalaTest = "3.2.10"
    val scalaTestCheck = "3.2.10.0"
    val scalaCheck = "1.15.4"
  }

  // Libraries
  val scalaTest = "org.scalatest" %% "scalatest" % Versions.scalaTest
  val scalacheck = "org.scalacheck" %% "scalacheck" % Versions.scalaCheck
  val scalaTestCheck =
    "org.scalatestplus" %% "scalacheck-1-15_3" % Versions.scalaTestCheck // keep in mind that this exists, but not working atm

  // Projects
  val fizzbuzzDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val evercraftDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val evercraftFDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val evercraftFrDependencies: Seq[ModuleID] =
    Seq(scalaTest, scalacheck).map(_ % Test)
  val fibonacciDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val bowlingDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val leapDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val chopDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val triangleDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
}
