package com.example.tasktreckervicemeat.compose.components.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktreckervicemeat.R
import com.example.tasktreckervicemeat.compose.components.images.UserIconComponent
import com.example.tasktreckervicemeat.ui.theme.Black22
import com.example.tasktreckervicemeat.ui.theme.Montserrat
import com.example.tasktreckervicemeat.ui.theme.PinkDD
import com.example.tasktreckervicemeat.ui.theme.Yellow26
import com.google.firebase.annotations.concurrent.Background

@Composable
fun TopBar(
    height: Dp = 0.dp,
    backgroundColor: Color = Black22,
    lineBackground: Brush = Brush.horizontalGradient(colors = listOf(PinkDD, Yellow26)),
    content: @Composable (() -> Unit),
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(color = backgroundColor),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            content.invoke()
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(brush = lineBackground)
        )
    }
}