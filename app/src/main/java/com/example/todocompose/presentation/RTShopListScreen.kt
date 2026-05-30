package com.example.todocompose.presentation

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todocompose.R
import com.example.todocompose.util.GroupPreferences
import com.google.firebase.messaging.FirebaseMessaging

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun RTShopListScreen(navController: NavController) {

    val context = LocalContext.current
    val groupCode = remember { GroupPreferences.getOrCreateGroupCode(context) }
    val vm = remember { RTShopListViewModel(groupCode = groupCode) }

    val itemState by vm.itemStateToDo.collectAsState()

    var pressed by remember { mutableStateOf(true) }
    val animatedBlur by animateDpAsState(targetValue = if (pressed) 4.dp else 0.dp)

    var shoppingTextFieldState by remember { mutableStateOf(TextFieldValue("")) }
    var showCodeDialog by remember { mutableStateOf(false) }
    var newCodeInput by remember { mutableStateOf(TextFieldValue("")) }
    var myToken by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) myToken = task.result
        }
    }

    val sortedItems = remember(itemState) {
        itemState.sortedWith(compareBy { it?.done })
    }

    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)

    if (showCodeDialog) {
        AlertDialog(
            onDismissRequest = {
                showCodeDialog = false
                newCodeInput = TextFieldValue("")
            },
            title = { Text("Enter group code") },
            text = {
                Column {
                    Text("Type a 6-digit code to join your partner's list, or generate a random one.")
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = newCodeInput,
                        onValueChange = { if (it.text.length <= 6) newCodeInput = it },
                        label = { Text("6-digit code") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                }
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = {
                        newCodeInput = TextFieldValue((100000..999999).random().toString())
                    }) {
                        Text("Random")
                    }
                    Row {
                        TextButton(onClick = {
                            showCodeDialog = false
                            newCodeInput = TextFieldValue("")
                        }) {
                            Text("Cancel")
                        }
                        TextButton(onClick = {
                            if (newCodeInput.text.length == 6) {
                                GroupPreferences.saveGroupCode(context, newCodeInput.text)
                                showCodeDialog = false
                                (context as? android.app.Activity)?.recreate()
                            }
                        }) {
                            Text("Save")
                        }
                    }
                }
            }
        )
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                Column(modifier = Modifier.padding(8.dp, top = 12.dp)) {
                    Text(text = "Your group code: $groupCode", fontSize = 12.sp, color = Color.White)
                    Text(
                        text = groupCode,
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp,
                        color = Color.White,
                        letterSpacing = 8.sp
                    )
                    Text(
                        text = "Share this code with your partner",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextButton(onClick = { showCodeDialog = true }) {
                        Text("Change code", color = Color.White)
                    }
                }
            }
        },
        sheetBackgroundColor = Color.DarkGray,
        sheetPeekHeight = 32.dp,
        sheetElevation = 16.dp,
    ) {

        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "დავთარი",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .blur(animatedBlur, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                        .padding(top = 16.dp)
                        .clickable { pressed = !pressed }
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp)
                        .weight(0.6f)
                ) {
                    items(
                        items = sortedItems,
                        key = { item -> item?.text ?: "" }
                    ) { item ->
                        Box(modifier = Modifier.animateItemPlacement()) {
                            ShopItemCard(
                                shopItem = item!!,
                                delete = { vm.removeShopItem(item.text!!) },
                                isDone = {
                                    vm.addNewShopItem(
                                        text = item.text!!,
                                        isDone = !item.done!!,
                                        senderToken = myToken
                                    )
                                }
                            )
                        }
                    }
                }

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
                                if (shoppingTextFieldState.text.isNotEmpty()) {
                                    vm.addNewShopItem(
                                        text = shoppingTextFieldState.text,
                                        isDone = false,
                                        senderToken = myToken
                                    )
                                    shoppingTextFieldState = TextFieldValue("")
                                }
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp)
                            .height(64.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_double_arrow_24),
                        contentDescription = "double arrow",
                        colorFilter = if (shoppingTextFieldState.text.isNotEmpty())
                            ColorFilter.tint(color = MaterialTheme.colors.primary)
                        else
                            ColorFilter.tint(color = Color.LightGray),
                        modifier = Modifier
                            .size(64.dp)
                            .fillMaxWidth(0.2f)
                            .clickable {
                                if (shoppingTextFieldState.text.isNotEmpty()) {
                                    vm.addNewShopItem(
                                        text = shoppingTextFieldState.text,
                                        isDone = false,
                                        senderToken = myToken
                                    )
                                    shoppingTextFieldState = TextFieldValue("")
                                }
                            }
                            .align(Alignment.CenterVertically)
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}