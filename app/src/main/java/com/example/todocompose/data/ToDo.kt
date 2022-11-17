package com.example.todocompose.data

data class ToDo(
    val id:Int=0,
    val text:String="default txt",
    var markAsDone:Boolean=true
)
