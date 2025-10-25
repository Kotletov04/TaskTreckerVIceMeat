package com.example.designsystem.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.BlueNeon
import com.example.designsystem.theme.Gray31


@Preview
@Composable
private fun TestCustomSwitch() {
    val activate = remember { mutableStateOf(false) }
    CustomSwitch(activate = activate.value, onClick = {activate.value = !activate.value})
}

@Composable
fun CustomSwitch(activate: Boolean, height: Dp = 20.dp, onClick: () -> Unit) {
    val animateOffset = animateDpAsState(
        targetValue = if (!activate) {0.dp} else {height}
    )
    val colorLine = animateColorAsState(
        targetValue = if (activate) {BlueNeon} else {Gray31}
    )
    val colorCircle = animateColorAsState(
        targetValue = if (activate) {Gray31} else {BlueNeon}
    )
    Box(
        modifier = Modifier.width(height * 2),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable {onClick.invoke()}
            .width(height * 2)
            .height(height / 2)
            .background(colorLine.value))
        Box(modifier = Modifier
            .offset(x = animateOffset.value)
            .clip(CircleShape)
            .clickable {onClick.invoke()}
            .size(height)
            .background(colorCircle.value)

        )
    }
}