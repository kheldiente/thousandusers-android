package kheldiente.apps.thousandusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import kheldiente.apps.thousandusers.di.appDependencies
import kheldiente.apps.thousandusers.ui.screen.UserListScreen
import kheldiente.apps.thousandusers.ui.theme.ThousandUsersTheme

class HomeActivity : ComponentActivity() {

    private val userViewModel by lazy {
        applicationContext.appDependencies().getUserListViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        userViewModel.setupDataIfNeeded {
            setContent {
                ThousandUsersTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        UserListScreen(
                            viewModel = userViewModel,
                            modifier = Modifier.padding(innerPadding),
                        )
                    }
                }
            }
        }
    }
}