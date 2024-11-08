import sbt._
import Keys._

object Dependencies {
  // Versions
  object Versions {
    val scalaTest = "3.2.19"
    val scalaTestCheck = "3.2.10.0"
    val scalaCheck = "1.18.1"
    val iron = "2.6.0"
    val cats = "2.12.0"
  }

  // Libraries
  val scalaTest = "org.scalatest" %% "scalatest" % Versions.scalaTest
  val scalacheck = "org.scalacheck" %% "scalacheck" % Versions.scalaCheck
  val scalaTestCheck =
    "org.scalatestplus" %% "scalacheck-1-15_3" % Versions.scalaTestCheck // keep in mind that this exists, but not working atm
  val iron = "io.github.iltotore" %% "iron" % Versions.iron
  val cats = "org.typelevel" %% "cats-core" % Versions.cats

  // Projects
  val fizzbuzzDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val evercraftDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val evercraftFDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val evercraftFrDependencies: Seq[ModuleID] =
    Seq(scalaTest, scalacheck).map(_ % Test)
  val fibonacciDependencies: Seq[ModuleID] =
    Seq(scalacheck % Test, scalaTest % Test)
  val bowlingDependencies: Seq[ModuleID] = Seq(cats, scalaTest % Test)
  val leapDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val chopDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
  val triangleDependencies: Seq[ModuleID] = Seq(iron, scalaTest % Test)
  val digitsDependencies: Seq[ModuleID] = Seq(iron, scalaTest % Test)
}
