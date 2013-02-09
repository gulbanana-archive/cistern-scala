package models

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import java.sql.Timestamp

object TestRepository {
  private def now = new Timestamp(System.currentTimeMillis)
  
  def createAll() {
    DB.withSession { implicit Session =>
      Posters.insertAll(
        ("banana", "Gul Banana", now),
        ("ferrinus", "FerrinusPrime", now),
        ("VoxPVoxD", "VoxPVoxD", now),
        ("tom", "tom", now)
      )
     
      Boards.insertAll(
        ("cistern", "Cistern Test Board")
      )
      
      Threads.insertAll(
        ("fate-is-the", "Fate is the worst arcanum", now, "ferrinus", "cistern"),
        ("ill-take-the-high-road", "I'll take the high road, and you take the low road", now, "tom", "cistern"),
        ("the-high-road-is-my", "The high road is my road, the low road's the slow road!", now, "tom", "cistern"),
        ("ill-guarantee-ya", "I'll guarantee ya I'll be there to see ya", now, "tom", "cistern"),
        ("predef", "Click here for the predefined in-memory thread (does not call database)", now, "banana", "cistern"),
        ("on-the-bonnie-bonnie-banks", "On the bonnie, bonnie banks of Loch Lomond...", now, "tom", "cistern"),
        ("goonsay", "^^^^^^^^^ :goonsay:", now, "VoxPVoxD", "cistern")
      )
      
      Posts.insertAll(
        ("1", "Furthermore, we begin bombing in five minutes.", now, "ferrinus", "fate-is-the"),
        ("2", "actually, it is the best.", now, "banana", "fate-is-the"),
        ("3", "nope", now, "ferrinus", "fate-is-the"),
        ("4", "hmm, this is a tough question. why don't we run a game about it to find out?", now, "VoxPVoxD", "fate-is-the"),
        
        ("5", ":goonsay:", now, "VoxPVoxD", "goonsay"),
        ("6", ":goonsay:", now, "VoxPVoxD", "goonsay"),
        ("7", ":goonsay:", now, "VoxPVoxD", "goonsay"),
        ("8", ":goonsay:", now, "VoxPVoxD", "goonsay"),
        ("9", ":goonsay:", now, "VoxPVoxD", "goonsay"),
        ("10", ":goonsay:", now, "VoxPVoxD", "goonsay"),
        ("11", ":goonsay:", now, "VoxPVoxD", "goonsay"),
        ("12", ":goonsay:", now, "VoxPVoxD", "goonsay"),
        ("13", ":goonsay:", now, "VoxPVoxD", "goonsay"),
        ("14", ":goonsay:", now, "VoxPVoxD", "goonsay"),
        ("15", ":goonsay:", now, "VoxPVoxD", "goonsay"),
        ("16", ":goonsay:", now, "VoxPVoxD", "goonsay")
      )
    }
  }
}