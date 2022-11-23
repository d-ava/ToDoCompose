package com.example.todocompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todocompose.R
import com.example.todocompose.TodoListEvent
import com.example.todocompose.data.ToDo
import com.example.todocompose.ui.toDoItem.TodoListItem
import com.example.todocompose.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun TodoListScreen(
//    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: ToDoViewModel = hiltViewModel()


) {

    var txtFieldState by remember { mutableStateOf(TextFieldValue("")) }
    var textToShow by remember { mutableStateOf(TextFieldValue("")) }

    val scaffoldState = rememberScaffoldState()
    //get to do  list state
    val todos = viewModel.todos.collectAsState(initial = emptyList())

    //collect ui events
    LaunchedEffect(key1 = true) {

        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(

                        message = event.message,
                        actionLabel = event.action
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(TodoListEvent.OnUndoDeleteClick)
                    }

                }
//                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }


        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(MaterialTheme.colors.background)
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Scaffold(scaffoldState = scaffoldState) {
            LazyColumn(modifier = Modifier.fillMaxSize().weight(0.8f)) {
                items(todos.value) { todo ->
                    TodoListItem(
                        todo = todo, onEvent = viewModel::onEvent,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
//                        .clickable { viewModel.onEvent(TodoListEvent.) }
                    )

                }
            }

        }

        ///////////

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
//                        vm.addToDoItem(ToDo(text = txtFieldState.text, isDone = true))
                        txtFieldState = TextFieldValue("")
                    }
                    .align(Alignment.CenterVertically)
                    .padding(4.dp)
            )
        }

    }
}