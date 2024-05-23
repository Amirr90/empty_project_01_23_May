package com.utils.db.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.userList.model.User
import com.utils.db.room.roomConstant.CustomerConstant
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    @Upsert
    suspend fun saveUSer(user: User)

    @Query("SELECT * FROM ${CustomerConstant.USER_TABLE_NAME}")
    fun fetchUser(): Flow<List<User>>

}