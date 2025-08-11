package com.example.tasktreckervicemeat.compose.components.hubs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktreckervicemeat.compose.components.images.UserIconComponent
import com.example.tasktreckervicemeat.ui.theme.Gray31
import com.example.tasktreckervicemeat.ui.theme.Montserrat


@Composable
fun HubListItemComponent(
    hubImageModel: String,
    title: String,
    category: String,
    amountUsers: Int,
    adminName: String,
    adminImageModel: String
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(color = Gray31)
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            UserIconComponent(size = 60.dp, model = hubImageModel, shape = RoundedCornerShape(10.dp), borderWidth = 2.dp)
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Row {
                    Text(
                        textAlign = TextAlign.Center,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 14.sp,
                        text = title
                    )
                }
                Text(
                    textAlign = TextAlign.Center,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                    fontSize = 10.sp,
                    text = category)
                Spacer(modifier = Modifier.height(2.dp))
                InfoContainerComponent(amount = amountUsers)
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            UserIconComponent(size = 20.dp, model = adminImageModel, borderWidth = 0.dp)
            Spacer(modifier = Modifier.width(3.dp))
            Column {
                Text(
                    textAlign = TextAlign.Center,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                    fontSize = 10.sp,
                    text = "Admin"
                )
                Text(
                    textAlign = TextAlign.Center,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                    fontSize = 10.sp,
                    text = adminName
                )
            }
        }


    }

}