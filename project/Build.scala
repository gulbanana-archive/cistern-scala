import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "cistern"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    jdbc
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // play-slick is a preview version of the slick integration coming in 2.2      
  ).dependsOn(RootProject( uri("git://github.com/freekh/play-slick.git") ))

}
