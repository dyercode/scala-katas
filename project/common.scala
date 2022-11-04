import sbt._
import Keys._

object common {
  def kataProject(name: String): Project = Project(name, file(name))
}
