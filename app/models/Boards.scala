package models

import scala.slick.driver.PostgresDriver.simple._
import Database.threadLocalSession

object Boards extends Table[(Int, String)]("BOARDS") {
  def id = column[Int]("ID", O.PrimaryKey)
  def name = column[String]("NAME")
  def * = id ~ name
}