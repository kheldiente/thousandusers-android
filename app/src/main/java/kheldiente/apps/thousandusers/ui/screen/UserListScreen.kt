package kheldiente.apps.thousandusers.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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

@Composable
fun UserListScreen(
    viewModel: UserViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState = viewModel.uiState.collectAsState()
    val users = uiState.value.users
    val isLoading = uiState.value.isLoading
    val hasMoreUsers = uiState.value.hasMoreUsers

    Box(modifier = Modifier.fillMaxSize()) {
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

                    if (index == users.lastIndex && hasMoreUsers) {
                        LaunchedEffect(Unit) {
                            viewModel.loadMoreUsers()
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
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "${user.getFullName()} - ID: ${user.id}",
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Email: ${user.email}",
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Gender: ${user.gender}",
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = """
                    Status: ${if (user.status) stringResource(R.string.active_label) else stringResource(R.string.inactive_label)}
                """.trimIndent(),
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}