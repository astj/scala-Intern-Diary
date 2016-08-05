package interndiary.model

import org.joda.time.LocalDateTime

case class Blog(id: Long, userId: Long, name: String, createdAt: LocalDateTime, updatedAt: LocalDateTime) {
}
