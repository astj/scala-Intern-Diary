package interndiary.repository

import interndiary.model.User
import org.joda.time.LocalDateTime
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.GetResult // to define implicit conversion
import slick.driver.MySQLDriver.api._
import com.github.tototoshi.slick.MySQLJodaSupport._

object Users {

  // define a implicit to use sql.as[User]
  private implicit val getUserRowResult = GetResult(r => User(r.<<, r.<<, r.<<, r.<<))

  def findByName(name: String)(implicit ctx: Context): Option[User] = run(
    sql"""
      SELECT * FROM user
        WHERE name = ${name} LIMIT 1
    """.as[User].map(_.headOption)
  )

  // TODO how to handle when specified user already exists???
  def createWithName(name: String)(implicit ctx: Context): User = {
    val newUser = User(
      Identifier.generate, name, new LocalDateTime, new LocalDateTime
    )
    run(
      sqlu"""
        INSERT INTO user (id, name, created_at, updated_at) VALUES(
          ${newUser.id},
          ${newUser.name},
          ${newUser.createdAt},
          ${newUser.updatedAt}
        )
      """
    )
    newUser
  }

  def findOrCreateByName(name: String)(implicit ctx: Context): User =
    findByName(name).getOrElse(createWithName(name))

}
