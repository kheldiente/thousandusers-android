package kheldiente.apps.thousandusers.data.local

import kheldiente.apps.thousandusers.data.model.User

interface UserDataSource {
    fun getUsers(limit: Int, offset: Int): List<User>
}