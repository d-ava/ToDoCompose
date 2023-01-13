package com.example.todocompose.domain.repository

import com.example.todocompose.domain.model.RTShopItem
import com.example.todocompose.util.Resource
import com.example.todocompose.util.ResourceToDo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

interface RTShopListRepository {




    suspend fun getRTShopItems555():Flow<ResourceToDo<List<RTShopItem?>>>
    suspend fun getRTShopItems777():Flow<ResourceToDo<List<RTShopItem?>>>





}