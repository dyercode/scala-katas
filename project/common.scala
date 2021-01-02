import sbt._
import Keys._

object common {
  def KataProject(name: String): Project = Project(name, file(name))
}
