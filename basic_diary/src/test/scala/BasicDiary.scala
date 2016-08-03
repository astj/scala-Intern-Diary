import org.scalatest._

abstract class UnitSpec extends FunSpec with Matchers with OptionValues with Inside with Inspectors

class UserSpec extends UnitSpec {
  describe("User") {
    val user = User("astj")
    user.name shouldBe "astj"
  }
}

class EntrySpec extends UnitSpec {
  describe("Entry") {
    val entry = Entry("myTitle", "myBody")
    entry.title shouldBe "myTitle"
    entry.body shouldBe "myBody"
  }
}

class BlogSpec extends UnitSpec {
  describe("Blog") {
    val blog = Blog("owner no namae", "blog no namae")
    blog.ownerName shouldBe "owner no namae"
    blog.name shouldBe "blog no namae"
    blog.allEntries shouldBe List()

    val (addedBlog, entry1) = blog.addEntry("entry no title", "entry no honbun")
    addedBlog.ownerName shouldBe blog.ownerName
    addedBlog.name shouldBe blog.name
    addedBlog.allEntries shouldBe List(entry1)

    entry1.title shouldBe "entry no title"
    entry1.body shouldBe "entry no honbun"

    val (addedBlog2, entry2) = addedBlog.addEntry("title2", "honbun2")
    addedBlog2.allEntries shouldBe List(entry1, entry2)
  }
}
