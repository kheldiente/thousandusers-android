package kheldiente.apps.thousandusers.data.local

import android.content.Context
import kheldiente.apps.thousandusers.data.model.User
import kheldiente.apps.thousandusers.util.readUsersFromJsonFile

class UserDataRepository(
    private val context: Context,
): UserDataSource {

    private var cachedUsers = emptyList<User>()

    override suspend fun setup() {
        val users = context.readUsersFromJsonFile()
        cachedUsers = users
        println("Users from JSON: ${users.size}")
    }

    override fun getUsers(limit: Int, offset: Int): List<User> {
        return cachedUsers.take(20)
    }

}