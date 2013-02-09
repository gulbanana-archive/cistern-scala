package controllers

import play.api._
import play.api.mvc._
import models.Repository._

object Board extends Controller {
  
  def view(id: String) = Action {
    val board = getBoardThreads(id)
    Ok(views.html.board(board.title, board.threads))
  }
  
  def post(id: String) = TODO
}