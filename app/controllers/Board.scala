package controllers

import play.api._
import play.api.mvc._
import models.Repository._
import views._

object Board extends Controller {
  
  val formatter = new java.text.SimpleDateFormat("yyyy-MM-dd")
  
  def view(id: String) = Action {
    val board = getBoardThreads(id)
    Ok(views.html.board(id, board.title, board.threads map {
      thread => (thread.threadid, thread.subject, UserHeader(thread.userid, thread.author), formatter.format(thread.date), thread.postcount)
    } ))
  }
  
  def post(id: String) = TODO
}