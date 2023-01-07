package com.example.todocompose.test.data.util

sealed class RealtimeDBResult<out R> {
    data class Success<out T>(val data: T) : RealtimeDBResult<T>()
    data class Error(val exception: Throwable) : RealtimeDBResult<Nothing>()
    object Loading : RealtimeDBResult<Nothing>()
}