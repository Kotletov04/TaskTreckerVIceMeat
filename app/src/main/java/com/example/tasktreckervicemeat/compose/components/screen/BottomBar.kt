package com.example.tasktreckervicemeat.compose.components.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.tasktreckervicemeat.R
import com.example.tasktreckervicemeat.ui.theme.Black22
import com.example.tasktreckervicemeat.ui.theme.PinkDD
import com.example.tasktreckervicemeat.ui.theme.Yellow26


@Composable
@Preview
private fun TestBottomBar() {
    BottomBar(onClickCreate = {})
}


@Composable
fun BottomBar(height: Dp = 0.dp, onClickCreate: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().height(height).background(color = Black22)) {
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(brush = Brush.horizontalGradient(colors = listOf(PinkDD, Yellow26))))
        Row(modifier = Modifier.fillMaxWidth().padding(20.dp), horizontalArrangement = Arrangement.Center) {
            Icon(painter = painterResource(R.drawable.iconadd), contentDescription = null, tint = Color.Gray, modifier = Modifier.clickable(onClick = onClickCreate))
        }
    }
}