package com.example.todocompose.ui

import androidx.lifecycle.ViewModel
import com.example.todocompose.TodoListEvent
import com.example.todocompose.data.ToDoRepository
import com.example.todocompose.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    val todos = repository.getAllToDos()

    private val _uiEvent = Channel<UiEvent>() //try shared flow as well
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event:TodoListEvent){
         when(event){
             is TodoListEvent.OnAddTodoClick -> {

             }
             is TodoListEvent.OnUndoDeleteClick -> {

             }
             is TodoListEvent.OnDeleteTodo -> {

             }
             is TodoListEvent.OnDoneChange -> {


             }
         }


    }


}