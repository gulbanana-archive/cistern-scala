package controllers

import play.api._
import play.api.mvc._
import models._

object Test extends Controller {
  
  def board = Action {
    val threads = Seq(
      ("predef", "tim", "Predefined Thread", "Predefined Date")
    )
    
    Ok(views.html.board("Predefined Board", threads))
  }
  
  def thread = Action {
    val posts = Seq (
      PostDetail("predef", "tim", "/assets/img/tim.jpg", "previous game my entourage consisted of a harlequin baby, an aborted foetus, a dead baby's soul and one of those tumours that's actually a twin"),
      PostDetail("predef", "thomas", "/assets/img/thomas.png", "argh like the kind where they strangle each other in the womb?"),
      PostDetail("predef", "tim", "/assets/img/tim.jpg", "no, like that thing where someone thinks they have a tumor and when the doctors open them up to cut it out, it turns out they've had a siamese twin their whole life and the tumor has teeth and hair and such"),
      PostDetail("predef", "tim", "/assets/img/tim.jpg", "i think it's a type of teratoma?"),
      PostDetail("predef", "thomas", "/assets/img/thomas.png", "is that really a twin though it's just like a extra growth of certain bits"),
      PostDetail("predef", "tim", "/assets/img/tim.jpg", "actual twins have apparently been reported but it's apparenly much more often just growths of certain bits"),
      PostDetail("predef", "tim", "/assets/img/tim.jpg", "like, just eyes or spines or teeth or w/e")
    )
      
    Ok(views.html.thread("Predefined Thread", "board name not available", posts))
  }
  
  def post = Action {
    val post = PostDetail("predef", "Predefined Poster", "/assets/img/thomas.png", "Predefined Post")
    Ok(views.html.single(post))
  }
  
  def generateTestData = Action {
    Ok("Test data generated.")
  }
 
}