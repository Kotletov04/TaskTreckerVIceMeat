package com.example.tasktreckervicemeat.compose.components.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktreckervicemeat.R
import com.example.tasktreckervicemeat.ui.theme.Black22
import com.example.tasktreckervicemeat.ui.theme.Montserrat
import com.example.tasktreckervicemeat.ui.theme.PinkDD
import com.example.tasktreckervicemeat.ui.theme.Yellow26

@Composable
@Preview
private fun TopBarTest() {
    TopBarMediaContainer(onClickBack = {})
}

@Composable
fun TopBarMediaContainer(onClickBack: () -> Unit, height: Dp = 65.dp) {
    Column {
        Row(modifier = Modifier.fillMaxWidth().height(height).background(Black22).padding(20.dp), verticalAlignment = Alignment.Bottom) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.clickable(onClick = onClickBack)) {
                Image(
                    painter = painterResource(R.drawable.visibleicon),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp).rotate(90f))
            }
            Text(
                modifier = Modifier.padding(start = 15.dp),
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Light,
                text = "Галерея"
            )
        }
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(brush = Brush.horizontalGradient(colors = listOf(PinkDD, Yellow26))))
    }

}