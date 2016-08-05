package interndiary.repository

import interndiary.model.{Blog,User}
import org.joda.time.LocalDateTime
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.GetResult // to define implicit conversion
import slick.driver.MySQLDriver.api._
import com.github.tototoshi.slick.MySQLJodaSupport._

object Blogs {

  // define a implicit to use sql.as[Blog]
  private implicit val getBlogRowResult = GetResult(r => Blog(r.<<, r.<<, r.<<, r.<<, r.<<))

  def find(id: Long)(implicit ctx: Context): Option[Blog] = run(
    sql"""
      SELECT id, user_id, name, created_at, updated_at FROM blog
        WHERE id = ${id} LIMIT 1
    """.as[Blog].map(_.headOption)
  )

  def create(user: User, name: String)(implicit ctx: Context): Blog = {
    val newBlog = Blog(
      Identifier.generate, user.id, name, new LocalDateTime, new LocalDateTime
    )
    run(
      sqlu"""
        INSERT INTO blog (id, user_id, name, created_at, updated_at) VALUES(
          ${newBlog.id},
          ${newBlog.userId},
          ${newBlog.name},
          ${newBlog.createdAt},
          ${newBlog.updatedAt}
        )
      """
    )
    newBlog
  }

}
