package kheldiente.apps.thousandusers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kheldiente.apps.thousandusers.data.model.User
import kheldiente.apps.thousandusers.data.local.UserDataSource
import kotlinx.coroutines.launch

class UserViewModel(
    private val userDataSource: UserDataSource,
): ViewModel() {

    fun setupDataIfNeeded(isReady: (Boolean) -> Unit) {
        viewModelScope.launch {
            userDataSource.setup()

            isReady(true)
        }
    }

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
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                return UserViewModel(userDataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}