package models
import play.api.db.slick.Config.driver.simple._

object Boards extends Table[(Int, String)]("Board") {
  def id = column[Int]("id", O.PrimaryKey)
  def name = column[String]("name")
  def * = id ~ name
}

object Threads extends Table[(Int, String)]("Thread") {
  def id = column[Int]("id", O.PrimaryKey)
  def name = column[String]("name")
  def * = id ~ name
}