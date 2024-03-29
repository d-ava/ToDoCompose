package com.example.todocompose.presentation

import android.util.Log.d
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todocompose.R
import com.example.todocompose.ui.ItemsProgressIndicator
import com.example.todocompose.util.Constants.TAG
import com.example.todocompose.util.Screen

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun RTShopListScreen(vm: RTShopListViewModel = hiltViewModel(), navController: NavController) {

    //thats it
    val itemState by vm.itemStateToDo.collectAsState()


//    val listCompose2 = vm.shopItemsCompose.value //<<<<<<<<<<<<<<<<<<<<<<<<<<<<that's the list

//    var buttonState by remember { mutableStateOf("auth") } // test fro switching auth bottom sheet state

//    val itemsLoading = vm.itemsLoading.value

    var pressed by remember { mutableStateOf(true) } // for making blur


    val animatedBlur by animateDpAsState(targetValue = if (pressed) 4.dp else 0.dp)

    var shoppingTextFieldState by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var emailTextFieldState by remember { mutableStateOf(TextFieldValue("david@gmail.com")) }
    var passwordTextFieldState by remember { mutableStateOf(TextFieldValue("test123")) }

    //for bottom sheet
    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )




    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
            ) {

                Column() {


                    Text(
                        text = "not working yet", //"user - ${vm.authResult.value}",
                        modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                    )

                    Row(modifier = Modifier.padding(top = 64.dp)) {

                        Text(text = "sign out", fontWeight = FontWeight.Black, modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable { })

                        Text(text = "AUTH", modifier = Modifier
                            .padding(start = 16.dp)
                            .clickable {

                                navController.navigate(route = Screen.Auth.route)
                            })
                    }

                }


            }

        },
        sheetBackgroundColor = Color.Red,

        sheetPeekHeight = 32.dp,
        sheetElevation = 16.dp,


        ) {

        Box(modifier = Modifier.fillMaxWidth()) {


            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "買い物リスト-shopping list",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .blur(animatedBlur, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                        .padding(top = 16.dp)
                        .clickable {
                            pressed = !pressed
//                            vm.getRTShopItems777()

                        })

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 64.dp)
                        .weight(0.6f)
                ) {
                    items(itemState) { item ->
                        ShopItemCard(shopItem = item!!,
                            delete = {
                                vm.removeShopItem(item.text!!)
                            },
                            isDone = {
                                vm.addNewShopItem222(text = item.text!!, isDone = !item.done!!)
                            })


                    }
                }


                /////////////////////////////////////////////----------------------------------------------------------------------------

                /*when {
                    itemState.isLoading -> {
                        Text(text = "Loading")
                        ItemsProgressIndicator(show = true)
                    }
                    !itemState.isLoading && !itemState.errorMsg.isNullOrEmpty() -> {
                        Text(text = itemState.errorMsg!!)
                    }
                    itemState.data.isNullOrEmpty() -> {
                        Text(text = "Empty123")
                        d(TAG, "from composable12 ${itemState.data}")
                    }
                    !itemState.data.isNullOrEmpty() -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 64.dp)
                                .weight(0.6f)
                        ) {
                            items(itemState.data!!) { item ->
                                ShopItemCard(shopItem = item!!, delete = {
                                    vm.removeShopItem(item.text!!)
                                }, isDone = {
                                    vm.addNewShopItem222(text = item.text!!, isDone = !item.done!!)
                                })


                            }
                        }
                    }

                }*/


                ///////////////////


//                    LazyColumn(modifier = Modifier
//                        .fillMaxSize()
//                        .padding(top = 64.dp)
//                        .weight(0.6f), content = {
//                        items(listCompose2.size) { i ->
//                            val item1 = listCompose2[i]
//                            ShopItemCard(shopItem = item1, delete = {
//                                vm.removeShopItem(item1.text!!)
//                            }, isDone = {
//
//                                vm.addNewShopItem222(item1.text!!, isDone = !item1.done!!)
//                            })
//                        }
//                    })


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp)
                ) {


                    OutlinedTextField(
                        value = shoppingTextFieldState,
                        onValueChange = { shoppingTextFieldState = it },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = MaterialTheme.colors.primary,
                            cursorColor = MaterialTheme.colors.primary,
                            leadingIconColor = MaterialTheme.colors.onPrimary,
                            focusedLabelColor = MaterialTheme.colors.onPrimary,
                            disabledTextColor = MaterialTheme.colors.onPrimary
                        ),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_shopping_cart_24),
                                contentDescription = "icon"
                            )
                        },
                        singleLine = true,
                        placeholder = { Text(text = "enter text") },

                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {

                            }
                        ),

                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp)
                            .height(64.dp)


                    )

                    Image(painter = painterResource(id = R.drawable.ic_baseline_double_arrow_24),
                        contentDescription = "double arrow",
                        colorFilter = if (shoppingTextFieldState.text.isNotEmpty()) ColorFilter.tint(
                            color = MaterialTheme.colors.primary
                        ) else ColorFilter.tint(
                            color = Color.LightGray
                        ),
                        modifier = Modifier

                            .size(64.dp)
                            .fillMaxWidth(0.2f)
                            .clickable {
                                if (shoppingTextFieldState.text.isNotEmpty()) {

                                    vm.addNewShopItem222(
                                        text = shoppingTextFieldState.text,
                                        isDone = false
                                    )
                                }
                                shoppingTextFieldState = TextFieldValue("")
                            }
                            .align(Alignment.CenterVertically)
                            .padding(8.dp)
                    )


                }


            }
        }

    }


}
