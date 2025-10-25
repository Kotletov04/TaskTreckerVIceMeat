package com.example.designsystem

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.designsystem.theme.BlueNeon
import com.example.designsystem.theme.OrangeFF

@Preview
@Composable
private fun RoundShapeComponentTest() {
    RoundShapeComponent(
        shapeColors = listOf(BlueNeon, Color.White, OrangeFF),
        xOffsetStep = 60.dp,
        yOffsetStep = 0.dp
    )
}



@Composable
fun RoundShapeComponent(
    shapeColors: List<Color>,
    xOffsetStep: Dp,
    yOffsetStep: Dp,
    width: Dp = 393.dp,
    height: Dp = 256.dp,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier) {
        shapeColors.forEachIndexed { index, color ->
            ArcComponent(
                color = color,
                offsetX = xOffsetStep * index,
                offsetY = yOffsetStep * index,
                width = width,
                height = height,
                animatable = index == 0
            )
        }
    }
}

@Composable
private fun ArcComponent(
    color: Color,
    offsetX: Dp,
    offsetY: Dp,
    width: Dp,
    height: Dp,
    animatable: Boolean
) {


    var start by remember { mutableStateOf(false) }

    // Анимируем угол поворота
    val rotation by animateFloatAsState(
        targetValue = if (start) 90f else 0f,
        animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing)
    )

    // Запускаем анимацию при первом появлении
    LaunchedEffect(Unit) {

        start = animatable


    }


    Canvas(
        modifier = Modifier
            .offset(x = offsetX, y = offsetY)
            .width(width)
            .height(height)

    ) {
        val widthPx = width.toPx()
        val heightPx = height.toPx()

        val path = Path().apply {
            moveTo(0f, heightPx)
            arcTo(
                rect = Rect(0f, 0f, widthPx * 2f, heightPx * 4f),
                startAngleDegrees = 180f,
                sweepAngleDegrees = if (animatable) {rotation} else 90f,
                forceMoveTo = false
            )
            lineTo(
                size.width,
                size.height
            )
            close()
        }

        drawPath(
            path = path,
            color = color
        )
    }
}

