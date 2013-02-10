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
    templatesImport += "views._"
    //requireJs += "lib/module"
    //lessEntryPoints <<= baseDirectory(_ / "app" / "assets" / "stylesheets" ** "whatever.less")
  ).dependsOn(RootProject( uri("git://github.com/freekh/play-slick.git") ))

}
