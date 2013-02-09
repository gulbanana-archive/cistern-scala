package controllers

import play.api._
import play.api.mvc._
import models.Repository._
import java.text.SimpleDateFormat

object Board extends Controller {
  
  val formatter = new SimpleDateFormat("yyyy-MM-dd")
  
  def view(id: String) = Action {
    val board = getBoardThreads(id)
    Ok(views.html.board(board.title, board.threads map {
      thread => (thread.id, thread.author, thread.subject, formatter.format(thread.date))
    } ))
  }
  
  def post(id: String) = TODO
}