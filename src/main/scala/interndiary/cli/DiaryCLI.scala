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
        case "add" :: blogName :: __ =>
          addBlog(app, blogName)
        case "help" :: _ =>
          help()
        case _ =>
          help()
      }
    } finally Context.destroy
  }

  def addBlog(app: DiaryApp, blogName: String)(implicit ctx: Context): Int = {
    val addedBlog = app.addBlog(blogName)

    println(s"added blog '${addedBlog.name}'. id: ${addedBlog.id}")
    0
  }

  def help(): Int = {
    process.stderr.println(
      """
        | usage:
        |   run add [name]
        |   run list [blog_id]
        |   run write [blog_id] [title] [body]
        |   run delete [blog_id] [entry_id]
      """.stripMargin)
    1
  }
}
