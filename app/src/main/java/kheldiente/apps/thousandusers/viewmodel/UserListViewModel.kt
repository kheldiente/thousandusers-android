package kheldiente.apps.thousandusers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kheldiente.apps.thousandusers.data.model.User
import kheldiente.apps.thousandusers.data.local.UserDataSource

class UserListViewModel(
    private val userDataSource: UserDataSource,
): ViewModel() {

    fun getUsers(
        limit: Int = 0,
        offset: Int = 0
    ): List<User> {
        return userDataSource.getUsers(limit, offset)
    }

    class UserListViewModelFactory(
        private val userDataSource: UserDataSource
    ): ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
                return UserListViewModel(userDataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}