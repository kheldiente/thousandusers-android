package kheldiente.apps.thousandusers.di

import android.content.Context
import kheldiente.apps.thousandusers.ThousandUserApplication
import kheldiente.apps.thousandusers.data.local.UserDataRepository
import kheldiente.apps.thousandusers.data.local.UserDataSource
import kheldiente.apps.thousandusers.viewmodel.UserViewModel

fun Context.appDependencies(): AppDependencies {
    return (applicationContext as ThousandUserApplication).appDependencies
}

class AppDependencies(
    private val context: Context
) {

    private fun getUserDataSource(): UserDataSource {
        return UserDataRepository(context)
    }

    fun getUserListViewModel(): UserViewModel {
        return UserViewModel.UserListViewModelFactory(
            userDataSource = getUserDataSource()
        ).create(UserViewModel::class.java)
    }

}