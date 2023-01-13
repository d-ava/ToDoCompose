package com.example.todocompose.data.repository

import android.util.Log.d
import com.example.todocompose.domain.model.RTShopItem
import com.example.todocompose.domain.repository.RTShopListRepository
import com.example.todocompose.util.Constants.RTShop
import com.example.todocompose.util.Constants.TAG
import com.example.todocompose.util.ResourceToDo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class RTShopListRepositoryImpl(

) : RTShopListRepository {


    private val auth1: FirebaseAuth = FirebaseAuth.getInstance()
    private val database1 = FirebaseDatabase.getInstance()
    private val databaseReference1 = database1.reference.child(RTShop)
    private val user1 = auth1.currentUser
    private val userReference1 = databaseReference1.child(user1?.uid!!)
    private val db = Firebase.database.getReference(RTShop)

    override suspend fun getRTShopItems777(): Flow<ResourceToDo<List<RTShopItem?>>> {
        return callbackFlow {
            kotlinx.coroutines.delay(500)

            trySend(ResourceToDo.Loading)


            userReference1.keepSynced(true)
            val event = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val items = snapshot.children.map {
                        it.getValue<RTShopItem>()
                    }
                    d(TAG, "from repo $items")
                    trySend(ResourceToDo.Success(items))
                }

                override fun onCancelled(error: DatabaseError) {
                    d(TAG, "from repo ${error.message}")
//                    trySend(ResourceToDo.Error(Throwable(error.message)))
                }
            }
            userReference1.addValueEventListener(event)
            awaitClose { close() }
        }
    }


    override suspend fun getRTShopItems555(): Flow<ResourceToDo<List<RTShopItem?>>> {
        return callbackFlow {
            trySend(ResourceToDo.Loading)
            db.keepSynced(true)

            val event = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val items = snapshot.children.map {
                        it.getValue<RTShopItem>()
                    }
                    trySend(ResourceToDo.Success(items))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(ResourceToDo.Error(Throwable(error.message)))
                }
            }
            db.addValueEventListener(event)
            awaitClose { close() }

        }

    }


}