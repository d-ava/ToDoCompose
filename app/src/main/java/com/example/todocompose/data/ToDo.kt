package com.example.todocompose.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    @PrimaryKey
    val id:Int?=null,
    val text:String,
    var isDone:Boolean
)
