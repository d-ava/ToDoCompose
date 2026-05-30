package com.example.todocompose.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todocompose.R
import com.example.todocompose.domain.model.RTShopItem

@Composable
fun ShopItemCard(
    shopItem: RTShopItem,
    delete: () -> Unit,
    isDone: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
            .fillMaxWidth()
            .alpha(0.8f)
            .clickable { isDone() },
        backgroundColor = if (shopItem.done!!) Color.LightGray else Color(0xFF16B8F3),
        shape = MaterialTheme.shapes.small
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .heightIn(min = 48.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = shopItem.text!!,
                fontWeight = if (shopItem.done) FontWeight.Light else FontWeight.ExtraBold,
                color = if (shopItem.done) Color.DarkGray else Color.White,
                fontSize = if (shopItem.done) 18.sp else 32.sp,
                modifier = Modifier.padding(start = 8.dp, end = 48.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_baseline_close_24),
                contentDescription = "delete icon",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable { delete() }
                    .align(Alignment.CenterEnd)
            )
        }
    }
}