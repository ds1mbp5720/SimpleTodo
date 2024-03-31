package com.example.domain.repository

import com.example.domain.model.TodoModel
import io.reactivex.Single

interface TodoRepository {
    fun getTodoList(): Single<List<TodoModel>>
    fun insertTodo(todo: TodoModel)
    fun updateTodo(todo: TodoModel)
    fun deleteTodo(todo: TodoModel)
    fun execute(
        onSuccess: ((t: List<TodoModel>) -> Unit),
        onError: ((t: Throwable) -> Unit),
        onFinished: () -> Unit = {}
    )
}