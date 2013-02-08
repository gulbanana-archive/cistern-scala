package controllers

import play.api._
import play.api.mvc._
import views.Post

object Thread extends Controller {
  def view(id: String, page: Int) = Action {
    val posts = List(
      Post("tim", "previous game my entourage consisted of a harlequin baby, an aborted foetus, a dead baby's soul and one of those tumours that's actually a twin"),
      Post("thomas", "argh like the kind where they strangle each other in the womb?"),
      Post("tim", "no, like that thing where someone thinks they have a tumor and when the doctors open them up to cut it out, it turns out they've had a siamese twin their whole life and the tumor has teeth and hair and such"),
      Post("tim", "i think it's a type of teratoma?")
    )
    
    Ok(views.html.thread(id, posts))
  }
}