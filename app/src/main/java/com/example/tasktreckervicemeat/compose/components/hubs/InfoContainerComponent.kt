package com.example.tasktreckervicemeat.compose.components.hubs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktreckervicemeat.R
import com.example.tasktreckervicemeat.ui.theme.Black22

@Composable
fun InfoContainerComponent(amount: Int) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .width(30.dp)
            .height(20.dp)
            .background(color = Black22)
            .padding(start = 5.dp, end = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.size(10.dp),
            painter = painterResource(R.drawable.person_icon),
            contentDescription = null)
        Text(
            fontSize = 10.sp,
            color = Color.White,
            text = amount.toString()
        )
    }
}