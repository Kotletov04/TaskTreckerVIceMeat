package com.example.tasktreckervicemeat.compose.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(onClick: () -> Unit, text: String, width: Dp = 120.dp, fontSize: Int = 14, padding: Dp = 5.dp) {
    Box(
        modifier = Modifier
            .padding(end = padding, start = padding)
            .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(15.dp))
            .clickable { onClick.invoke() }
            .padding(10.dp)
            .width(width),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = fontSize.sp
        )
    }

}