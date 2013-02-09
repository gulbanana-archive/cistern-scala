package models
import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted._;
import java.sql.Timestamp

object Boards extends Table[(String,String)]("Board") {
  def id = column[String]("id", O.PrimaryKey)
  def title = column[String]("title")
  
  def * = id ~ title
}

object Threads extends Table[(String,String,Timestamp,String,String)]("Thread") {
  def id = column[String]("id", O.PrimaryKey)
  def subject = column[String]("subject")
  def posted = column[Timestamp]("posted")
  def userID = column[String]("userID")
  def boardID = column[String]("boardID")
  
  def * = id ~ subject ~ posted ~ userID ~ boardID
  
  def user = foreignKey("thread_user", userID, Users) { _.id }
  def board = foreignKey("thread_board", boardID, Boards) { _.id }
}

object Posts extends Table[(String,String,Timestamp,String,String)]("Post") {
  def id = column[String]("id", O.PrimaryKey)
  def content = column[String]("content")
  def posted = column[Timestamp]("posted")
  def userID = column[String]("userID")
  def threadID = column[String]("threadID")
  
  def * = id ~ content ~ posted ~ userID ~ threadID
  
  def user = foreignKey("post_user", userID, Users) { _.id }
  def thread = foreignKey("post_thread", threadID, Threads) { _.id }
}

object Users extends Table[(String,String,Timestamp)]("User") {
  def id = column[String]("id", O.PrimaryKey)
  def username = column[String]("username")
  def registered = column[Timestamp]("registered")
  
  def * = id ~ username ~ registered
}