package kheldiente.apps.thousandusers.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kheldiente.apps.thousandusers.data.entity.UserEntity
import kheldiente.apps.thousandusers.data.local.dao.UserDao

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}