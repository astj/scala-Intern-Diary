package interndiary.model

import org.joda.time.LocalDateTime

case class User(id: Long, name: String, created_at: LocalDateTime, updated_at: LocalDateTime) {
}
