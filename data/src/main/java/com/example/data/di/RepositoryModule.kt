package com.example.data.di

import com.example.data.repository.TodoRepositoryImpl
import com.example.domain.repository.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsTodoRepository(
        todoRepositoryImpl: TodoRepositoryImpl
    ): TodoRepository
}