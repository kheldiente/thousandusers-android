package kheldiente.apps.thousandusers.ui.screen

import kheldiente.apps.thousandusers.ui.screen.data.User

data class UserListUiState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMoreUsers: Boolean = false,
    val hasMoreUsers: Boolean = false,
    val searchQuery: String = "",
)