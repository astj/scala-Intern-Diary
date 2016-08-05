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

    val app = createApp("astj")

    val user = app.currentUser
    process.stderr.println(
      s"Welcome ${user.name} !"
    )

    try {
      args.toList match {
        // TODO: implement
        case "help" :: _ =>
          help()
        case _ =>
          help()
      }
    } finally Context.destroy
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
