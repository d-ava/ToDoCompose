package com.example.todocompose.test.ui.state

import com.example.todocompose.test.data.model.RealtimeDBUser

data class RealtimeDBUserState(
    val data: List<RealtimeDBUser?>? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)