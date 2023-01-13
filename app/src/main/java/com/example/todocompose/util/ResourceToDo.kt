package com.example.todocompose.util

sealed class ResourceToDo<out R> {
    data class Success<out T>(val data: T) : ResourceToDo<T>()
    data class Error(val exception: Throwable) : ResourceToDo<Nothing>()
    object Loading : ResourceToDo<Nothing>()
}