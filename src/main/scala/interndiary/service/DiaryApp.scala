package interndiary.service

import interndiary.model.User
import interndiary.repository

class DiaryApp(userName: String) {
  def currentUser(implicit ctx: Context): User = {
    repository.Users.findOrCreateByName(userName)
  }
}
