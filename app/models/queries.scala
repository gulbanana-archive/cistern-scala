package models

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._
import java.sql.Timestamp

case class PostDetail(id: String, author: String, avatar: String, content: String)

object Repository {
  
  //XXX this uses two queries, is that more efficient than one which returns redundant titles?
  case class BoardWithThreads(title: String, threads: Seq[BoardThread])
  case class BoardThread(id: String, author: String, subject: String, date: Timestamp)
  def getBoardThreads(boardID: String) = {
    DB.withSession { implicit session =>
      val title = Boards.filter(_.id === boardID).map(_.title).first
      
      val threadsQuery = for {
        t <- Threads 
        b <- t.board if b.id === boardID
        u <- t.poster
      } yield (t.id, u.username, t.subject, t.posted)
      
      BoardWithThreads(title, threadsQuery.list.map(BoardThread.tupled))
    }
  }
  
  case class ThreadContents(subject: String, board: String, posts: Seq[PostDetail])
  def getThreadPosts(threadID: String) = {
    null.asInstanceOf[ThreadContents]
  }
  
  def getPost(postID: String) = {
    null.asInstanceOf[PostDetail]
  }
}

object TestRepository {
  def createAll {
    DB.withSession { implicit Session =>
      
    }
  }
}