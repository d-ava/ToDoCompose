package com.example.todocompose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todocompose.domain.model.RTShopItem
import com.example.todocompose.presentation.ShopItemCard

@Composable
fun TestScreen() {
    var testList by remember {
        mutableStateOf((1..40).map {
            RTShopItem(text = "number $it", done = false)
        })
    }


    LazyColumn(modifier = Modifier.fillMaxSize(), content = {
        items(testList.size) { i ->
            ShopItemCard(shopItem = testList[i], delete = { }, isDone = {
                testList = testList.mapIndexed { j, item ->
                    if (i == j){
                        item.copy(done = !item.done!!)
                    }else item
                }
            })
        }
    })
}