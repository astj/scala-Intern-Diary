package interndiary.model

import org.joda.time.LocalDateTime

case class Entry(id: Long, blogId: Long, title: String, body: String, date: LocalDateTime, createdAt: LocalDateTime, updatedAt: LocalDateTime) {
}
