package kheldiente.apps.thousandusers.util

import android.content.Context
import com.google.gson.Gson
import kheldiente.apps.thousandusers.ui.screen.data.User

fun Context.readUsersFromJsonFile(): List<User> {
    val fileName = "thousand_users.json"
    val jsonString = assets.open(fileName).bufferedReader().use {
        it.readText()
    }
    return Gson().fromJson(jsonString, Array<User>::class.java).toList()
}