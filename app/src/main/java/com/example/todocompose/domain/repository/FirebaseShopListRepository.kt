//package com.example.todocompose.domain.repository
//
//import com.example.todocompose.domain.model.FirebaseItem
//import com.example.todocompose.domain.model.Response
//import kotlinx.coroutines.flow.Flow
//
//
//
//typealias ShopItems = List<FirebaseItem>
//typealias ShopItemsResponse = Response<ShopItems>
//typealias AddShopItemResponse = Response<Boolean>
//typealias DeleteShopItemResponse = Response<Boolean>
//
//interface FirebaseShopListRepository {
//
//    fun getShopListItemsFromFirestore(): Flow<ShopItemsResponse>
//
//    suspend fun addShopItemToFirestore(text:String):AddShopItemResponse
//
//
//    suspend fun deleteShopItemFromFirestore(shopId: String): DeleteShopItemResponse
//
//}