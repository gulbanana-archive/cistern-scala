package controllers

import play.api._
import play.api.mvc._
import models.Repository._

object Thread extends Controller {
  def view(id: String, page: Int) = Action {
    val thread = getThreadPosts(id)
    
    Ok(views.html.thread(thread.subject, thread.board, thread.posts))
  }
  
  def post(id: String) = TODO
}