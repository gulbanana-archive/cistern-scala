package controllers

import play.api._
import play.api.mvc._
import models.Post

object Thread extends Controller {
  def view(id: String, page: Int) = Action {
    val posts = List(
      Post("tim", "/assets/img/tim.jpg", "previous game my entourage consisted of a harlequin baby, an aborted foetus, a dead baby's soul and one of those tumours that's actually a twin"),
      Post("thomas", "/assets/img/thomas.png", "argh like the kind where they strangle each other in the womb?"),
      Post("tim", "/assets/img/tim.jpg", "no, like that thing where someone thinks they have a tumor and when the doctors open them up to cut it out, it turns out they've had a siamese twin their whole life and the tumor has teeth and hair and such"),
      Post("tim", "/assets/img/tim.jpg", "i think it's a type of teratoma?"),
      Post("thomas", "/assets/img/thomas.png", "is that really a twin though it's just like a extra growth of certain bits"),
      Post("tim", "/assets/img/tim.jpg", "actual twins have apparently been reported but it's apparenly much more often just growths of certain bits"),
      Post("tim", "/assets/img/tim.jpg", "like, just eyes or spines or teeth or w/e")
    )
    
    Ok(views.html.thread(id, posts))
  }
}