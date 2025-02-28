package kheldiente.apps.thousandusers.ui.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kheldiente.apps.thousandusers.data.local.UserDataSource
import kheldiente.apps.thousandusers.ui.screen.UserListUiState
import kheldiente.apps.thousandusers.util.DELAY_TO_SHOW_LIST_MILLIS
import kheldiente.apps.thousandusers.util.INIT_PAGE
import kheldiente.apps.thousandusers.util.PAGE_LIMIT
import kheldiente.apps.thousandusers.util.SEARCH_DELAY_MILLIS
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(
    private val userDataSource: UserDataSource,
): ViewModel() {

    private val _uiState = MutableStateFlow(UserListUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    private var searchUsersJob: Job? = null

    private var currentPage = INIT_PAGE
    private var maxUserCount = 0
    private var searchQuery = ""

    init {
        viewModelScope.launch {
            populateDatabaseIfNeeded()
            loadInitData()
        }
    }

    fun loadMoreUsers() {
        val isLoadingMoreUsers = _uiState.value.isLoadingMoreUsers
        val hasMoreUsers = _uiState.value.hasMoreUsers

        if (isLoadingMoreUsers || !hasMoreUsers) {
            return
        }

        viewModelScope.launch {
            try {
                _uiState.update { currentState ->
                    currentState.copy(isLoadingMoreUsers = true)
                }

                currentPage++

                // Added to simulate loading delay
                delay(DELAY_TO_SHOW_LIST_MILLIS)

                val nextUsers = userDataSource.getUsers(
                    keyword = searchQuery,
                    limit = PAGE_LIMIT,
                    offset = currentPage * PAGE_LIMIT
                )

                if (nextUsers.isNotEmpty()) {
                    _uiState.update { currentState ->
                        val newUserList = currentState.users + nextUsers
                        currentState.copy(
                            users = newUserList,
                            hasMoreUsers = newUserList.size < maxUserCount,
                        )
                    }
                } else {
                    _uiState.update { currentState ->
                        currentState.copy(
                            hasMoreUsers = false
                        )
                    }
                }
            } finally {
                _uiState.update { currentState ->
                    currentState.copy(isLoadingMoreUsers = false)
                }
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        currentPage = INIT_PAGE
        searchQuery = query

        _uiState.update { currentState ->
            currentState.copy(
                searchQuery = query
            )
        }

        searchUsersJob?.cancel()
        searchUsersJob = viewModelScope.launch {
            delay(SEARCH_DELAY_MILLIS)
            loadInitData()
        }
    }

    private suspend fun populateDatabaseIfNeeded() {
        userDataSource.populateDatabaseIfNeeded()
    }

    private suspend fun loadInitData() {
        currentPage = INIT_PAGE
        maxUserCount = userDataSource.getUserCount(searchQuery)

        // Added to simulate loading delay
        delay(DELAY_TO_SHOW_LIST_MILLIS)

        userDataSource.getUsers(
            keyword = searchQuery,
            limit = PAGE_LIMIT,
            offset = currentPage * PAGE_LIMIT
        ).let { users ->
            _uiState.value = UserListUiState(
                users = users,
                hasMoreUsers = users.size < maxUserCount,
                isLoading = false,
                searchQuery = searchQuery
            )
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