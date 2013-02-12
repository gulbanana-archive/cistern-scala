package controllers

import play.api._
import play.api.mvc._
import models.Queries._
import models.Commands
import views._

object Thread extends Controller {
  def viewPage(id: String, page: Int) = Action {
    val thread = getThreadPosts(id)
    
    Ok(views.html.thread(ThreadHeader(id, thread.subject), BoardHeader(thread.boardid, thread.board), thread.posts, NewPost.form))
  }
  
  def view(id: String) = viewPage(id, 1)
  
  def viewLast(id: String) = TODO
  
  def addpost(id: String) = Action {
    val ThreadAndBoard(subject, board, title) = getThreadAndBoard(id)
    Ok(views.html.addpost(ThreadHeader(id, subject), BoardHeader(board, title), NewPost.form))
  }
  
  def post(id: String) = Action { implicit Request =>
    NewPost.form.bindFromRequest.fold(failedForm => {
      val ThreadAndBoard(subject, board, title) = getThreadAndBoard(id)
      BadRequest(views.html.addpost(ThreadHeader(id, subject), BoardHeader(board, title), failedForm))
    }, success => {
      Commands.newPost(id, "anonymous", success.contents)
      Redirect(routes.Thread.view(id)) //XXX change this to viewLast once that's implemented 
    })
  }
}