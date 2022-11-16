package com.example.todocompose.ui

import android.util.Log.d
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.todocompose.data.ToDo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainViewModel:ViewModel() {


    private val db = Firebase.database
    private val myRef = db.getReference("ToDoItem")

    private val list = mutableListOf<ToDo>()
    private val listdb = mutableListOf<ToDo>()

    val todoList:MutableState<List<ToDo>> = mutableStateOf(mutableListOf())

    fun addToDoItem(item:ToDo){

        list.add(item)
        todoList.value=list
    }

//    fun remove(s:String){
//        list.remove(s)
//        todoList.value=list
//        d("---", "from view model, was clicked to remove $s from list $list")
//    }

    fun addToRealtimeDatabase(item:ToDo){
        listdb.add(item)
        myRef.setValue(listdb)

    }

     fun readFromRealtimeDatabase(){
        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value
                d("---", "value from db is - $value")
            }

            override fun onCancelled(error: DatabaseError) {
                d("---", "Failed ${error.toException()}")
            }


        })

    }



}