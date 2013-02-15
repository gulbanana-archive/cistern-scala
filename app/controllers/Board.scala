package controllers

import play.api._
import play.api.mvc._
import models.Queries._
import models.Commands
import views._

object Board extends Controller {
  
  val formatter = new java.text.SimpleDateFormat("MMM d h:m a")
  
  def view(id: String) = Action {
    val title = getBoardTitle(id)
    val threads = getBoardThreads(id)
    
    Ok(views.html.board(BoardHeader(id, title), threads.map {
      thread => ThreadDetail(
        ThreadHeader(thread.threadid, thread.subject),
        thread.postcount, 
        UserHeader(thread.first.userid, thread.first.username), formatter.format(thread.first.date),
        UserHeader(thread.last.userid, thread.last.username), formatter.format(thread.last.date))
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