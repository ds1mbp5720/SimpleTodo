package com.example.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.room.entity.TodoEntity
import io.reactivex.Single

@Dao
interface TodoDao {
    @Query("SELECT * FROM Task")
    fun getTodoList(): Single<List<TodoEntity>>

    @Insert
    fun insertTodo(todo: TodoEntity)

    @Update
    fun updateTodo(todo: TodoEntity)

    @Delete
    fun deleteTodo(todo: TodoEntity)
}