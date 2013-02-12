package models

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._
import java.sql.Timestamp
import views.PostDetail
import Schema._

object Commands {
  def newPost(threadID: String, userID: String, content: String) = DB.withSession { implicit session =>
    //Boards.filter(_.id === boardID).map(_.title).first
  }
  
  def newThread(boardID: String, userID: String, subject: String, content: String) = DB.withSession { implicit session =>
    //Boards.filter(_.id === boardID).map(_.title).first
  }
}