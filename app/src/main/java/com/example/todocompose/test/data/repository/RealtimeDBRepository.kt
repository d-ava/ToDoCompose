package com.example.todocompose.test.data.repository

import com.example.todocompose.test.data.model.RealtimeDBUser
import com.example.todocompose.test.data.util.RealtimeDBResult
import kotlinx.coroutines.flow.Flow

interface RealtimeDBRepository {
    suspend fun createUser(user: RealtimeDBUser): Flow<RealtimeDBResult<String>>
    suspend fun getUser(): Flow<RealtimeDBResult<List<RealtimeDBUser?>>>
    suspend fun updateUser(user: RealtimeDBUser): Flow<RealtimeDBResult<String>>
    suspend fun deleteUser(user: RealtimeDBUser): Flow<RealtimeDBResult<String>>
}