package controllers

import play.api._
import play.api.mvc._
import models.Repository._
import views._

object Thread extends Controller {
  def view(id: String, page: Int) = Action {
    val thread = getThreadPosts(id)
    
    Ok(views.html.thread(id, thread.subject, views.BoardHeader(thread.boardid, thread.board), thread.posts, NewPost.form))
  }
  
  def viewFirst(id: String) = view(id, 1)
  
  def viewLast(id: String) = TODO
  
  def post(id: String) = Action { implicit Request =>
    NewPost.form.bindFromRequest.fold(failedForm => {
      val thread = getThreadPosts(id)
      Ok(views.html.thread(id, thread.subject, views.BoardHeader(thread.boardid, thread.board), thread.posts, failedForm))
    }, success => {
      Redirect(routes.Thread.viewFirst(id)) //XXX change this to viewLast once that's implemented 
    } 
    )
  }
}