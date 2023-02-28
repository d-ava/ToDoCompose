package com.example.todocompose.presentation

import android.util.Log.d
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.data.repository.RTShopListRepositoryImpl
import com.example.todocompose.domain.model.RTShopItem
import com.example.todocompose.domain.repository.RTShopListRepository
import com.example.todocompose.ui.state.RTShopItemState
import com.example.todocompose.util.Constants.RTShop
import com.example.todocompose.util.Constants.TAG
import com.example.todocompose.util.Resource2
import com.example.todocompose.util.ResourceToDo
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

//@HiltViewModel
class RTShopListViewModel(
    private val repo: RTShopListRepository = RTShopListRepositoryImpl()
) : ViewModel() {

    val auth by lazy { Firebase.auth }

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


        getRTShopItems777()

//        authResult.value = auth.currentUser?.uid


    }


//    private val _itemsState = MutableStateFlow(RTShopItemState())
//    val itemState: StateFlow<RTShopItemState> = _itemsState.asStateFlow()

    private val _itemsStateToDo = MutableStateFlow<List<RTShopItem?>>(emptyList())
    val itemStateToDo: StateFlow<List<RTShopItem?>> = _itemsStateToDo.asStateFlow()



    private fun getRTShopItems777() = viewModelScope.launch {
        val db = Firebase.database.getReference(RTShop)

        db.keepSynced(true)
        val event = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.children.map {
                    it.getValue<RTShopItem>()
                }

                _itemsStateToDo.value = items

            }
            override fun onCancelled(error: DatabaseError) {
                d(TAG, "from repo ${error.message}")

            }
        }
        db.addValueEventListener(event) // <---


    }

//    private fun getRTShopItems555() = viewModelScope.launch {
//
//        repo.getRTShopItems777().collect { result ->
//            when (result) {
//                is ResourceToDo.Success -> {
//                    _itemsState.update {
//                        it.copy(
//                            data = result.data,
//                            isLoading = false,
//                            errorMsg = null
//                        )
//                    }
//
//                    d(TAG, "from vm - result data - ${result.data}")
//
//                }
//                is ResourceToDo.Error -> {
//                    _itemsState.update {
//                        it.copy(data = null, isLoading = false, errorMsg = result.exception.message)
//                    }
//                }
//                is ResourceToDo.Loading -> {
//                    _itemsState.update { it.copy(data = null, isLoading = true, errorMsg = null) }
//
//                }
//            }
//        }
//    }


    fun testLoading() {
        viewModelScope.launch {
            itemsLoading.value = true
            delay(3000)
            itemsLoading.value = false
        }
    }

//    private var listCompose = mutableListOf<RTShopItem>()

//    fun addNewShopItem(text: String, isDone: Boolean) {
//        viewModelScope.launch {
//            val shopItem = RTShopItem(text = text, done = isDone)
//            db.child(RTShop).child(text).setValue(shopItem)
//        }
//    }


    //222 when user is registered
    fun addNewShopItem222(text: String, isDone: Boolean) {


        viewModelScope.launch {
            val shopItem = RTShopItem(text = text, done = isDone)
            //db.child(RTShop).child(auth.uid!!).child(text).setValue(shopItem)  <--- when registered
            db.child(RTShop).child(text).setValue(shopItem)  //without auth
        }
    }

//    fun getRTShopItems2222() {
//        val auth1: FirebaseAuth = FirebaseAuth.getInstance()
//        val database1 = FirebaseDatabase.getInstance()
//        val databaseReference1 = database1.reference.child(RTShop)
//        val user1 = auth1.currentUser
//        val userReference1 = databaseReference1.child(user1?.uid!!)
//
//
//        userReference1.addValueEventListener(object : ValueEventListener {
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                viewModelScope.launch {
//                    if (snapshot.exists()) {
//                        val listCompose222 = mutableListOf<RTShopItem>()
//                        for (s in snapshot.children) {
//                            val item = s.getValue(RTShopItem::class.java)!!
//                            listCompose222.add(item)
//                        }
//                        shopItemsCompose.value = listCompose222
//                    }
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//        })
//    }

//    fun getRTShopItems333() {
//        val auth1: FirebaseAuth = FirebaseAuth.getInstance()
//        val database1 = FirebaseDatabase.getInstance()
//        val databaseReference1 = database1.reference.child(RTShop)
//        val user1 = auth1.currentUser
//        val userReference1 = databaseReference1.child(user1?.uid!!)
//
//        viewModelScope.launch {
//
//
//            userReference1.addValueEventListener(object : ValueEventListener {
//
//
//                override fun onDataChange(snapshot: DataSnapshot) {
//
//
//                    val test = snapshot.value
//                    d(TAG, "snaphot from vm - $test")
//
//                    try {
//
//                        if (snapshot.exists()) {
//                            val listCompose222 = mutableListOf<RTShopItem>()
//                            for (s in snapshot.children) {
//                                val item = s.getValue(RTShopItem::class.java)!!
//                                listCompose222.add(item)
//                            }
//                            shopItemsCompose.value = listCompose222
//                        }
//                    } catch (e: IOException) {
//                        d(TAG, e.message ?: "unknown error")
//                    }
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//
//                }
//            })
//
//        }
//    }


    fun removeShopItem(text: String) {
//        db2.child(text).removeValue().addOnCompleteListener { task ->
        db.child(RTShop).child(text).removeValue().addOnCompleteListener { task ->
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
//    private val _userLogInStatus: MutableSharedFlow<Resource2<AuthResult>> = MutableSharedFlow()
//    val userLogInStatus: SharedFlow<Resource2<AuthResult>> = _userLogInStatus


//    fun authUser222(email: String, password: String) {
//        viewModelScope.launch {
//
//
//            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
//                if (it.isSuccessful) {
////                    authResult.value = auth.currentUser?.email!!
//
//
//                } else {
//                    d(TAG, "NO - ${it.exception?.localizedMessage}")
//                }
//            }
//
//        }
//    }

//    fun authUser(email: String, password: String) {
//        viewModelScope.launch {
//            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
//                if (it.isSuccessful) {
//                    authResult.value = auth.currentUser?.email!!
//
//                    d(TAG, "YES - ${it.result}")
//                } else {
//                    d(TAG, "NO - ${it.exception?.localizedMessage}")
//                }
//            }
//
//        }
//    }
//
//    fun signOut() {
//        auth.signOut()
//        authResult.value = auth.currentUser?.uid
//    }


}