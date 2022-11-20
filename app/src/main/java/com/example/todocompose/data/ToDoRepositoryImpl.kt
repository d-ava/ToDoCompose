package com.example.todocompose.data

import kotlinx.coroutines.flow.Flow

class ToDoRepositoryImpl(private val dao: ToDoDao):ToDoRepository {

    override suspend fun insertToDo(toDo: ToDo) {
        dao.insertToDo(toDo)
    }

    override suspend fun deleteToDo(toDo: ToDo) {
        dao.deleteToDo(toDo)
    }

    override suspend fun getToDoById(id: Int): ToDo? {
        return dao.getToDoById(id)
    }

    override fun getAllToDos(): Flow<List<ToDo>> {
        return dao.getAllToDos()
    }
}