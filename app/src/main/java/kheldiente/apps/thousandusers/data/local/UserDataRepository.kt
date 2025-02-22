package kheldiente.apps.thousandusers.data.local

import kheldiente.apps.thousandusers.data.model.User

class UserDataRepository: UserDataSource {

    override fun getUsers(limit: Int, offset: Int): List<User> {
        return listOf(
            User(1, "John", "Doe", "john.doe@example", "Male", true),
            User(2, "Mary", "Doe", "mary.doe@example", "Female", false)
        )
    }

}