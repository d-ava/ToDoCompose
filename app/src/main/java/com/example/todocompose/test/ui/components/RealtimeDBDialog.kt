package com.example.todocompose.test.ui.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.*

@Composable
fun RealtimeDBDialog(
    userName: String,
    userId: String,
    showDialog: MutableState<Boolean>,
    onUpdateClick: (id: String, name: String) -> Unit,
) {
    var inputText by remember { mutableStateOf(userName) }
    AlertDialog(
        onDismissRequest = {
            showDialog.value = false
        },
        text = {
            TextField(
                value = inputText,
                onValueChange = { inputText = it }
            )
        },
        confirmButton = {
            Button(onClick = {
                onUpdateClick(userId, inputText)
                showDialog.value = false
            }) {
                Text(text = "Update")
            }
        }
    )
}