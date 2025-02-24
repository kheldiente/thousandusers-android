package kheldiente.apps.thousandusers.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kheldiente.apps.thousandusers.data.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserEntity>)

    @Query("""
        SELECT * FROM users 
        WHERE firstName LIKE '%' || :keyword || '%' 
        OR lastName LIKE '%' || :keyword || '%' 
        LIMIT :limit OFFSET :offset
    """)
    suspend fun getUsers(
        keyword: String,
        limit: Int,
        offset: Int
    ): List<UserEntity>

    @Query("""
        SELECT COUNT(*) FROM users 
        WHERE firstName LIKE '%' || :keyword || '%' 
        OR lastName LIKE '%' || :keyword || '%'
    """)
    suspend fun getUserCount(keyword: String): Int

}