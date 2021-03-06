package models

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import java.sql.Timestamp
import Schema._

object TestRepository {
  private def now = new Timestamp(System.currentTimeMillis)
  
  def createAll() {
    DB.withSession { implicit Session =>
      Posters.insertAll(
        ("banana", "Gul Banana", now),
        ("ferrinus", "FerrinusPrime", now),
        ("VoxPVoxD", "VoxPVoxD", now),
        ("tom", "tom", now),
        ("ninjeff", "ninjeff", now),
        ("tim", "squimmy", now),
        ("anonymous", "Anonymous Coward", now)
      )
     
      Boards.insertAll(
        ("cistern", "Cistern Test Board")
      )
      
      Threads.insertAll(
        ("fate-is-the", "Fate is the worst arcanum", now, "ferrinus", "cistern"),
        ("ill-take-the-high-road", "I'll take the high road, and you take the low road", now, "tom", "cistern"),
        ("the-high-road-is-my", "The high road is my road, the low road's the slow road!", now, "tom", "cistern"),
        ("ill-guarantee-ya", "I'll guarantee ya I'll be there to see ya", now, "tom", "cistern"),
        ("predef", "the thread formerly known as the predefined in-memory thread", now, "tim", "cistern"),
        ("on-the-bonnie-bonnie-banks", "On the bonnie, bonnie banks of Loch Lomond...", now, "tom", "cistern"),
        ("goonsay", "^^^^^^^^^ :goonsay:", now, "VoxPVoxD", "cistern"),
        ("ha-ha-ha", "ha ha ha i have hacked this forum", now, "ninjeff", "cistern")
      )
      
      Posts.insertAll(
        ("1", "Furthermore, we begin bombing in five minutes.", now, "ferrinus", "fate-is-the"),
        ("2", "actually, it is the best.", now, "banana", "fate-is-the"),
        ("3", "nope", now, "ferrinus", "fate-is-the"),
        ("4", "hmm, this is a tough question. why don't we run a game about it to find out?", now, "VoxPVoxD", "fate-is-the"),
        
        ("5", "Don't put your daughter on the stage, Mrs. Worthington", now, "tom", "ill-take-the-high-road"),
        ("9", "what?", now, "ferrinus", "ill-take-the-high-road"),
        
        ("6", "The Stately Homes of England", now, "tom", "the-high-road-is-my"),
        
        ("7", "Mad Dogs And Englishmen", now, "tom", "ill-guarantee-ya"),
        ("10", "agreed", now, "VoxPVoxD", "ill-guarantee-ya"),
        
        ("8", "I Like America", now, "tom", "on-the-bonnie-bonnie-banks"),
        ("11", "i dont", now, "banana", "on-the-bonnie-bonnie-banks"),
        ("12", "me neither", now, "VoxPVoxD", "on-the-bonnie-bonnie-banks"),
        ("19", "[b]Adjudicating Disbelief:[/b] Disbelief is triggered whenever the operation of magic is apparent to Sleeper witnesses. This most often happens when a mage attempts to cast or sustain a straightforwardly fantastic vulgar spell in a scene containing Sleepers; as soon as Sleepers see the effects of a spell, they disbelieve. Disbelief is applied whenever and wherever a spell's effects are or would be seen, so it's as difficult to teleport out of a crowd as into a crowd.<br/>Vulgar spell effects don't inherently trigger Disbelief when they consist of natural phenomena. The direct conjuration, bizarre behavior, or gross transformation of vulgar spell effects, on the other hand, do. A tornado that rips through a populated area won't trigger Disbelief on its own, but it will if it chases down individual people, turns everything it touches to glass, or is created on the spot with the wave of a hand.", now, "ferrinus", "on-the-bonnie-bonnie-banks"),
        
        ("13", ":goonsay:", now, "VoxPVoxD", "goonsay"),
        ("14", ":goonsay:", now, "VoxPVoxD", "goonsay"),
        ("15", ":goonsay:", now, "VoxPVoxD", "goonsay"),
        ("16", ":goonsay:", now, "VoxPVoxD", "goonsay"),
        ("20", ":goonsay:", now, "VoxPVoxD", "goonsay"),
        ("21", ":goonsay:", now, "VoxPVoxD", "goonsay"),
        ("22", ":goonsay:", now, "VoxPVoxD", "goonsay"),
        
        ("17", "all your post are belong to us", now, "ninjeff", "ha-ha-ha"),
        ("18", "or it could be more like disbelief marks you, the mage. as soon as you've been zapped, you'll always lose to disbelief for the rest of the scene. i don't like how that looks for multiple mages though, it'd be weird to see one laughing and chasing another because only one got unlucky", now, "ferrinus", "ha-ha-ha"),
        
        ("23", "previous game my entourage consisted of a harlequin baby, an aborted foetus, a dead baby's soul and one of those tumours that's actually a twin", now, "tim", "predef"),
        ("24", "argh like the kind where they strangle each other in the womb?", now, "banana", "predef"),
        ("25", "no, like that thing where someone thinks they have a tumor and when the doctors open them up to cut it out, it turns out they've had a siamese twin their whole life and the tumor has teeth and hair and such", now, "tim", "predef"),
        ("26", "i think it's a type of teratoma?", now, "tim", "predef"),
        ("27", "is that really a twin though it's just like a extra growth of certain bits", now, "banana", "predef"),
        ("28", "actual twins have apparently been reported but it's apparenly much more often just growths of certain bits", now, "tim", "predef"),
        ("29", "like, just eyes or spines or teeth or w/e", now, "tim", "predef")
      )
    }
  }
}
