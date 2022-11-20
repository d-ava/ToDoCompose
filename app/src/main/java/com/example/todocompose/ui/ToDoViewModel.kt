package com.example.todocompose.ui

import androidx.lifecycle.ViewModel
import com.example.todocompose.data.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    val todos = repository.getAllToDos()



}