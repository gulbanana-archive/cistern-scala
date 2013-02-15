package models

import scala.math.Ordering
import scala.slick.jdbc.{GetResult, StaticQuery=>Q}
import java.sql.Timestamp
import play.api.Play.current
import play.api.Logger
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._
import views.PostDetail
import Schema._

object Queries {
  implicit val timestampOrdering: Ordering[Timestamp] = Ordering.fromLessThan(_ before _)
  
  /**
   * does what it says on the tin
   */
  def getBoardTitle(boardID: String) = DB.withSession { implicit session =>
    Boards.filter(_.id === boardID).map(_.title).first
  }
  
  /**
   * returns a list of the threads in a board, with their first and last posts
   */
  case class DatedAuthor(userid: String, username: String, date: Timestamp)
  case class BoardThread(threadid: String, subject: String, postcount: Int, first: DatedAuthor, last: DatedAuthor)
  implicit val getThreadResult = GetResult(r => BoardThread(r.<<, r.<<, r.<<, DatedAuthor(r.<<, r.<<, r.<<), DatedAuthor(r.<<, r.<<, r.<<)))
  def getBoardThreads(boardID: String) = DB.withSession { implicit session =>
    val threadsQuery = Q.query[String, BoardThread]("""
        select "Thread"."id", "Thread"."subject", count(lastpost), firstposter."id", firstposter."username", "Thread"."posted", min(lastpost)."id", min(lastpost)."username", min(lastpost)."posted"
        from "Thread"
        inner join "Poster" firstposter on "Thread"."posterID"=firstposter."id" 
        inner join (select p."threadID", p."posted", u."id", u."username"
                    from "Post" p
                    inner join "Poster" u on p."posterID" = u."id"
                    order by p."posted" desc) lastpost on thread."id"=lastpost."threadID"
        where "Thread"."boardID"=?
        group by "Thread"."id", "Thread"."subject", 0, firstposter."id", firstposter."username", "Thread"."posted"
    """)
    
    threadsQuery(boardID).list
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
    } yield (p.id, u.id, u.username, ConstColumn("/assets/images/") ++ u.id ++ ".png", p.content, p.posted)
      
    ThreadContents(subject, boardid, title, postsQuery.sortBy(_._6).list.map(PostDetail.tupled))
  }
  
  case class PostContext(threadid: String, threadtitle: String, boardid: String, boardtitle: String)
  def getPost(postID: String) = DB.withSession { implicit session =>
    val postsQuery = for {
      p <- Posts if p.id === postID
      u <- p.poster
    } yield (p.id, u.id, u.username, ConstColumn("/assets/images/") ++ u.id ++ ".png", p.content, p.posted)
    
    val ctxQuery = for {
      p <- Posts if p.id === postID
      t <- p.thread
      b <- t.board
    } yield (t.id, t.subject, b.id, b.title)
      
    (PostContext.tupled(ctxQuery.first), PostDetail.tupled(postsQuery.first))
  }
  
  case class ThreadAndBoard(subject: String, board: String, title: String)
  def getThreadAndBoard(threadID: String) = DB.withSession { implicit session =>
    val query = for {
      t <- Threads if t.id === threadID
      b <- t.board
    } yield (t.subject, b.id, b.title)
    
    ThreadAndBoard.tupled(query.first)
  }
}