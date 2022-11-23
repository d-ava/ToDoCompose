package com.example.todocompose.shopData

import kotlinx.coroutines.flow.Flow

class ShoppingListRepoImpl(private val dao:ShopDao):ShoppingListRepo {

    override suspend fun insertShopItem(shop: Shop) {
        dao.insertShopItem(shop)
    }

    override suspend fun deleteShopItem(shop: Shop) {
        dao.deleteShopItem(shop)
    }

    override suspend fun deleteAllShopItems() {
        dao.deleteAllShoppingItems()
    }

    override suspend fun getShopItemById(id: Int): Shop? {
       return dao.getShopItemById(id)
    }

    override fun getAllShopItems(): Flow<List<Shop>> {
        return dao.getShopItems()
    }
}