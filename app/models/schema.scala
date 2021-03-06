package models
import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted._;
import java.sql.Timestamp

//this is necessary to avoid a compiler optimisation which breaks cloning singletons
trait Tables {
  object Boards extends Table[(String,String)]("Board") {
    def id = column[String]("id", O.PrimaryKey)
    def title = column[String]("title")
    
    def * = id ~ title
  }
  
  object Threads extends Table[(String,String,Timestamp,String,String)]("Thread") {
    def id = column[String]("id", O.PrimaryKey)
    def subject = column[String]("subject")
    def posted = column[Timestamp]("posted")
    def posterID = column[String]("posterID")
    def boardID = column[String]("boardID")
    
    def * = id ~ subject ~ posted ~ posterID ~ boardID
    
    def poster = foreignKey("thread_poster", posterID, Posters) { _.id }
    def board = foreignKey("thread_board", boardID, Boards) { _.id }
  }
  
  object Posts extends Table[(String,String,Timestamp,String,String)]("Post") {
    def id = column[String]("id", O.PrimaryKey)
    def content = column[String]("content")
    def posted = column[Timestamp]("posted")
    def posterID = column[String]("posterID")
    def threadID = column[String]("threadID")
    
    def * = id ~ content ~ posted ~ posterID ~ threadID
    
    def poster = foreignKey("post_poster", posterID, Posters) { _.id }
    def thread = foreignKey("post_thread", threadID, Threads) { _.id }
  }
  
  object Posters extends Table[(String,String,Timestamp)]("Poster") {
    def id = column[String]("id", O.PrimaryKey)
    def username = column[String]("username")
    def registered = column[Timestamp]("registered")
    
    def * = id ~ username ~ registered
  }
}

object Schema extends Tables