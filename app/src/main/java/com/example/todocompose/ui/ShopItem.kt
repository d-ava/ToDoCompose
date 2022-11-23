package com.example.todocompose.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.todocompose.R
import com.example.todocompose.shopData.Shop

@Composable
fun ShopItem(shop: Shop, delete:(shop:Shop)-> Unit) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(48.dp),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)

    ) {
        Box(
            modifier = Modifier.padding(8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = shop.text,
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                modifier = Modifier.padding(start = 8.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_baseline_delete_24),
                contentDescription = "delete icon",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable { delete(shop) }
                    .align(Alignment.CenterEnd)
            )


        }


    }


}