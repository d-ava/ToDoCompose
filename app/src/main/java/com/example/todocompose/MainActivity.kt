package com.example.todocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todocompose.test.ui.screen.RealtimeDBScreen
import com.example.todocompose.ui.auth.AuthScreen
import com.example.todocompose.ui.register.RegisterScreen
import com.example.todocompose.ui.theme.ToDoComposeTheme
import com.example.todocompose.util.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoComposeTheme {
                navController = rememberNavController()

//whn using theme, grey areas around icons when click appear. need to check later
//                RTShopListScreen()
//                AuthScreen()
//            RegisterScreen()
//                RealtimeDBScreen()


                SetupNavGraph(navController = navController)
            }

        }
    }
}

