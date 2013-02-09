package controllers

import play.api._
import play.api.mvc._
import models.Repository._

object Post extends Controller {
  
  def view(id: String) = Action {
    Ok(views.html.post(getPost(id)))
  }
  
  def post(id: String) = TODO
}