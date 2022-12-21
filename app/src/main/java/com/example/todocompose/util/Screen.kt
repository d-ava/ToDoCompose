package com.example.todocompose.util

sealed class Screen(val route:String){
    object Home:Screen(route = "home_screen")
    object Auth:Screen(route = "auth_screen")
    object Register:Screen(route = "register_screen")


}
