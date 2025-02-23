package kheldiente.apps.thousandusers.data.local

import kheldiente.apps.thousandusers.ui.screen.data.User

interface UserDataSource {
    suspend fun populateDatabaseIfNeeded()
    suspend fun getUsers(limit: Int, offset: Int): List<User>
    suspend fun getUserCount(): Int
}