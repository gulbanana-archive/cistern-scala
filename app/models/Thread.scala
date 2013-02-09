package models

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted._
//import Database.threadLocalSession

case class Thread(board: String, author: String, title: String)

object Thread {
  def byBoard(boardID: String) = {
    DB.withSession { implicit session => 
      val boardthreads = for {
        t <- Threads 
        b <- t.board
        u <- t.user
      } yield (b.title, u.username, t.subject)
      
      boardthreads.list.map { case (board, user, thread) => Thread(board, user, thread) }
    }
  }
}