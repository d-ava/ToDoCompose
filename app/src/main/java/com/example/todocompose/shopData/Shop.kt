package com.example.todocompose.shopData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Shop(
    @PrimaryKey val id: Int? = null,
    val text: String,
    var isDone: Boolean
)
