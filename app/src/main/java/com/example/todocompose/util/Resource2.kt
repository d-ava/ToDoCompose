package com.example.todocompose.util

sealed class Resource2<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T? = null) : Resource2<T>(data)
    class Loading<T>(data: T? = null) : Resource2<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource2<T>(data, message)

}
