package com.utils.db.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.userList.model.User
import com.utils.db.room.dao.RoomDao
import com.utils.db.room.model.Customer


@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): RoomDao
}