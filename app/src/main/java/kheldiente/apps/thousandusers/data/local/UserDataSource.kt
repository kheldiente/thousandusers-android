package kheldiente.apps.thousandusers.data.local

import kheldiente.apps.thousandusers.ui.screen.data.User

interface UserDataSource {
    suspend fun setup()
    suspend fun getUsers(limit: Int, offset: Int): List<User>
}