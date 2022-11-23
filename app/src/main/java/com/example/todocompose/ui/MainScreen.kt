package com.example.todocompose.ui

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todocompose.R
import com.example.todocompose.data.ToDo
import com.example.todocompose.ui.toDoItem.TodoItem

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun MainScreen() {
    val vm: MainViewModel = viewModel()
    val keyboardController = LocalSoftwareKeyboardController.current

    var checked by remember { mutableStateOf(true) }
    val animatedBlur by animateDpAsState(targetValue = if (checked) 10.dp else 0.dp)

    var txtFieldState by remember { mutableStateOf(TextFieldValue("")) }
    var textToShow by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ToDo List",
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .blur(animatedBlur, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                .clickable { checked = !checked }
        )

        LazyVerticalGrid(cells = GridCells.Fixed(1), modifier = Modifier.weight(1f), content = {
            items(vm.todoList.value.size) { i ->
                Column() {
                    TodoItem(item = vm.todoList.value[i], vm)

                }
            }
        }
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(value = txtFieldState,

                onValueChange = { txtFieldState = it },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.primary,
                    cursorColor = MaterialTheme.colors.primary,
                    leadingIconColor = MaterialTheme.colors.onPrimary,
                    focusedLabelColor = MaterialTheme.colors.onPrimary,


                    ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_shopping_cart_24),
                        contentDescription = "icon"
                    )


                },

                singleLine = true,
                placeholder = { Text(text = "Enter Text") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        textToShow = txtFieldState
                        vm.addToDoItem(ToDo(text = txtFieldState.text, isDone = true))
                        txtFieldState = TextFieldValue("")
//                        keyboardController?.hide()
                    }
                ), modifier = Modifier
                    .fillMaxWidth(fraction = 0.8f)
                    .padding(4.dp)
                    .height(64.dp))

            Image(painter = painterResource(id = R.drawable.ic_baseline_double_arrow_24),
                contentDescription = "double arrow",
                colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary),
                modifier = Modifier

                    .size(64.dp)
                    .clickable {
                        textToShow = txtFieldState
                        vm.addToDoItem(ToDo(text = txtFieldState.text, isDone = true))
                        txtFieldState = TextFieldValue("")
                    }
                    .align(Alignment.CenterVertically)
                    .padding(4.dp)
            )
        }


    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EnterTextField(txtFieldState: TextFieldValue, keyboardController: SoftwareKeyboardController?) {
    var txt = txtFieldState
    Row(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(value = txt,
            onValueChange = { txt = it },

            singleLine = true,
            placeholder = { Text(text = "Enter Text") },


            keyboardOptions = KeyboardOptions(

                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            ), modifier = Modifier
                .fillMaxWidth(fraction = 0.8f)
                .padding(4.dp)
                .height(64.dp))

        Image(painter = painterResource(id = R.drawable.ic_baseline_double_arrow_24),
            contentDescription = "double arrow",
            modifier = Modifier

                .size(64.dp)
                .clickable { }
                .align(Alignment.CenterVertically)
                .padding(4.dp)
        )
    }

}