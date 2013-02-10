package controllers

import play.api._
import play.api.mvc._
import models.Repository._
import views._

object Thread extends Controller {
  def view(id: String, page: Int) = Action {
    val thread = getThreadPosts(id)
    
    Ok(views.html.thread(id, thread.subject, views.BoardHeader(thread.boardid, thread.board), thread.posts))
  }
  
  def post(id: String) = TODO
}