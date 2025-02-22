package kheldiente.apps.thousandusers.di

import android.content.Context
import kheldiente.apps.thousandusers.ThousandUserApplication
import kheldiente.apps.thousandusers.data.local.UserDataRepository
import kheldiente.apps.thousandusers.data.local.UserDataSource
import kheldiente.apps.thousandusers.viewmodel.UserListViewModel

fun Context.appDependencies(): AppDependencies {
    return (applicationContext as ThousandUserApplication).appDependencies
}

class AppDependencies {

    private fun getUserDataSource(): UserDataSource {
        return UserDataRepository()
    }

    fun getUserListViewModel(): UserListViewModel {
        return UserListViewModel.UserListViewModelFactory(
            userDataSource = getUserDataSource()
        ).create(UserListViewModel::class.java)
    }

}