package com.utils.db.room.repo

import android.util.Log
import com.userList.model.User
import com.utils.db.room.dao.RoomDao
import javax.inject.Inject

const val TAG = "SaveUserToRoomDatabase"

class SaveUserToRoomDatabase @Inject constructor(
    private val dao: RoomDao,
) : UserRepo {
    override suspend fun saveUser(user: User): Boolean {
        Log.d(TAG, "saveUser: $user")
        return try {
            dao.saveUSer(user)
            true
        } catch (ex: Exception) {
            Log.d(TAG, "saveUserException: ${ex.localizedMessage}")
            false
        }
    }
}