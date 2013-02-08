package controllers

import play.api._
import play.api.mvc._

object Board extends Controller {
  
  def view(id: String) = Action {
    val threads = List(
      models.Thread(id, "Badposter", "where is echeyakee lair"),
      models.Thread(id, "Very badposter", "Mankrik's Wife"),
      models.Thread(id, "Complete shitposter", "bring me the heart of isha awak")
    )
    
    Ok(views.html.board(id, threads))
  }
  
  def post = TODO
}