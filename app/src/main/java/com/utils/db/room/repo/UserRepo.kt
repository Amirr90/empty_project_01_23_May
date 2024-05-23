package com.utils.db.room.repo

import com.userList.model.User

interface UserRepo {
    suspend fun saveUser(user: User): Boolean
}