package com.example.todocompose.ui.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todocompose.R
import com.example.todocompose.presentation.RTShopListViewModel
import com.example.todocompose.ui.ItemsProgressIndicator
import com.example.todocompose.util.Screen

@Composable
fun RegisterScreen(navController: NavController, vm: RTShopListViewModel = hiltViewModel()){

    var emailTextFieldState by remember { mutableStateOf(TextFieldValue("")) }
    var passwordTextFieldState by remember { mutableStateOf(TextFieldValue("")) }
    var repeatPasswordTextFieldState by remember { mutableStateOf(TextFieldValue("")) }

    val loading2 = vm.itemsLoading.value


    ItemsProgressIndicator(show = loading2)

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {



        Text(text = "BACK", fontWeight = FontWeight.Light, modifier = Modifier
            .padding(top = 16.dp)
            .clickable {
                navController.popBackStack()
            })

        Text(text = "Register user - NOT READY", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp))

        OutlinedTextField(
            value = emailTextFieldState,
            onValueChange = { emailTextFieldState = it },
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.primary,
                cursorColor = MaterialTheme.colors.primary,
                leadingIconColor = MaterialTheme.colors.onPrimary,
                focusedLabelColor = MaterialTheme.colors.primary,
                unfocusedLabelColor = MaterialTheme.colors.secondary,
                disabledTextColor = MaterialTheme.colors.onPrimary
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_alternate_email_24),
                    contentDescription = "icon"
                )
            },
            singleLine = true,
            label = { Text(text = "enter email")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp)

                .height(64.dp)
        )

        OutlinedTextField(
            value = passwordTextFieldState,
            onValueChange = { passwordTextFieldState = it },
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.primary,
                cursorColor = MaterialTheme.colors.primary,
                leadingIconColor = MaterialTheme.colors.onPrimary,
                focusedLabelColor = MaterialTheme.colors.primary,
                unfocusedLabelColor = MaterialTheme.colors.secondary,
                disabledTextColor = MaterialTheme.colors.onPrimary

            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_lock_open_24),
                    contentDescription = "icon"
                )
            },
            label = { Text(text = "enter password")},
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)

                .height(64.dp)
        )

        OutlinedTextField(
            value = repeatPasswordTextFieldState,
            onValueChange = { repeatPasswordTextFieldState = it },
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.primary,
                cursorColor = MaterialTheme.colors.primary,
                leadingIconColor = MaterialTheme.colors.onPrimary,
                focusedLabelColor = MaterialTheme.colors.primary,
                unfocusedLabelColor = MaterialTheme.colors.secondary,
                disabledTextColor = MaterialTheme.colors.onPrimary
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_lock_open_24),
                    contentDescription = "icon"
                )
            },
            singleLine = true,
            label = { Text(text = "repeat password")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)

                .height(64.dp)
        )




        Button(
            onClick = {
                      vm.testLoading()


            }, modifier = Modifier
                .padding(top = 16.dp)

                .fillMaxWidth()
        ) { Text(text = "Register") }






    }
}