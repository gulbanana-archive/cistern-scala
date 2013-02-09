package models

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._
import java.sql.Timestamp
import views.PostDetail
import Schema._

object Repository {
  
  //XXX this uses two queries, is that more efficient than one which returns redundant titles?
  case class BoardWithThreads(title: String, threads: Seq[BoardThread])
  case class BoardThread(threadid: String, userid: String, author: String, subject: String, date: Timestamp, postcount: Int)
  def getBoardThreads(boardID: String) = DB.withSession { implicit session =>
    val title = Boards.filter(_.id === boardID).map(_.title).first
      
    val postcountQuery = for {
      p <- Posts
      t <- p.thread if t.boardID === boardID
    } yield (t.id, p.id)
    
    val postcounts = postcountQuery.groupBy { case (threadid, postid) =>
      threadid
    }.map { case (threadid, grouping) =>
      (threadid, grouping.length)
    }.list.toMap.withDefaultValue(0)
    
    val threadsQuery = for {
      t <- Threads
      u <- t.poster
      b <- t.board if b.id === boardID
    } yield (t.id, u.id, u.username, t.subject, t.posted)
    
    val threads = threadsQuery.list.map { case (threadid, userid, username, subject, posted) =>
      BoardThread(threadid, userid, username, subject, posted, postcounts(threadid))
    }
    
    BoardWithThreads(title, threads)
  }
  
  case class ThreadContents(subject: String, board: String, posts: Seq[PostDetail])
  def getThreadPosts(threadID: String) = DB.withSession { implicit session =>
    val threadQuery = for {
      t <- Threads if t.id === threadID
      b <- t.board
    } yield (t.subject, b.title)
    val (subject, title) = threadQuery.first
      
    val postsQuery = for {
      p <- Posts if p.threadID === threadID
      u <- p.poster
    } yield (p.id, u.id, u.username, ConstColumn("/assets/img/") ++ u.id ++ ".png", p.content)
      
    ThreadContents(subject, title, postsQuery.list.map(PostDetail.tupled))
  }
  
  def getPost(postID: String) = DB.withSession { implicit session =>
    val postsQuery = for {
      p <- Posts if p.id === postID
      u <- p.poster
    } yield (p.id, u.id, u.username, "/assets/img/" + u.username + ".png", p.content)
      
    PostDetail.tupled(postsQuery.first)
  }
  
}