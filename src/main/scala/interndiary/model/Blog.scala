package interndiiary.model

import org.joda.time.LocalDateTime

case class Blog(id: Long, user_id: Long, name: String, created_at: LocalDateTime, updated_at: LocalDateTime) {
}
