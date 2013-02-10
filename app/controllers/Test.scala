package controllers

import play.api._
import play.api.mvc._
import views._
import java.util.Date

object Test extends Controller {
  
  def board = Action {
    val threads = Seq(
      ("predef", "Predefined Thread", UserHeader("predef", "tim"), "Predefined Date", 7)
    )
    
    Ok(views.html.board("predef", "Predefined Board", threads))
  }
  
  def thread = Action {
    def p(user: String, content: String) = views.PostDetail(
      "predef", "predef", user, "/assets/img/" + user + ".png", content, new Date(System.currentTimeMillis)
    )
    
    val posts = Seq (
      p("tim", "previous game my entourage consisted of a harlequin baby, an aborted foetus, a dead baby's soul and one of those tumours that's actually a twin"),
      p("thomas", "argh like the kind where they strangle each other in the womb?"),
      p("tim", "no, like that thing where someone thinks they have a tumor and when the doctors open them up to cut it out, it turns out they've had a siamese twin their whole life and the tumor has teeth and hair and such"),
      p("tim", "i think it's a type of teratoma?"),
      p("thomas", "is that really a twin though it's just like a extra growth of certain bits"),
      p("tim", "actual twins have apparently been reported but it's apparenly much more often just growths of certain bits"),
      p("tim", "like, just eyes or spines or teeth or w/e")
    )
      
    Ok(views.html.thread("predef", "Predefined Thread", views.BoardHeader("predef", "Predefined Board"), posts))
  }
  
  def post = Action {
    val post = views.PostDetail("predef", "predef", "Predefined Poster", "/assets/img/thomas.png", "Predefined Post", new Date(System.currentTimeMillis))
    Ok(views.html.single(BoardHeader("predef", "Predefined Board"),
                         ThreadHeader("predef", "Predefined Thread"),
                         post))
  }
  
  def user = TODO
  
  def generateTestData = Action {
    models.TestRepository.createAll()
    Redirect(routes.Board.view("cistern"))
  }
 
}