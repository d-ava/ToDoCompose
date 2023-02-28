package com.example.todocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todocompose.presentation.RTShopListViewModel
import com.example.todocompose.ui.theme.ToDoComposeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    private lateinit var navController: NavHostController






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoComposeTheme {
                val vm: RTShopListViewModel = hiltViewModel()
                navController = rememberNavController()

//whn using theme, grey areas around icons when click appear. need to check later




                    SetupNavGraph(navController = navController)



            }

        }
    }
}

