package kheldiente.apps.thousandusers.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kheldiente.apps.thousandusers.data.model.User
import kheldiente.apps.thousandusers.viewmodel.UserListViewModel

@Composable
fun UserListScreen(
    viewModel: UserListViewModel,
    modifier: Modifier = Modifier,
) {
    val users = viewModel.getUsers()

    LazyColumn(
        modifier = modifier.padding(8.dp)
    ) {
        items(
            count = users.size,
            key = { index -> index }
        ) { index ->
            UserListItem(users[index])
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
                text = user.getFullName(),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = user.email,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}