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
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(onClick: () -> Unit, text: String) {
    Box(
        modifier = Modifier
            .padding(end = 5.dp, start = 5.dp)
            .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(15.dp))
            .clickable { onClick.invoke() }
            .padding(10.dp)
            .width(120.dp),
    ) { Text(modifier = Modifier.fillMaxWidth(), text = text, color = Color.White, textAlign = TextAlign.Center) }

}