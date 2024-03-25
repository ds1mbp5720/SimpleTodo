package com.example.data.di

import android.content.Context
import com.example.data.room.dao.TodoDao
import com.example.data.room.database.TodoDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {
    @Provides
    @Singleton
    fun provideTodoDataBase(
        @ApplicationContext context: Context
    ): TodoDataBase = TodoDataBase.getInstance(context)

    @Provides
    @Singleton
    fun provideTodoDao(todoDataBase: TodoDataBase): TodoDao = todoDataBase.getTodoDao()
}