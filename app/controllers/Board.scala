package controllers

import play.api._
import play.api.mvc._

object Board extends Controller {
  
  def view(id: String) = Action {
    val threads = List(
      models.Thread(id, "tim", "predef")
    )
    
    Ok(views.html.board(id, threads))
  }
  
  def predef = Action {
    val threads = List(
      models.Thread("predef", "tim", "predef")
    )
    
    Ok(views.html.board("Predefined Board", threads))
  }
  
  def post(id: String) = TODO
}