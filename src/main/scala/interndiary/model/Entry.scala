package internbookmark.model

import org.joda.time.LocalDateTime

case class Entry(id: Long, blog_id: Long, title: String, body: String, date: LocalDateTime,  created_at: LocalDateTime, updated_at: LocalDateTime) {
}
