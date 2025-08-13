package com.example.tasktreckervicemeat.compose.components.hubs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasktreckervicemeat.compose.components.CustomButton
import com.example.tasktreckervicemeat.compose.components.images.UserIconComponent
import com.example.tasktreckervicemeat.ui.theme.Black22


@Composable
@Preview
private fun TestCreateHub() {
    CreateHubComponent()
}


@Composable
fun CreateHubComponent() {
    Box(modifier = Modifier.width(300.dp).height(200.dp).background(Black22).padding(20.dp)) {
        Row {
            Column {
                UserIconComponent(size = 90.dp, model = "", borderWidth = 2.dp)
                CustomButton(onClick = {}, text = "Завершить", width = 70.dp, fontSize = 8)
            }

            Column {
                Text(text = "Name")
                Text(text = "Участники: 4")
                UserIconComponent(size = 20.dp, model = "", borderWidth = 0.dp)
                CustomButton(onClick = {}, text = "Пригласить", fontSize = 6, width = 42.dp)
            }
        }

    }
}