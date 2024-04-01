package com.example.data.di

import com.example.data.repository.TodoRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.TodoRepository
import com.example.domain.repository.UserRepository
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
    @Binds
    abstract fun bindsUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}