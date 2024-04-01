package com.example.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.model.TodoModel
import com.example.domain.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    application: Application
) : AndroidViewModel(application) {
    val todoList = MutableLiveData<List<TodoModel>>()
    fun getTodoList() {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.getTodoList()
            todoRepository.execute(
                onSuccess = {
                    todoList.value = it
                },
                onError = {
                    Log.e("","Room Error (TodoList) $it")
                }
            )
        }
    }

    fun insertTodo(todo: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.insertTodo(todo)
        }
    }
    fun updateTodo(todo: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.updateTodo(todo)
        }
    }
    fun deleteTodo(todo: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteTodo(todo)
        }
    }
}