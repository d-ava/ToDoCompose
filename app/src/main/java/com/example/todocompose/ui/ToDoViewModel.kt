package com.example.todocompose.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.TodoListEvent
import com.example.todocompose.data.ToDo
import com.example.todocompose.data.ToDoRepository
import com.example.todocompose.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    //states
    var todooo by mutableStateOf<ToDo>(ToDo(text = "first", isDone = false))
        private set

    var todoText by mutableStateOf("")
        private set

    val todos = repository.getAllToDos()

    private var deletedTodo: ToDo? = null

    private val _uiEvent = Channel<UiEvent>() //try shared flow as well
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event: TodoListEvent) {
        when (event) {
            is TodoListEvent.OnAddTodoClick -> {
                viewModelScope.launch {
                    if (todoText.isBlank()) {
                        sendUiEvent(
                            UiEvent.ShowSnackbar(
                                message = "enter some text"
                            )
                        )
                        return@launch
                    }
                    repository.insertToDo(
                        ToDo(
                            text = todoText,
                            isDone = false
                        )
                    )
                }
            }
            is TodoListEvent.OnUndoDeleteClick -> {
                deletedTodo?.let { todo ->
                    viewModelScope.launch {
                        repository.insertToDo(todo)
                    }

                }

            }
            is TodoListEvent.OnDeleteTodo -> {
                viewModelScope.launch {
                    deletedTodo = event.todo
                    repository.deleteToDo(event.todo)
                    sendUiEvent(
                        UiEvent.ShowSnackbar(
                            message = "todo deleted", action = "Undo"
                        )
                    )
                }

            }
            is TodoListEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repository.insertToDo(
                        event.todo.copy(isDone = event.isDone)
                    )
                }


            }
        }


    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}