package com.example.todocompose.presentation

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todocompose.R
import com.example.todocompose.domain.model.RTShopItem
import com.example.todocompose.shopData.Shop

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
            .height(64.dp)
            .alpha(0.8f)
            .clickable { isDone() },
        backgroundColor = if (shopItem.done!!) Color.LightGray else Color(0xFF5EE8FA),
//        elevation = 16.dp,
        shape = MaterialTheme.shapes.small


        ) {
        Box(
            modifier = Modifier.padding(8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = shopItem.text!!,
                fontWeight = if (shopItem.done) FontWeight.Light else FontWeight.Bold,
//                style = if (shopItem.done!!) TextStyle(textDecoration = TextDecoration.LineThrough) else TextStyle(
//                    textDecoration = TextDecoration.None
//                ),
                color = if (shopItem.done) Color.DarkGray else if(shopItem.text=="წყალი")Color.Red else MaterialTheme.colors.primary,
                modifier = Modifier.padding(start = 8.dp)
            )


//            Image(
//                painter = painterResource(id = R.drawable.ic_baseline_done_outline_24),
//                contentDescription = "not done / done icon",
//
//                colorFilter = if (shopItem.done) ColorFilter.tint(MaterialTheme.colors.primary) else ColorFilter.tint(
//                    Color.LightGray
//                ),
//                modifier = Modifier
//                    .padding(end = 48.dp)
//                    .clickable { isDone() }
//                    .align(Alignment.CenterEnd)
//            )


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