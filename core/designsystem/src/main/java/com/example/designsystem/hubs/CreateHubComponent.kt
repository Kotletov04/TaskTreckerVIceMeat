package com.example.designsystem.hubs

import android.widget.Switch
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.MeatViceIcons
import com.example.designsystem.R
import com.example.designsystem.images.UserIconComponent
import com.example.designsystem.screen.CustomButton
import com.example.designsystem.screen.CustomSwitch
import com.example.designsystem.theme.Gray4B
import com.example.designsystem.theme.Montserrat


/*@Composable
@Preview
private fun TestCreateHub() {
    val title = remember { mutableStateOf("") }
    CreateHubComponent(onTitleValueChange = { title }, titleValue = title.value)
}*/


@Composable
fun CreateHubComponent(
    onClickComplete: () -> Unit,
    onTitleValueChange: (String) -> Unit,
    titleValue: String,
    onChangeSwitch: () -> Unit,
    switchIsActive: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(105.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(end = 5.dp)
                .border(width = 1.dp, color = Gray4B, shape = RoundedCornerShape(10.dp))
                .clip(shape = RoundedCornerShape(10.dp))
                .size(105.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(15.dp),
            contentAlignment = Alignment.Center
        ) {
            UserIconComponent(
                size = 90.dp,
                borderWidth = 0.dp,
                bitmap = ImageBitmap.imageResource(R.drawable.test)
            )
        }
        Box(
            modifier = Modifier
                .border(width = 1.dp, color = Gray4B, shape = RoundedCornerShape(10.dp))
                .clip(shape = RoundedCornerShape(10.dp))
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
                .padding(10.dp),
        ) {
            Icon(
                painter = painterResource(MeatViceIcons.Close),
                tint = Gray4B,
                modifier = Modifier.align(Alignment.TopEnd),
                contentDescription = null
            )
            Column(modifier = Modifier.padding(5.dp)) {
                BasicTextField(
                    singleLine = true,
                    value = titleValue,
                    textStyle = TextStyle(
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        fontFamily = Montserrat,
                        color = MaterialTheme.colorScheme.onTertiary
                    ),
                    onValueChange = onTitleValueChange,
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.onTertiary),
                    decorationBox = { innerTextField ->
                        Box {
                            if (titleValue.isBlank()) {
                                Text(
                                    lineHeight = 16.sp,
                                    text = "Название",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    fontFamily = Montserrat,
                                    color = MaterialTheme.colorScheme.onTertiary
                                )
                            }
                            innerTextField()

                        }


                    }
                )
                Box(modifier = Modifier
                    .padding(top = 2.dp)
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Gray4B))
                Text(
                    text = "Участники: 0",
                    fontWeight = FontWeight.ExtraLight,
                    fontSize = 12.sp,
                    fontFamily = Montserrat,
                    color = MaterialTheme.colorScheme.onTertiary
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CustomSwitch(
                            height = 15.dp,
                            activate = switchIsActive,
                            onClick = onChangeSwitch
                        )
                        Text(
                            modifier = Modifier.padding(start = 5.dp),
                            text = if (switchIsActive) "Публичный" else "Приватный",
                            fontWeight = FontWeight.ExtraLight,
                            fontSize = 10.sp,
                            fontFamily = Montserrat,
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    }
                    CustomButton(
                        onClick = onClickComplete,
                        text = "Завершить",
                        fontSize = 8,
                        width = 70.dp,
                        height = 25.dp,
                        innerPadding = 0.dp,
                        shape = RoundedCornerShape(5.dp)
                    )
                }


            }

        }
    }


}