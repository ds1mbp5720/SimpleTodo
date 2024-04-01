package com.example.presentation

import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.model.TodoModel
import com.example.domain.model.UserModel
import com.example.domain.repository.TodoRepository
import com.example.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository,
    application: Application
) : AndroidViewModel(application) {
    val todoList = MutableLiveData<List<TodoModel>>()
    val userInfo = MutableLiveData<UserModel>()
    fun setUserInfo(context: Context, uri: Uri, name: String, birthday: String){
        viewModelScope.launch {
            userRepository.setUserInfo(context = context, uri = uri.toString(), name = name, birthday = birthday)
        }
    }
    fun getUsrInfo(context: Context) {
        viewModelScope.launch{
            userInfo.value = userRepository.getUserInfo(context)
        }
    }
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
    fun findTodo(id: Long): TodoModel? {
        return todoList.value?.find { it.id == id }
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