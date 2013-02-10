package views

import play.api.data._
import play.api.data.Forms._

case class NewPost(contents: String)
object NewPost {
  val form = Form (
    mapping(
      "content" -> nonEmptyText
    )(NewPost.apply)(NewPost.unapply)
  )
}

case class NewThread(subject: String, contents: String)
object NewThread {
  val form = Form (
    mapping(
      "subject" -> nonEmptyText,
      "content" -> nonEmptyText
    )(NewThread.apply)(NewThread.unapply)
  )
}