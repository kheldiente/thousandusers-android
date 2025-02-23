package kheldiente.apps.thousandusers.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val gender: String,
    val status: Boolean
)
