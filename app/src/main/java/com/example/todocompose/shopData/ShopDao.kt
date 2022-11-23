package com.example.todocompose.shopData

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ShopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShopItem(shop:Shop)

    @Delete
    suspend fun deleteShopItem(shop:Shop)

    @Query("SELECT * FROM shop WHERE id=:id")
    suspend fun getShopItemById(id:Int):Shop?

    @Query ("SELECT * FROM shop ")
    fun getShopItems(): Flow<List<Shop>>

    @Query("DELETE FROM shop")
    suspend fun deleteAllShoppingItems()


}