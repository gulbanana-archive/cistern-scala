package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  
  def index = Action {
    Redirect(routes.Application.board("hello"))
  }
  
  def board(id: String) = Action {
    Ok(views.html.board(id))
  }
  
}