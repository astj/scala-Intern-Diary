package interndiary.service

import interndiary.model.{User,Blog}
import interndiary.repository

import org.joda.time.LocalDateTime

class DiaryApp(userName: String) {
  def currentUser(implicit ctx: Context): User = {
    repository.Users.findOrCreateByName(userName)
  }

  def addBlog(blogName: String)(implicit ctx: Context): Blog = {
    val user = currentUser

    // TODO use repository
    new Blog(12345, user.id, blogName, new LocalDateTime, new LocalDateTime)
  }
}
