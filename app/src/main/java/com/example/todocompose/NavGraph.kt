package com.example.todocompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todocompose.presentation.RTShopListScreen
import com.example.todocompose.ui.auth.AuthScreen
import com.example.todocompose.ui.register.RegisterScreen
import com.example.todocompose.util.Screen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {

    NavHost(navController = navController,
        startDestination = Screen.Home.route) {

        composable(route = Screen.Home.route){
            RTShopListScreen(navController = navController)
        }

        composable(route = Screen.Auth.route){
            AuthScreen(navController = navController)
        }

        composable(route = Screen.Register.route){
            RegisterScreen(navController = navController)
        }





    }


}