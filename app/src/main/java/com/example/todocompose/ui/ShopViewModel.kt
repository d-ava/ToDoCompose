package com.example.todocompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.shopData.Shop
import com.example.todocompose.shopData.ShoppingListRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(private val repo: ShoppingListRepo) : ViewModel() {

    val shoppingItems = repo.getAllShopItems()


    fun insertShoppingItem(shopItem:Shop){
        viewModelScope.launch {
            repo.insertShopItem(shopItem)
        }
    }

    fun emptyShoppingList(){
        viewModelScope.launch {
            repo.deleteAllShopItems()
        }
    }

    fun deleteShoppingItem(shopItem:Shop){
        viewModelScope.launch {
            repo.deleteShopItem(shopItem)
        }
    }
}