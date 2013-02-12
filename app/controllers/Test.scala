package controllers

import play.api._
import play.api.mvc._
import views._
import java.util.Date

object Test extends Controller {
  def generateTestData = Action {
    models.TestRepository.createAll()
    Redirect(routes.Board.view("cistern"))
  }
}