package com.example.todocompose.ui

import android.annotation.SuppressLint
import android.util.Log.d
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todocompose.R
import com.example.todocompose.data.ToDo
import com.example.todocompose.ui.toDoItem.ToDOItem

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
            .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ToDo List",
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .blur(animatedBlur, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                .clickable { checked = !checked }
        )

//        EnterTextField(txtFieldState = txtFieldState, keyboardController = keyboardController)

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(value = txtFieldState,
                onValueChange = { txtFieldState = it },

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
                    .clickable {
                        textToShow = txtFieldState
                        vm.addToDoItem(ToDo(text = txtFieldState.text, isDone = true))
                        txtFieldState = TextFieldValue("")
                    }
                    .align(Alignment.CenterVertically)
                    .padding(4.dp)
            )
        }



//        Button(
//            onClick = {
//                textToShow = txtFieldState
//                vm.addToDoItem(ToDo(text = txtFieldState.text, isDone = true))
////                vm.addToRealtimeDatabase(ToDo(text=txtFieldState.text))
////                d("---", "list - ${vm.strings.value}")
////                list.add(textToShow.text)
//                txtFieldState = TextFieldValue("")
//
//
//            },
//        ) {
//            Text(text = "button with very long name")
//
//        }
        /////

//        Button(onClick = { vm.readFromRealtimeDatabase() }) {
//            Text(text = "read from db")
//
//        }

//        Text(
//            text = textToShow.text,
//            textAlign = TextAlign.Start,
//            color = Color.Blue,
//            fontWeight = FontWeight.ExtraBold,
//            fontSize = 24.sp
//        )

        LazyVerticalGrid(cells = GridCells.Fixed(1), content = {
            items(vm.todoList.value.size) { i ->
                Column() {

                    ToDOItem(item = vm.todoList.value[i], vm)

                }

            }

        }

        )


    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EnterTextField(txtFieldState:TextFieldValue, keyboardController:SoftwareKeyboardController?){
    var txt = txtFieldState
    Row(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(value = txtFieldState,
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