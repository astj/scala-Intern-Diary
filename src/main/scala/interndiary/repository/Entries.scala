package interndiary.repository

import interndiary.model.{Blog,Entry}
import org.joda.time.LocalDateTime
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.GetResult // to define implicit conversion
import slick.driver.MySQLDriver.api._
import com.github.tototoshi.slick.MySQLJodaSupport._

object Entries {

  // define a implicit to use sql.as[Blog]
  private implicit val getEntryRowResult = GetResult(r => Entry(r.<<, r.<<, r.<<, r.<<, r.<<, r.<<, r.<<))

  def find(id: Long)(implicit ctx: Context): Option[Entry] = run(
    sql"""
      SELECT * FROM Entry
        WHERE id = ${id} LIMIT 1
    """.as[Entry].map(_.headOption)
  )

  def list(blog: Blog, limit : Int, offset: Int = 0)(implicit ctx: Context): Seq[Entry] = run (
    sql"""
      SELECT * FROM Entry
        WHERE blog_id = ${blog.id} ORDER BY created_at DESC limit ${limit} offset ${offset}
    """.as[Entry] // this actually returns Vector
  )

  def create(blog: Blog, title: String, body: String)(implicit ctx: Context): Entry = {
    // TODO support date
    val newEntry = Entry(
      Identifier.generate, blog.id, title, body, new LocalDateTime, new LocalDateTime, new LocalDateTime
    )
    run(
      sqlu"""
        INSERT INTO entry (id, blog_id, title, body, date, created_at, updated_at) VALUES(
          ${newEntry.id},
          ${newEntry.blogId},
          ${newEntry.title},
          ${newEntry.body},
          ${newEntry.date},
          ${newEntry.createdAt},
          ${newEntry.updatedAt}
        )
      """
    )
    newEntry
  }

}
