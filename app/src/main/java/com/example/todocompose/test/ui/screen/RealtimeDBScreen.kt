package com.example.todocompose.test.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todocompose.test.data.model.RealtimeDBUser
import com.example.todocompose.test.ui.viewmodel.RealtimeDBViewModel

@Composable
fun RealtimeDBScreen(
    viewModel: RealtimeDBViewModel = hiltViewModel()
) {
    val userState by viewModel.userState.collectAsState()

    var inputName by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize()) {
        TextField(value = inputName, onValueChange = { inputName = it }, label = { Text("Name") })
        Button(onClick = {
            viewModel.createUser(RealtimeDBUser(name = inputName))
        }) { Text("Submit") }
        when {
            userState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            !userState.isLoading && !userState.errorMsg.isNullOrEmpty() -> {
                Text(text = userState.errorMsg!!)
            }
            userState.data.isNullOrEmpty() -> { Text(text = "Empty") }
            !userState.data.isNullOrEmpty() -> {
                LazyColumn {
                    items(userState.data!!) { item ->
                        RealtimeDBListItem(
                            userName = item?.name!!,
                            userId = item.id!!,
                            onUpdateClick = { id, name ->
                                viewModel.updateUser(RealtimeDBUser(id, name))
                            },
                            onDeleteClick = { id, name ->
                                viewModel.deleteUser(RealtimeDBUser(id, name))
                            }
                        )
                    }
                }
            }
        }
    }
}