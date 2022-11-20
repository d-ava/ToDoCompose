package com.example.todocompose.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(toDo: ToDo)

    @Delete
    suspend fun deleteToDo(toDo: ToDo)

    @Query("SELECT * FROM toDo WHERE id = :id")
    suspend fun getToDoById(id:Int):ToDo?

    @Query("SELECT * FROM toDo")
    fun getAllToDos(): Flow<List<ToDo>>

}