//package com.example.todocompose.presentation
//
//import androidx.compose.runtime.State
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.todocompose.domain.model.FirebaseItem
//import com.example.todocompose.domain.model.Response
//import com.example.todocompose.domain.repository.AddShopItemResponse
//import com.example.todocompose.domain.repository.DeleteShopItemResponse
//import com.example.todocompose.domain.repository.ShopItemsResponse
//import com.example.todocompose.domain.use_case.UseCases
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.collect
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//
//class FirebaseShopListViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {
//
//    var shopItemsResponse by mutableStateOf<ShopItemsResponse>(Response.Loading)
//        private set
//    var addShopItemResponse by mutableStateOf<AddShopItemResponse>(Response.Success(false))
//        private set
//    var deleteShopItemResponse by mutableStateOf<DeleteShopItemResponse>(Response.Success(false))
//        private set
//    var openDialog by mutableStateOf(false)
//        private set
//
//
//
//
//    init {
//        getShopItemsFromFirestore()
//    }
//
//    private fun getShopItemsFromFirestore() = viewModelScope.launch{
//        useCases.getShoppingItems().collect{
//            shopItemsResponse=it
//        }
//
//    }
//
//    fun addShopItem(text: String)=viewModelScope.launch {
//        addShopItemResponse = Response.Loading
//        addShopItemResponse = useCases.addShopItem(text)
//    }
//
//    fun deleteShopItem(id: String)=viewModelScope.launch {
//        deleteShopItemResponse = Response.Loading
//        deleteShopItemResponse = useCases.deleteShopItem(id)
//    }
//    fun openDialog(){
//        openDialog=true
//    }
//
//    fun closeDialog(){
//        openDialog=false
//    }
//
//
//}