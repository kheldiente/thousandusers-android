package kheldiente.apps.thousandusers.data.local

import android.content.Context
import kheldiente.apps.thousandusers.data.local.dao.UserDao
import kheldiente.apps.thousandusers.ui.screen.data.User
import kheldiente.apps.thousandusers.ui.screen.data.mapper.toUserEntity
import kheldiente.apps.thousandusers.ui.screen.data.mapper.toUserViewData
import kheldiente.apps.thousandusers.util.readUsersFromJsonFile

class UserDataRepository(
    private val context: Context,
    private val userDao: UserDao
): UserDataSource {

    override suspend fun populateDatabaseIfNeeded() {
        if (shouldPopulateDb()) {
            val users = context.readUsersFromJsonFile()
                .map { it.toUserEntity() }

            userDao.insertAll(users)
            println("Inserted users from JSON: ${users.size}")
        } else {
            println("Database already populated")
        }
    }

    override suspend fun getUsers(limit: Int, offset: Int): List<User> {
        return userDao.getUsers(limit, offset)
            .map { it.toUserViewData() }
    }

    override suspend fun getUserCount(): Int {
        return userDao.getUserCount()
    }

    private suspend fun shouldPopulateDb(): Boolean {
        return userDao.getUserCount() == 0
    }

}