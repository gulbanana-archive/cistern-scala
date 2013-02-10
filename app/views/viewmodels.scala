package views

import java.util.Date

case class PostDetail(postid: String, userid: String, author: String, avatar: String, content: String, posted: Date)
case class UserHeader(id: String, username: String)
case class BoardHeader(id: String, title: String)
case class ThreadHeader(id: String, subject: String)