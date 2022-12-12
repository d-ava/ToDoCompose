//package com.example.todocompose.data.repository
//
//import com.example.todocompose.domain.model.FirebaseItem
//import com.example.todocompose.domain.model.Response
//import com.example.todocompose.domain.repository.AddShopItemResponse
//import com.example.todocompose.domain.repository.DeleteShopItemResponse
//import com.example.todocompose.domain.repository.FirebaseShopListRepository
//import com.example.todocompose.util.Constants.SHOP_ITEMS
//import com.google.firebase.firestore.CollectionReference
//import kotlinx.coroutines.channels.awaitClose
//import kotlinx.coroutines.flow.callbackFlow
//import kotlinx.coroutines.tasks.await
//import javax.inject.Inject
//
//class FirebaseShopListRepositoryImpl @Inject constructor(
//    private val shopItemsRef: CollectionReference,
////    private val shopItemsQuery: Query
//
//) : FirebaseShopListRepository {
//    override fun getShopListItemsFromFirestore() = callbackFlow {
//        val snapshotListener = shopItemsRef.orderBy(SHOP_ITEMS).addSnapshotListener { snapshot, e ->
//            val shopItemsResponse = if (snapshot != null) {
//                val shopItems = snapshot.toObjects(FirebaseItem::class.java)
//                Response.Success(shopItems)
//            } else {Response.Error(e)}
//            trySend(shopItemsResponse)
//        }
//        awaitClose{
//            snapshotListener.remove()
//        }
//
//    }
//
//    override suspend fun addShopItemToFirestore(text: String): AddShopItemResponse {
//        return try {
//            val id = shopItemsRef.document().id
//            val shopItem = FirebaseItem(
//                id = id,
//                text = text
//            )
//            shopItemsRef.document(id).set(shopItem).await()
//            Response.Success(true)
//        }catch (e:Exception){
//            Response.Error(e)
//        }
//    }
//
//    override suspend fun deleteShopItemFromFirestore(shopId: String): DeleteShopItemResponse {
//        return try {
//            shopItemsRef.document(shopId).delete().await()
//            Response.Success(true)
//        }catch (e:Exception){
//            Response.Error(e)
//        }
//    }
//
//
//}