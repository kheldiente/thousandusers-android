package kheldiente.apps.thousandusers.data.model

data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val gender: String,
    val status: Boolean
) {

    fun getFullName(): String {
        return "$firstName $lastName"
    }

}
