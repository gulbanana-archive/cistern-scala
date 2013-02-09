package views

case class PostDetail(postid: String, userid: String, author: String, avatar: String, content: String)
case class UserHeader(id: String, username: String)
case class BoardHeader(id: String, title: String)
case class ThreadHeader(id: String, subject: String)