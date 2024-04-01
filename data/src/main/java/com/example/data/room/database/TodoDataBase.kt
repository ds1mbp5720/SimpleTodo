package com.example.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.room.dao.TodoDao
import com.example.data.room.entity.TodoEntity

@Database(entities = [TodoEntity::class], version = 2, exportSchema = false)
abstract class TodoDataBase : RoomDatabase() {
    abstract fun getTodoDao(): TodoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDataBase? = null

        private fun buildDataBase(context: Context): TodoDataBase =
            Room.databaseBuilder(
                context.applicationContext,
                TodoDataBase::class.java,
                "todo"
            ).fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): TodoDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDataBase(context).also { INSTANCE = it }
            }
    }
}