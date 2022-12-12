//package com.example.todocompose.presentation
//
//import android.widget.ProgressBar
//import androidx.compose.animation.core.animateDpAsState
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.size
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.BlurredEdgeTreatment
//import androidx.compose.ui.draw.blur
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.example.todocompose.R
//import com.example.todocompose.domain.model.Response
//import com.example.todocompose.domain.repository.ShopItems
//
//@Composable
//fun FirebaseShopListScreen(
//    viewModel: FirebaseShopListViewModel = hiltViewModel()
//) {
//
//
//    var pressed by remember { mutableStateOf(true) }
//    val animatedBlur by animateDpAsState(targetValue = if (pressed) 16.dp else 0.dp)
//
////    var list = mutableListOf<ShopItems>()
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Text(
//            text = "Firebase Shop List",
//            fontWeight = FontWeight.Bold,
//            fontSize = 48.sp,
//            color = Color.Red,
//            textAlign = TextAlign.Center,
//            modifier = Modifier
//                .blur(animatedBlur, edgeTreatment = BlurredEdgeTreatment.Unbounded)
//                .clickable { pressed = !pressed }
//        )
//
////        if (shoppingList.value.isEmpty()) {
////            Text(text = "empty shopping list, please blablabla")
////            Image(
////                painter = painterResource(id = R.drawable.ic_baseline_shopping_cart_24),
////                contentDescription = "shopping cart", modifier = Modifier.size(128.dp),
////                colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary)
////            )
////        }
//
//        ShopItems() {
//           val t = it
//
//
//        }
//
//
//    }
//}
//
//@Composable
//fun ShopItems(
//    vm: FirebaseShopListViewModel = hiltViewModel(),
//    shopItemContent: @Composable (shopItems: ShopItems) -> Unit
//) {
//    when (val shopItemsResponse = vm.shopItemsResponse) {
//        is Response.Loading -> {}
//        is Response.Success -> shopItemContent(shopItemsResponse.data)
//        is Response.Error -> {}
//    }
//
//}