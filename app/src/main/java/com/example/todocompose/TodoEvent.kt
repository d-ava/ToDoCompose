package com.example.todocompose

import com.example.todocompose.data.ToDo

sealed class TodoListEvent {
    data class OnDeleteTodo(val todo: ToDo) : TodoListEvent()
    data class OnDoneChange(val todo: ToDo, val isDone: Boolean) : TodoListEvent()
    object OnUndoDeleteClick:TodoListEvent()
    object OnAddTodoClick: TodoListEvent()

}
