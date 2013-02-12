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
    var postID = makeID(content, postExists)
    
    Posts.insert((postID, content, now, userID, threadID))
    
    postID
  }
  
  def newThread(boardID: String, userID: String, subject: String, content: String) = DB.withSession { implicit session =>
    var threadID = makeID(subject, threadExists)
    var postID = makeID(content, postExists)    
    
    Threads.insert((threadID, subject, now, userID, boardID))
    Posts.insert((postID, content, now, userID, threadID))
    
    threadID
  }
  
  private def postExists(postID: String) = DB.withSession { implicit session =>
    Posts.filter(_.id === postID).firstOption.nonEmpty
  }
  
  private def threadExists(postID: String) = DB.withSession { implicit session =>
    Posts.filter(_.id === postID).firstOption.nonEmpty
  }
  
  private def now = new Timestamp(System.currentTimeMillis)
  
  private def makeID(data: String, nonUnique: String => Boolean) = {
    val alphanum = data.replaceAll("[^a-zA-Z0-9\\s]", "")
    val first5 = alphanum.split(" ", 5).take(5)
    val putative = first5.mkString("-")
    
    if (nonUnique(putative) || putative == "")
      putative + "-" + UUID.randomUUID().toString()
    else
      putative
  }
}