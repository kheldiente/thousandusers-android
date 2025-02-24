package kheldiente.apps.thousandusers.data.local

import kheldiente.apps.thousandusers.ui.screen.data.User

interface UserDataSource {
    suspend fun populateDatabaseIfNeeded()
    suspend fun getUsers(keyword: String, limit: Int, offset: Int): List<User>
    suspend fun getUserCount(keyword: String): Int
}