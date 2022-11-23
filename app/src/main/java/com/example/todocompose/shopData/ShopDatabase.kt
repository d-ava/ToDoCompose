package com.example.todocompose.shopData

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Shop::class],
    version = 1
)
abstract class ShopDatabase :RoomDatabase(){

abstract val dao: ShopDao
}