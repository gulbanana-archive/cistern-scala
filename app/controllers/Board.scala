package controllers

import play.api._
import play.api.mvc._

object Board extends Controller {
  
  def view(id: String) = Action {
    Ok(views.html.board(id, models.Thread.byBoard(id)))
  }
  
  def post(id: String) = TODO
}