package interndiary.service

import interndiary.model.{User,Blog,Entry}
import interndiary.repository

import org.joda.time.LocalDateTime

class DiaryApp(userName: String) {
  def currentUser(implicit ctx: Context): User = {
    repository.Users.findOrCreateByName(userName)
  }

  def addBlog(blogName: String)(implicit ctx: Context): Blog = {
    val user = currentUser
    repository.Blogs.create(user, blogName)
  }

  def getBlog(blogId: Long)(implicit ctx: Context): Option[Blog] = {
    val user = currentUser
    repository.Blogs.find(blogId)
  }

  def getEntries(blog: Blog)(implicit ctx: Context): Seq[Entry] = {
    repository.Entries.list(blog, 20)
  }

  def writeEntry(blog: Blog, title: String, body: String)(implicit ctx: Context): Entry = {
    repository.Entries.create(blog, title, body)
  }
}
