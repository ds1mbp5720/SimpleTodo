package com.example.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Task")
data class TodoEntity(
    @PrimaryKey
    val id: Long, // 입력 시간 millTime 생성 : 입력 시간 중복 불가
    val task: String,
    val date: String
)
