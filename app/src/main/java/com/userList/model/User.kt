package com.userList.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.utils.db.room.roomConstant.CustomerConstant.USER_TABLE_NAME

@Entity(tableName = USER_TABLE_NAME)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fName: String = "",
    val lName: String = "",
    val age: String = "",
    val address: String = "",
    val cityName: String = "",
)
