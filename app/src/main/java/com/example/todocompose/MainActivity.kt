package com.example.todocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todocompose.ui.auth.AuthScreen
import com.example.todocompose.ui.register.RegisterScreen
import com.example.todocompose.ui.theme.ToDoComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoComposeTheme {
                navController = rememberNavController()

//whn using theme, grey areas around icons when click appear. need to check later
//                RTShopListScreen()
//                AuthScreen()
//            RegisterScreen()
                
                SetupNavGraph(navController = navController)
            }

        }
    }
}

