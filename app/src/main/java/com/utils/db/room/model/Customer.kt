package com.utils.db.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Customer")
data class Customer(
    val id: Long = System.currentTimeMillis(),
    val userName: String,
    @PrimaryKey(autoGenerate = false)
    val email: String,
)
