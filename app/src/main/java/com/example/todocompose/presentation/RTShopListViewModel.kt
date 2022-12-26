package com.example.todocompose.presentation

import android.util.Log.d
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.data.repository.RTShopListRepositoryImpl
import com.example.todocompose.domain.model.RTShopItem
import com.example.todocompose.domain.repository.RTShopListRepository
import com.example.todocompose.util.Constants.RTShop
import com.example.todocompose.util.Constants.TAG
import com.example.todocompose.util.Resource
import com.example.todocompose.util.Resource2
import com.example.todocompose.util.Screen
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.io.IOException

//@HiltViewModel
class RTShopListViewModel(
    private val repo: RTShopListRepository = RTShopListRepositoryImpl()
) : ViewModel() {

    private val auth by lazy { Firebase.auth }

//    private val auth1: FirebaseAuth = FirebaseAuth.getInstance()
//    private val database1 = FirebaseDatabase.getInstance()
//    var databaseReference1 = database1.reference.child(RTShop)
//    private val user1 = auth1.currentUser
//    private val userReference1 = databaseReference1.child(user1?.uid!!)


    //this is the list for lazyColumn in Composable
    val shopItemsCompose: MutableState<List<RTShopItem>> = mutableStateOf(listOf())

    // for loading
    val itemsLoading = mutableStateOf(false)

    val authResult: MutableState<String?> = mutableStateOf("")

    private val db = Firebase.database.reference
    private val db2 = FirebaseDatabase.getInstance().getReference(RTShop)

//    private var _shopListItems = mutableStateOf<List<RTShopItem>>(emptyList())
//    val shopListItems: State<List<RTShopItem>> = _shopListItems
//
//    var _shopItems333 = mutableStateListOf<RTShopItem>()
//
//    private var _lll: MutableStateFlow<RTShopItem> = MutableStateFlow(RTShopItem())
//    val lll: StateFlow<RTShopItem> = _lll

    init {

        authResult.value = auth.currentUser?.uid
//        getRTShopItems222()
    }

    fun testLoading(){
        viewModelScope.launch {
            itemsLoading.value = true
            delay(3000)
            itemsLoading.value = false
        }
    }

//    private var listCompose = mutableListOf<RTShopItem>()

    fun addNewShopItem(text: String, isDone: Boolean) {
        viewModelScope.launch {
            val shopItem = RTShopItem(text = text, done = isDone)
            db.child(RTShop).child(text).setValue(shopItem)
        }
    }

//    fun getRTShopItems() {
//        db2.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                viewModelScope.launch {
//                    if (snapshot.exists()) {
//                        val listCompose2 = mutableListOf<RTShopItem>()
//                        for (s in snapshot.children) {
//                            val item = s.getValue(RTShopItem::class.java)!!
//                            listCompose2.add(item)
//                        }
//                        shopItemsCompose.value = listCompose2
//                    }
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//            }
//        })
//    }


    //222 when user is registered
    fun addNewShopItem222(text: String, isDone: Boolean) {


        viewModelScope.launch {
            val shopItem = RTShopItem(text = text, done = isDone)
            db.child(RTShop).child(auth.uid!!).child(text).setValue(shopItem)
        }
    }

    fun getRTShopItems2222() {
        val auth1: FirebaseAuth = FirebaseAuth.getInstance()
        val database1 = FirebaseDatabase.getInstance()
        val databaseReference1 = database1.reference.child(RTShop)
        val user1 = auth1.currentUser
        val userReference1 = databaseReference1.child(user1?.uid!!)


        userReference1.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                viewModelScope.launch {
                    if (snapshot.exists()) {
                        val listCompose222 = mutableListOf<RTShopItem>()
                        for (s in snapshot.children) {
                            val item = s.getValue(RTShopItem::class.java)!!
                            listCompose222.add(item)
                        }
                        shopItemsCompose.value = listCompose222
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun getRTShopItems333() {
        val auth1: FirebaseAuth = FirebaseAuth.getInstance()
        val database1 = FirebaseDatabase.getInstance()
        val databaseReference1 = database1.reference.child(RTShop)
        val user1 = auth1.currentUser
        val userReference1 = databaseReference1.child(user1?.uid!!)


        userReference1.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                viewModelScope.launch {

                    try {
                        d(TAG, "LOADING start")
//                        itemsLoading.value = true
                        if (snapshot.exists()) {
                            val listCompose222 = mutableListOf<RTShopItem>()
                            for (s in snapshot.children) {
                                val item = s.getValue(RTShopItem::class.java)!!
                                listCompose222.add(item)
                            }
                            shopItemsCompose.value = listCompose222
                            d(TAG, "LOADING items")
                        }
//                        itemsLoading.value = false
                        d(TAG, "LOADING stop")

                    }catch (e: IOException){
                        d(TAG, e.message ?: "unknown error")
                    }



                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }




    fun removeShopItem(text: String) {
//        db2.child(text).removeValue().addOnCompleteListener { task ->
        db.child(RTShop).child(auth.uid!!).child(text).removeValue().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                d(TAG, "$text is removed")
                d(TAG, "VM list - ${shopItemsCompose.value}")
            } else {
                d(TAG, "cant remove item. ${task.exception?.localizedMessage}")
            }


        }
    }

    // auth result
//    val userLogInStatus: MutableState<Resource2<AuthResult>> = mutableStateOf()
    private val _userLogInStatus: MutableSharedFlow<Resource2<AuthResult>> = MutableSharedFlow()
    val userLogInStatus: SharedFlow<Resource2<AuthResult>> = _userLogInStatus


    fun authUser222(email: String, password: String) {
        viewModelScope.launch {


            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
//                    authResult.value = auth.currentUser?.email!!


                } else {
                    d(TAG, "NO - ${it.exception?.localizedMessage}")
                }
            }

        }
    }

    fun authUser(email: String, password: String) {
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    authResult.value = auth.currentUser?.email!!

                    d(TAG, "YES - ${it.result}")
                } else {
                    d(TAG, "NO - ${it.exception?.localizedMessage}")
                }
            }

        }
    }

    fun signOut() {
        auth.signOut()
        authResult.value = auth.currentUser?.uid
    }


}