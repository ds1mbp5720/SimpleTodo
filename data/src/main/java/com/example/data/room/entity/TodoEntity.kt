package com.example.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Task")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val task: String,
    val date: String
)
