package com.example.todocompose.ui.toDoItem

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.todocompose.R
import com.example.todocompose.TodoListEvent
import com.example.todocompose.data.ToDo

@Composable
fun TodoListItem(
    todo:ToDo,
    onEvent: (TodoListEvent) -> Unit,
    modifier: Modifier = Modifier
){

    var clicked by remember{ mutableStateOf(false) }
    val animatedBlur by animateDpAsState(targetValue = if (clicked) 4.dp else 0.dp)

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(48.dp)
        ,
        border = BorderStroke(1.dp, Color.Red)

    ) {

        Box(modifier = Modifier.padding(8.dp), contentAlignment = Alignment.CenterStart) {
            Text(
                text = todo.text,
                color = if (!clicked) MaterialTheme.colors.primary else Color.LightGray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .blur(radius = animatedBlur, edgeTreatment = BlurredEdgeTreatment.Unbounded),
            )

            Image(
                painter = painterResource(id = R.drawable.ic_baseline_delete_24),
                contentDescription = "delete icon",
                colorFilter = ColorFilter.tint(color = Color.Red),
                modifier = Modifier
                    .padding(end = 4.dp)
                    .clickable {
                        onEvent(TodoListEvent.OnDeleteTodo(todo))
                    }
                    .align(alignment = Alignment.CenterEnd)


            )
            Checkbox(checked = todo.isDone, onCheckedChange = {
                onEvent(TodoListEvent.OnDoneChange(todo, it))
            })

//            Image(
//                painter = painterResource(id = R.drawable.ic_baseline_done_24),
//                contentDescription = "done icon",
//                colorFilter = ColorFilter.tint(color = Color.Red),
//                modifier = Modifier
//                    .padding(end = 64.dp)
//
//                    .align(alignment = Alignment.CenterEnd)
//                    .clickable {
//                        clicked = !clicked
//                        todo.isDone=clicked
//                    }
//
//
//            )

        }

    }
}