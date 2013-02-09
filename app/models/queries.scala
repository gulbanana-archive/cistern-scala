package models

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._
import java.sql.Timestamp
import views.PostDetail

object Repository {
  
  //XXX this uses two queries, is that more efficient than one which returns redundant titles?
  case class BoardWithThreads(title: String, threads: Seq[BoardThread])
  case class BoardThread(threadid: String, userid: String, author: String, subject: String, date: Timestamp)
  def getBoardThreads(boardID: String) = {
    DB.withSession { implicit session =>
      val title = Boards.filter(_.id === boardID).map(_.title).first
      
      val threadsQuery = for {
        t <- Threads 
        b <- t.board if b.id === boardID
        u <- t.poster
      } yield (t.id, u.id, u.username, t.subject, t.posted)
      
      BoardWithThreads(title, threadsQuery.list.map(BoardThread.tupled))
    }
  }
  
  case class ThreadContents(subject: String, board: String, posts: Seq[PostDetail])
  def getThreadPosts(threadID: String) = {    
    DB.withSession { implicit session =>
      val threadQuery = for {
        t <- Threads if t.id === threadID
        b <- t.board
      } yield (t.subject, b.title)
      val (subject, title) = threadQuery.first
      
      val postsQuery = for {
        p <- Posts if p.threadID === threadID
        u <- p.poster
      } yield (p.id, u.id, u.username, "/assets/img/avatar.png", p.content)
      
      ThreadContents(subject, title, postsQuery.list.map(PostDetail.tupled))
    }
  }
  
  def getPost(postID: String) = {
    DB.withSession { implicit session =>
      val postsQuery = for {
        p <- Posts if p.id === postID
        u <- p.poster
      } yield (p.id, u.id, u.username, "/assets/img/avatar.png", p.content)
      
      PostDetail.tupled(postsQuery.first)
    }
  }
}