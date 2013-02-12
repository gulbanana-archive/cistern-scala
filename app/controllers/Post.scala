package controllers

import play.api._
import play.api.mvc._
import models.Repository._
import views._

object Post extends Controller {
  def view(id: String) = Action {
    val (context, post) = getPost(id)
    Ok(html.viewpost(BoardHeader(context.boardid, context.boardtitle),
                     ThreadHeader(context.threadid, context.threadtitle),
                     post))
  }
}