package views

import java.util.Date

abstract class Header(val description: String) {
  def uri : String
}

case class UserHeader(id: String, username: String) extends Header(username) {
  override val uri = controllers.routes.User.view(id).url
}

case class BoardHeader(id: String, title: String) extends Header(title) {
  override val uri = controllers.routes.Board.view(id).url
}

case class ThreadHeader(id: String, subject: String) extends Header(subject) {
  override val uri = controllers.routes.Thread.view(id).url
}

case class PostDetail(postid: String, userid: String, author: String, avatar: String, content: String, posted: Date)

case class ThreadDetail(thread: ThreadHeader, postcount: Int, firstPoster: UserHeader, firstPosted: String, lastPoster: UserHeader, lastPosted: String)