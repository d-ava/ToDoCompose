package com.example.todocompose.presentation

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.domain.model.RTShopItem
import com.example.todocompose.util.Constants.TAG
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RTShopListViewModel(
    private val groupCode: String
) : ViewModel() {

    private val db = Firebase.database.reference
    private val groupRef = db.child("RTShop").child(groupCode)

    private val _itemsStateToDo = MutableStateFlow<List<RTShopItem?>>(emptyList())
    val itemStateToDo: StateFlow<List<RTShopItem?>> = _itemsStateToDo.asStateFlow()

    init {
        getRTShopItems()
    }

    private fun getRTShopItems() = viewModelScope.launch {
        val itemsRef = groupRef.child("items")
        itemsRef.keepSynced(true)
        val event = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.children.map {
                    it.getValue<RTShopItem>()
                }
                _itemsStateToDo.value = items
            }
            override fun onCancelled(error: DatabaseError) {
                d(TAG, "error: ${error.message}")
            }
        }
        itemsRef.addValueEventListener(event)
    }

    fun addNewShopItem(text: String, isDone: Boolean, senderToken: String) {
        val safeText = text.replace("/", "").trim()
        if (safeText.isEmpty()) return
        viewModelScope.launch {
            val shopItem = RTShopItem(text = safeText, done = isDone, senderToken = senderToken)
            groupRef.child("items").child(safeText).setValue(shopItem)
        }
    }

    fun removeShopItem(text: String) {
        groupRef.child("items").child(text).removeValue()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    d(TAG, "$text removed")
                } else {
                    d(TAG, "cant remove: ${task.exception?.localizedMessage}")
                }
            }
    }

    fun saveToken(token: String) {
        groupRef.child("tokens").child(token).setValue(token)
    }
}