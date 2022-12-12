//package com.example.todocompose.domain.use_case
//
//import com.example.todocompose.domain.repository.FirebaseShopListRepository
//
//class DeleteShopItem(private val repo: FirebaseShopListRepository) {
//
//    suspend operator fun invoke (id:String) = repo.deleteShopItemFromFirestore(id)
//}