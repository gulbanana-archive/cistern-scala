package models

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._
import java.sql.Timestamp
import scala.collection.JavaConversions._
import views.PostDetail
import Schema._
import scala.collection.immutable.StringOps

object Commands {
  def newPost(threadID: String, userID: String, content: String) = DB.withSession { implicit session =>
    Posts.insert((makeID(content), content, now, userID, threadID))
  }
  
  def newThread(boardID: String, userID: String, subject: String, content: String) = DB.withSession { implicit session =>
    val threadID = makeID(subject)
    val postID = makeID(content)
    Threads.insert((threadID, subject, now, userID, boardID))
    Posts.insert((postID, content, now, userID, threadID))
  }
  
  private def now = new Timestamp(System.currentTimeMillis)
  
  private def makeID(data: String) = {
    val alphanum = data.replaceAll("[^a-zA-Z0-9\\s]", "")
    val first5 = alphanum.split(" ", 5).take(5)
    first5.mkString("-")
  }
}