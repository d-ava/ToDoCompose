package com.example.todocompose.ui.toDoItem

import android.util.Log.d
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.todocompose.R
import com.example.todocompose.data.ToDo
import com.example.todocompose.ui.MainViewModel

@Composable
fun ToDOItem(item: ToDo, vm:MainViewModel) {

    var clicked by remember{ mutableStateOf(false)}
    val animatedBlur by animateDpAsState(targetValue = if (clicked) 4.dp else 0.dp)

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(48.dp)
            ,
        border = BorderStroke(1.dp, Color.Black)

    ) {

        Box(modifier = Modifier.padding(8.dp), contentAlignment = Alignment.CenterStart) {
            Text(
                text = item.text,
                color = if (!clicked) Color.Black else Color.LightGray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 4.dp)
                    .blur(radius = animatedBlur, edgeTreatment = BlurredEdgeTreatment.Unbounded),
            )
//            Text(text = "another text", modifier = Modifier.padding(start = 8.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_clear_24),
                contentDescription = "close icon",
                modifier = Modifier
                    .padding(end = 4.dp)
                    .align(alignment = Alignment.CenterEnd)
//                    .clickable {
//                        vm.remove(text)
//                        d("---", "clicked to remove") }

            )

            Image(
                painter = painterResource(id = R.drawable.ic_baseline_done_24),
                contentDescription = "done icon",
                modifier = Modifier
                    .padding(end = 32.dp)
                    .align(alignment = Alignment.CenterEnd)
                    .clickable { clicked = !clicked
                    item.markAsDone=clicked
                    }


            )

        }

    }
}