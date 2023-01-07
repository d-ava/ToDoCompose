package com.example.todocompose.data.repository

import android.util.Log.d
import androidx.lifecycle.viewModelScope
import com.example.todocompose.domain.model.RTShopItem
import com.example.todocompose.domain.repository.RTShopListRepository
import com.example.todocompose.util.Constants.RTShop
import com.example.todocompose.util.Constants.TAG
import com.example.todocompose.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException

class RTShopListRepositoryImpl(

) : RTShopListRepository {

    private val database = Firebase.database
    private val db3 = FirebaseDatabase.getInstance().getReference(RTShop)

    private val listRepo = mutableListOf<RTShopItem>()

    fun getItemsFromRepo2(): Flow<List<RTShopItem>> {

        return flow<List<RTShopItem>> {
            db3.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {




                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }.flowOn(Dispatchers.IO)

    }

    override fun getRTShopItemsFromRepo(): Flow<List<RTShopItem>> {
        return flow {
            database.getReference(RTShop)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val shopItems = snapshot.getValue<List<RTShopItem>>()!!
                        CoroutineScope(IO).launch {
                            d(TAG, "got results from repo $shopItems")
                            emit(shopItems)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        d(TAG, "failed to read value")
                    }
                })

        }
    }



}