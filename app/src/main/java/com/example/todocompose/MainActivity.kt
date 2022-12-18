package com.example.todocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.todocompose.presentation.RTShopListScreen
import com.example.todocompose.ui.MainScreen
import com.example.todocompose.ui.ShoppingScreen
import com.example.todocompose.ui.TestScreen
import com.example.todocompose.ui.TodoListScreen
import com.example.todocompose.ui.theme.ToDoComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoComposeTheme {
//whn using theme, grey areas around icons when click appear. need to check later
                RTShopListScreen()
//                TestScreen()
            }
//            MainScreen()
//            TodoListScreen()
//            ShoppingScreen()
        }
    }
}

