package models
import play.api.db.slick.Config.driver.simple._

object Boards extends Table[(String, String)]("Board") {
  def id = column[String]("id", O.PrimaryKey)
  def name = column[String]("name")
  def * = id ~ name
}

object Threads extends Table[(String, String)]("Thread") {
  def id = column[String]("id", O.PrimaryKey)
  def name = column[String]("name")
  def * = id ~ name
}