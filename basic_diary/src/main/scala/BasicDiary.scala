case class User(name: String) {
  def addBlog(blogName: String): Blog = {
    Blog(name, blogName)
  }
}

case class Entry(title: String, body: String) {
}

case class Blog(ownerName: String, name: String, allEntries: List[Entry] = List()) {
  def addEntry(title: String, body: String): (Blog, Entry) = {
    val newEntry = Entry(title, body)
    val addedBlog = Blog(ownerName, name, allEntries :+ newEntry)
    (addedBlog, newEntry)
  }
}

case class Hello(msg: String) {
  def hello() = msg
}

object BasicDiary {
  def main(args: Array[String]): Unit = {
    val user = User("daiksy")

    val blog = user.addBlog("だいくしーblog")

    println(blog.name) // だいくしーblog

    // 工夫ポイント: タプルよりかっこいい方法がありそうだ!
    val (addedBlog1, entry1) = blog.addEntry("今日の日記", "今日はインターン2日目。Scalaプログラムの基本編の講義を受けた。")
    val (addedBlog2, entry2) = addedBlog1.addEntry("一昨日の日記", "今日はインターン初日。最高の夏にするぞ！！！")

    val entries = addedBlog2.allEntries
    entries.map(_.title).foreach(println) // "今日の日記", "一昨日の日記"
  }
}
