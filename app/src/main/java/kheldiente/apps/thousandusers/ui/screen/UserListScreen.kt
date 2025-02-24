package kheldiente.apps.thousandusers.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kheldiente.apps.thousandusers.R
import kheldiente.apps.thousandusers.ui.screen.data.User
import kheldiente.apps.thousandusers.ui.screen.viewmodel.UserViewModel
import kheldiente.apps.thousandusers.ui.theme.Grey
import kheldiente.apps.thousandusers.ui.theme.Typography
import kheldiente.apps.thousandusers.ui.theme.White

@Composable
fun UserListScreen(
    viewModel: UserViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState = viewModel.uiState.collectAsState()
    val users = uiState.value.users
    val isLoading = uiState.value.isLoading
    val isLoadingMoreUsers = uiState.value.isLoadingMoreUsers
    val hasMoreUsers = uiState.value.hasMoreUsers

    Box(
        modifier = Modifier.fillMaxSize()
            .background(Grey)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = modifier.padding(8.dp)
            ) {
                items(
                    count = users.size,
                    key = { index -> index }
                ) { index ->
                    UserListItem(users[index])

                    // For now, we will just load more users when we reach the last item
                    // IMPROVEMENT: Load more users when we reach the 3rd last item in the list (prefetching)
                    if (index == users.lastIndex && hasMoreUsers) {
                        LaunchedEffect(Unit) {
                            viewModel.loadMoreUsers()
                        }
                    }
                }

                if (isLoadingMoreUsers) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UserListItem(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = White,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp)
        ) {
            Text(
                text = user.getFullName(),
                style = Typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${stringResource(R.string.id_label)}: ${user.id}",
                style = Typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${stringResource(R.string.email_label)}: ${user.email}",
                style = Typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${stringResource(R.string.gender_label)}: ${user.gender}",
                style = Typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = """
                    ${stringResource(R.string.status_label)}: ${if (user.status) stringResource(R.string.active_label) else stringResource(R.string.inactive_label)}
                """.trimIndent(),
                style = Typography.bodyMedium,
            )
        }
    }
}