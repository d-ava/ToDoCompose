package com.example.todocompose.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
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
import com.example.todocompose.R
import com.example.todocompose.shopData.Shop

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShoppingScreen(viewModel: ShopViewModel = hiltViewModel()) {

    val shoppingList = viewModel.shoppingItems.collectAsState(initial = emptyList())

    var pressed by remember { mutableStateOf(true) }
    val animatedBlur by animateDpAsState(targetValue = if (pressed) (shoppingList.value.size).dp else 0.dp)
    var shoppingTextFieldState by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Shopping List",
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
            color = Color.Red,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .blur(animatedBlur, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                .clickable { pressed = !pressed }
        )

        if (shoppingList.value.isEmpty()) {
            Text(text = "empty shopping list, please blablabla")
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_shopping_cart_24),
                contentDescription = "shopping cart", modifier = Modifier.size(128.dp),
                colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary)
            )
        }

        LazyVerticalGrid(cells = GridCells.Fixed(1), modifier = Modifier.weight(0.6f), content = {
            items(shoppingList.value.reversed()) { shop ->
                ShopItem(shop = shop, delete = {
                    viewModel.deleteShoppingItem(it)
                }, )

            }
        })

        Row(modifier = Modifier.fillMaxWidth()) {


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
                        viewModel.insertShoppingItem(
                            shopItem = Shop(
                                text = shoppingTextFieldState.text,
                                isDone = true
                            )
                        )
                        shoppingTextFieldState = TextFieldValue("")

                    }
                ),


                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(8.dp)
                    .height(64.dp)


            )

            Image(painter = painterResource(id = R.drawable.ic_baseline_double_arrow_24),
                contentDescription = "double arrow",
                colorFilter = ColorFilter.tint(color = Color.Red),
                modifier = Modifier

                    .size(64.dp)
                    .fillMaxWidth(0.2f)
                    .clickable {
                        viewModel.insertShoppingItem(
                            shopItem = Shop(
                                text = shoppingTextFieldState.text,
                                isDone = true
                            )
                        )
                        shoppingTextFieldState = TextFieldValue("")
                    }
                    .align(Alignment.CenterVertically)

                    .padding(8.dp)
            )

        }

        Text(fontSize = 32.sp,
            text = "Empty shopping List",

            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clickable {
                    viewModel.emptyShoppingList()
                })


    }


}