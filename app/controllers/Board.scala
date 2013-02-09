package controllers

import play.api._
import play.api.mvc._
import models.Repository._
import java.text.SimpleDateFormat
import views.UserHeader

object Board extends Controller {
  
  val formatter = new SimpleDateFormat("yyyy-MM-dd")
  
  def view(id: String) = Action {
    val board = getBoardThreads(id)
    Ok(views.html.board(board.title, board.threads map {
      thread => (thread.threadid, thread.subject, UserHeader(thread.userid, thread.author), formatter.format(thread.date))
    } ))
  }
  
  def post(id: String) = TODO
}