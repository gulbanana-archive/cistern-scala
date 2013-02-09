package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  
  var genDone = false
  
  def index = Action {
    if (genDone) {
      Redirect(routes.Board.view("cistern"))
    } else {
      genDone = true
      Redirect(routes.Test.generateTestData)
    }
  }
 
}