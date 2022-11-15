package com.example.todocompose.ui

import android.annotation.SuppressLint
import android.util.Log.d
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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
            text = "Main Screen",
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .blur(animatedBlur, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                .clickable { checked = !checked }
        )




        OutlinedTextField(value = txtFieldState,
            onValueChange = { txtFieldState = it },

            singleLine = true,
            placeholder = { Text(text = "Enter Text") },
//            label = { Text(text = "label text") },

            keyboardOptions = KeyboardOptions(

                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            )

        )
        Button(
            onClick = {
                textToShow = txtFieldState
                vm.addString(txtFieldState.text)
                d("---", "list - ${vm.strings.value}")
//                list.add(textToShow.text)
                txtFieldState = TextFieldValue("")


            },
        ) {
            Text(text = "button with very long name")

        }

//        Text(
//            text = textToShow.text,
//            textAlign = TextAlign.Start,
//            color = Color.Blue,
//            fontWeight = FontWeight.ExtraBold,
//            fontSize = 24.sp
//        )

        LazyVerticalGrid(cells = GridCells.Fixed(1), content = {
            items(vm.strings.value.size) { i ->
                Column() {

                    ToDOItem(text = vm.strings.value[i], vm)

                }

            }

        }

        )


    }
}