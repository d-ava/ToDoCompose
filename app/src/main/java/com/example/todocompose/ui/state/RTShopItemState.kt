package com.example.todocompose.ui.state

import com.example.todocompose.domain.model.RTShopItem

data class RTShopItemState(
    val data: List<RTShopItem?>? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null


)
