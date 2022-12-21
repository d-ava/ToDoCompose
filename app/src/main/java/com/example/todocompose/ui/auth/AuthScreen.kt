package com.example.todocompose.ui.auth

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
import com.example.todocompose.presentation.RTShopListViewModel
import com.example.todocompose.util.Screen

@Composable
fun AuthScreen(navController: NavController, vm: RTShopListViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        var emailTextFieldState by remember { mutableStateOf(TextFieldValue("david@gmail.com")) }
        var passwordTextFieldState by remember { mutableStateOf(TextFieldValue("test123")) }


        Text(
            text = "BACK",
            fontWeight = FontWeight.Light,
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable {
                    navController.popBackStack()
                })
        Text(
            text = "Authenticate user",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "user - ${vm.authResult.value}",
            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
        )

        OutlinedTextField(
            value = emailTextFieldState,
            onValueChange = { emailTextFieldState = it },
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.primary,
                cursorColor = MaterialTheme.colors.primary,
                leadingIconColor = MaterialTheme.colors.secondary,
                focusedLabelColor = MaterialTheme.colors.primary,
                unfocusedLabelColor = MaterialTheme.colors.secondary,
                disabledTextColor = MaterialTheme.colors.onPrimary
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = com.example.todocompose.R.drawable.ic_baseline_alternate_email_24),
                    contentDescription = "icon"
                )
            },
            singleLine = true,
            label = { Text(text = "enter email") },
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
                leadingIconColor = MaterialTheme.colors.secondary,
                focusedLabelColor = MaterialTheme.colors.primary,
                unfocusedLabelColor = MaterialTheme.colors.secondary,
                disabledTextColor = MaterialTheme.colors.onPrimary
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = com.example.todocompose.R.drawable.ic_baseline_lock_open_24),
                    contentDescription = "icon"
                )
            },
            singleLine = true,
            label = { Text(text = "enter password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)

                .height(64.dp)
        )
        Button(
            onClick = {
                vm.authUser(emailTextFieldState.text, passwordTextFieldState.text)


            }, modifier = Modifier
                .padding(top = 16.dp)

                .fillMaxWidth()
        ) { Text(text = "Log In") }

        Row(modifier = Modifier.padding(top = 64.dp)) {
            Text(text = "Dont have an account?", fontWeight = FontWeight.Light)
            Text(text = "Register", fontWeight = FontWeight.ExtraBold, modifier = Modifier
                .padding(start = 8.dp)
                .clickable { navController.navigate(Screen.Register.route) })

        }


    }
}