package com.example.designsystem.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.R
import com.example.designsystem.ThemePreviews

@Composable
@Preview
@ThemePreviews
private fun TestButton() {
    CustomButton(
        onClick = {},
        text = "Кнопка",
    )
}



@Composable
fun CustomButton(
    onClick: () -> Unit,
    text: String,
    width: Dp = 120.dp,
    height: Dp = 30.dp,
    fontSize: Int = 14,
    padding: Dp = 5.dp,
    innerPadding: Dp = 10.dp,
    shape: Shape = RoundedCornerShape(15.dp),
    leadingIcon: Painter? = null
    )
{
    Row(
        modifier = Modifier
            .padding(end = padding, start = padding)
            .border(width = 1.dp, color = Color.White, shape = shape)
            .clip(shape = shape)
            .clickable(onClick = {onClick.invoke()}, enabled = true)
            .padding(innerPadding)
            .width(width)
            .height(height)
            ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingIcon != null) {
            Icon(
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                painter = painterResource(R.drawable.person_icon),
                tint = Color.White,
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = fontSize.sp
        )
    }

}