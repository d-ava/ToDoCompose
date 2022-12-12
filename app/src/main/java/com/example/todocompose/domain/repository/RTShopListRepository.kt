package com.example.todocompose.domain.repository

import com.example.todocompose.domain.model.RTShopItem
import com.example.todocompose.util.Resource
import kotlinx.coroutines.flow.Flow

interface RTShopListRepository {

//    fun getRTShopListItemsOnce(): Flow<Resource<List<RTShopItem>>>
    fun getRTShopItemsFromRepo(): Flow<List<RTShopItem>>

}