package kheldiente.apps.thousandusers.ui.screen.data.mapper

import kheldiente.apps.thousandusers.data.entity.UserEntity
import kheldiente.apps.thousandusers.ui.screen.data.User

fun UserEntity.toUserViewData() = User(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    gender = this.gender,
    status = this.status
)

fun User.toUserEntity() = UserEntity(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    gender = this.gender,
    status = this.status
)