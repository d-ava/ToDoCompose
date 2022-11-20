package com.example.todocompose.data

import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    suspend fun insertToDo(toDo: ToDo)

    suspend fun deleteToDo(toDo: ToDo)

    suspend fun getToDoById(id:Int):ToDo?


    fun getAllToDos(): Flow<List<ToDo>>


}