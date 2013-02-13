package models
import scala.collection.JavaConversions._
import scala.collection.immutable.StringOps
import java.sql.Timestamp
import java.util.UUID
import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

import views.PostDetail
import Schema._


object Commands {
  def newPost(threadID: String, userID: String, content: String) = DB.withSession { implicit session =>
    var postID = makeID(content)
    
    Posts.insert((postID, content, now, userID, threadID))
  }
  
  def newThread(boardID: String, userID: String, subject: String, content: String) = DB.withSession { implicit session =>
    var threadID = makeID(subject)
    var postID = makeID(content)    
    
    Threads.insert((threadID, subject, now, userID, boardID))
    Posts.insert((postID, content, now, userID, threadID))
    
    threadID
  }
  
  private def now = new Timestamp(System.currentTimeMillis)
  
  private def makeID(data: String) = {
    val alphanum = data.replaceAll("[^a-zA-Z0-9\\s]", "")
    val first5 = alphanum.split(" ", 8).take(7)
    (Base62.encode(UUID.randomUUID) :: first5.toList).mkString("-")
  }
}