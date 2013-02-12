package controllers

import play.api._
import play.api.mvc._
import models.Queries._
import models.Commands
import views._

object Board extends Controller {
  
  val formatter = new java.text.SimpleDateFormat("yyyy-MM-dd")
  
  def view(id: String) = Action {
    val board = getBoardThreads(id)
    Ok(views.html.board(BoardHeader(id, board.title), board.threads map {
      thread => (thread.threadid, thread.subject, UserHeader(thread.userid, thread.author), formatter.format(thread.date), thread.postcount)
    } ))
  }
  
  def addpost(id: String) = Action {
    val title = getBoardTitle(id)
    Ok(views.html.addthread(BoardHeader(id, title), NewThread.form))
  }
  
  def post(id: String) = Action { implicit Request =>
    NewThread.form.bindFromRequest.fold(failedForm => {
      val title = getBoardTitle(id)
      BadRequest(views.html.addthread(BoardHeader(id, title), failedForm))
    }, success => {
      val threadID = Commands.newThread(id, "anonymous", success.subject, success.contents)
      Redirect(routes.Thread.view(threadID)) 
    })
  }
}