package interndiary.cli

import interndiary.service.{DiaryApp,Context}
import scala.sys.process

object DiaryCLI {
  def main(args: Array[String]): Unit = {
    val exitStatus = run(args)
    sys.exit(exitStatus)
  }

  def createApp(userName: String): DiaryApp = new DiaryApp(userName)

  def run(args: Array[String]): Int = {
    Context.setup("db.default")
    implicit val ctx = Context.createContext

    val App = createApp("astj")
    val user = App.currentUser
    process.stderr.println(
      s"Welcome ${user.name} !"
    )

    // TODO: implement
    help()
  }

  def help(): Int = {
    process.stderr.println(
      """
        | usage:
        |   run add url [comment]
        |   run list
        |   run delete url
      """.stripMargin)
    1
  }
}
