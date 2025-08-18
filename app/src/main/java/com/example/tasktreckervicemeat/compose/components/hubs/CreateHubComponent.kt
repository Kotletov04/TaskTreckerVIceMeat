package com.example.tasktreckervicemeat.compose.components.hubs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktreckervicemeat.R
import com.example.tasktreckervicemeat.compose.components.CustomButton
import com.example.tasktreckervicemeat.compose.components.images.UserIconComponent
import com.example.tasktreckervicemeat.ui.theme.Black22
import com.example.tasktreckervicemeat.ui.theme.BlueNeon
import com.example.tasktreckervicemeat.ui.theme.Montserrat
import com.example.tasktreckervicemeat.ui.theme.OrangeFF


@Composable
@Preview
private fun TestCreateHub() {
    val title = remember { mutableStateOf("") }
    CreateHubComponent(title = title)
}


@Composable
fun CreateHubComponent(title: MutableState<String>) {
    Box(modifier = Modifier.width(300.dp).height(200.dp).background(Black22).padding(20.dp)) {
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Start) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                UserIconComponent(
                    size = 90.dp,
                    model = "",
                    borderWidth = 2.dp
                )
                CustomButton(
                    onClick = {},
                    text = "Завершить",
                    width = 70.dp,
                    fontSize = 8,
                    padding = 0.dp,
                    innerPadding = 10.dp
                )
            }

            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                BasicTextField(
                    value = title.value,
                    modifier = Modifier.padding(bottom = 3.dp),
                    onValueChange = { title.value = it},
                    decorationBox = {
                        if (title.value.isEmpty()) {
                            Text(
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                text = "Название хаба",
                                color = Color.White
                            )
                        }
                    }
                )
                Box(modifier = Modifier.width(100.dp).height(1.dp).background(Color.Gray))
                Text(
                    modifier = Modifier.padding(top = 3.dp),
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = Color.White,
                    text = "Участники: 4"
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    UserIconComponent(
                        size = 20.dp,
                        model = "",
                        borderWidth = 0.dp
                    )
                    Icon(
                        modifier = Modifier.padding(start = 5.dp).height(10.dp),
                        painter = painterResource(R.drawable.invite_users),
                        contentDescription = null,
                        tint = Color.White
                        )
                }

            }
        }

    }
}