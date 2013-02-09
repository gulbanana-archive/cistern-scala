package controllers

import play.api._
import play.api.mvc._
import models.Repository._

object Post extends Controller {
  def view(id: String) = Action {
    val post = getPost(id)
    Ok(views.html.single(post))
  }
}