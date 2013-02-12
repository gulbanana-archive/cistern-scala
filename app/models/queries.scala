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
  
  case class ThreadContents(subject: String, boardid: String, board: String, posts: Seq[PostDetail])
  def getThreadPosts(threadID: String) = DB.withSession { implicit session =>
    val threadQuery = for {
      t <- Threads if t.id === threadID
      b <- t.board
    } yield (t.subject, b.id, b.title)
    val (subject, boardid, title) = threadQuery.first
      
    val postsQuery = for {
      p <- Posts if p.threadID === threadID
      u <- p.poster
    } yield (p.id, u.id, u.username, ConstColumn("/assets/img/") ++ u.id ++ ".png", p.content, p.posted)
      
    ThreadContents(subject, boardid, title, postsQuery.sortBy(_._6).list.map(PostDetail.tupled))
  }
  
  case class PostContext(threadid: String, threadtitle: String, boardid: String, boardtitle: String)
  def getPost(postID: String) = DB.withSession { implicit session =>
    val postsQuery = for {
      p <- Posts if p.id === postID
      u <- p.poster
    } yield (p.id, u.id, u.username, ConstColumn("/assets/img/") ++ u.id ++ ".png", p.content, p.posted)
    
    val ctxQuery = for {
      p <- Posts if p.id === postID
      t <- p.thread
      b <- t.board
    } yield (t.id, t.subject, b.id, b.title)
      
    (PostContext.tupled(ctxQuery.first), PostDetail.tupled(postsQuery.first))
  }
  
  case class ThreadAndBoard(subject: String, board: String, title: String)
  def getThreadAndBoard(threadID: String) = DB.withSession { implicit session =>
    
    ThreadAndBoard("", "", "")
  }
}