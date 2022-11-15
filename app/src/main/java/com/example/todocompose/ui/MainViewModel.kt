package com.example.todocompose.ui

import android.util.Log.d
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    private val list = mutableListOf<String>()

    val strings:MutableState<List<String>> = mutableStateOf(mutableListOf())

    fun addString(s:String){

        list.add(s)
        strings.value=list
    }

    fun remove(s:String){
        list.remove(s)
        strings.value=list
        d("---", "from view model, was clicked to remove $s from list $list")
    }
}