package com.example.todocompose.shopData

import com.example.todocompose.data.ToDo
import kotlinx.coroutines.flow.Flow

interface ShoppingListRepo {

    suspend fun insertShopItem(shop: Shop)

    suspend fun deleteShopItem(shop: Shop)

    suspend fun deleteAllShopItems()

    suspend fun getShopItemById(id:Int): Shop?



    fun getAllShopItems(): Flow<List<Shop>>


}