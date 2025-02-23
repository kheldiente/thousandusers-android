package kheldiente.apps.thousandusers.ui.screen.data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Long,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("status")
    val status: Boolean
) {

    fun getFullName(): String {
        return "$firstName $lastName"
    }

}
