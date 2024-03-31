package com.example.data.mapper

import com.example.data.room.entity.TodoEntity
import com.example.domain.model.TodoModel

fun TodoModel.toEntity() = TodoEntity(
    id = id,
    task = task,
    date = date
)

interface Mapper<I, O> {
    fun map(input: I): O
}

interface ListMapper<I, O> : Mapper<List<I>, List<O>>
class TodoMapper : ListMapper<TodoEntity, TodoModel> {
    override fun map(input: List<TodoEntity>): List<TodoModel> {
        return input.map {
            TodoModel(
                id = it.id,
                task = it.task,
                date = it.date
            )
        }
    }
}