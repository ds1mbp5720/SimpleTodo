package com.example.data.repository

import android.annotation.SuppressLint
import com.example.data.mapper.TodoMapper
import com.example.data.mapper.toEntity
import com.example.data.room.database.TodoDataBase
import com.example.domain.model.TodoModel
import com.example.domain.repository.TodoRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDataBase: TodoDataBase
) : TodoRepository {
    override fun getTodoList(): Single<List<TodoModel>> {
        return todoDataBase.getTodoDao().getTodoList()
            .map {
                TodoMapper().map(it)
            }
    }

    override fun insertTodo(todo: TodoModel) {
        todoDataBase.getTodoDao().insertTodo(todo = todo.toEntity())
    }

    override fun updateTodo(todo: TodoModel) {
        todoDataBase.getTodoDao().updateTodo(todo = todo.toEntity())
    }

    override fun deleteTodo(todo: TodoModel) {
        todoDataBase.getTodoDao().deleteTodo(todo = todo.toEntity())
    }

    @SuppressLint("CheckResult")
    override fun execute(
        onSuccess: (t: List<TodoModel>) -> Unit,
        onError: (t: Throwable) -> Unit,
        onFinished: () -> Unit
    ) {
        getTodoList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate(onFinished)
            .subscribe(onSuccess, onError)
    }
}