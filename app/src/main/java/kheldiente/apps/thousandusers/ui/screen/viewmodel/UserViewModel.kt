package kheldiente.apps.thousandusers.ui.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kheldiente.apps.thousandusers.ui.screen.data.User
import kheldiente.apps.thousandusers.data.local.UserDataSource
import kheldiente.apps.thousandusers.ui.screen.UserListUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(
    private val userDataSource: UserDataSource,
): ViewModel() {

    companion object {
        private const val PAGINATION_LIMIT = 20
        private const val PAGINATION_OFFSET = 0
        private const val DELAY_TO_SHOW_LOADING = 500L
    }

    private val _uiState = MutableStateFlow(UserListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = UserListUiState(isLoading = true)
            try {
                // Added delay just to show that loading is working
                delay(DELAY_TO_SHOW_LOADING)
                userDataSource.setup()
            } finally {
                getUsers()
            }
        }
    }

    fun getUsers(
        limit: Int = PAGINATION_LIMIT,
        offset: Int = PAGINATION_OFFSET
    ) {
        viewModelScope.launch {
            userDataSource.getUsers(limit, offset).let { users ->
                _uiState.update {
                    it.copy(
                        users = users,
                        isLoading = false
                    )
                }
            }
        }
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