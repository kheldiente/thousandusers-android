package kheldiente.apps.thousandusers.di

import android.content.Context
import androidx.room.Room
import kheldiente.apps.thousandusers.ThousandUserApplication
import kheldiente.apps.thousandusers.data.local.UserDataRepository
import kheldiente.apps.thousandusers.data.local.UserDataSource
import kheldiente.apps.thousandusers.data.local.db.AppDatabase
import kheldiente.apps.thousandusers.ui.screen.viewmodel.UserViewModel

fun Context.appDependencies(): AppDependencies {
    return (applicationContext as ThousandUserApplication).appDependencies
}

class AppDependencies(private val context: Context) {

    companion object {
        private const val DB_NAME = "thousand-users-db"
    }

    private lateinit var database: AppDatabase

    fun initDb() {
        database = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }

    fun getUserListViewModel(): UserViewModel {
        return UserViewModel.UserListViewModelFactory(
            userDataSource = getUserDataSource()
        ).create(UserViewModel::class.java)
    }

    private fun getUserDataSource(): UserDataSource {
        return UserDataRepository(context, database.userDao())
    }

}