package controllers

import play.api._
import play.api.mvc._
import views.UserHeader

object Test extends Controller {
  
  def board = Action {
    val threads = Seq(
      ("predef", "Predefined Thread", UserHeader("predef", "tim"), "Predefined Date")
    )
    
    Ok(views.html.board("Predefined Board", threads))
  }
  
  def thread = Action {
    val posts = Seq (
      views.PostDetail("predef", "predef", "tim", "/assets/img/tim.jpg", "previous game my entourage consisted of a harlequin baby, an aborted foetus, a dead baby's soul and one of those tumours that's actually a twin"),
      views.PostDetail("predef", "predef", "thomas", "/assets/img/thomas.png", "argh like the kind where they strangle each other in the womb?"),
      views.PostDetail("predef", "predef", "tim", "/assets/img/tim.jpg", "no, like that thing where someone thinks they have a tumor and when the doctors open them up to cut it out, it turns out they've had a siamese twin their whole life and the tumor has teeth and hair and such"),
      views.PostDetail("predef", "predef", "tim", "/assets/img/tim.jpg", "i think it's a type of teratoma?"),
      views.PostDetail("predef", "predef", "thomas", "/assets/img/thomas.png", "is that really a twin though it's just like a extra growth of certain bits"),
      views.PostDetail("predef", "predef", "tim", "/assets/img/tim.jpg", "actual twins have apparently been reported but it's apparenly much more often just growths of certain bits"),
      views.PostDetail("predef", "predef", "tim", "/assets/img/tim.jpg", "like, just eyes or spines or teeth or w/e")
    )
      
    Ok(views.html.thread("Predefined Thread", "board name not available", posts))
  }
  
  def post = Action {
    val post = views.PostDetail("predef", "predef", "Predefined Poster", "/assets/img/thomas.png", "Predefined Post")
    Ok(views.html.single(post))
  }
  
  def user = TODO
  
  def generateTestData = Action {
    models.TestRepository.createAll()
    Ok("Test data generated.")
  }
 
}