package com.example.todocompose.presentation

import android.util.Log.d
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.data.repository.RTShopListRepositoryImpl
import com.example.todocompose.domain.model.RTShopItem
import com.example.todocompose.domain.repository.RTShopListRepository
import com.example.todocompose.util.Constants.RTShop
import com.example.todocompose.util.Constants.TAG
import com.example.todocompose.util.Resource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class RTShopListViewModel(
    private val repo: RTShopListRepository = RTShopListRepositoryImpl()
) : ViewModel() {

    val shopItemsCompose: MutableState<List<RTShopItem>> = mutableStateOf(listOf())

    private val db = Firebase.database.reference
    private val db2 = FirebaseDatabase.getInstance().getReference(RTShop)

    private var _shopListItems = mutableStateOf<List<RTShopItem>>(emptyList())
    val shopListItems: State<List<RTShopItem>> = _shopListItems

    var _shopItems333 = mutableStateListOf<RTShopItem>()

    private var _lll: MutableStateFlow<RTShopItem> = MutableStateFlow(RTShopItem())
    val lll: StateFlow<RTShopItem> = _lll


    init {


    }




    private var listCompose = mutableListOf<RTShopItem>()

    fun getRTShopItems333() {

        db2.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                viewModelScope.launch {
                    if (snapshot.exists()) {
                        val listCompose2 = mutableListOf<RTShopItem>()
                        for (s in snapshot.children) {
                            val item = s.getValue(RTShopItem::class.java)!!
                            listCompose2.add(item)

                        }
                        shopItemsCompose.value = listCompose2
                    }
                }


            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

    }

    fun removeShopItem(text: String) {
        db2.child(text).removeValue().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                d(TAG, "$text is removed")
                d(TAG, "VM list - ${shopItemsCompose.value}")
            }else{
                d(TAG, "cant remove item. ${task.exception?.localizedMessage}")
            }


        }
    }


    fun addNewShopItem(text: String, isDone: Boolean) {

        viewModelScope.launch {
            val shopItem = RTShopItem(text = text, done = isDone)
            db.child(RTShop).child(text).setValue(shopItem)

        }
    }




}