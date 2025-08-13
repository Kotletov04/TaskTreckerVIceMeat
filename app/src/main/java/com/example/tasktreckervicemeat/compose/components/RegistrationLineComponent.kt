package com.example.tasktreckervicemeat.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tasktreckervicemeat.R
import com.example.tasktreckervicemeat.navigation.Designations.REGISTRATION_EMAIL
import com.example.tasktreckervicemeat.navigation.Designations.REGISTRATION_PASSWORD
import com.example.tasktreckervicemeat.navigation.Designations.REGISTRATION_USERNAME
import com.example.tasktreckervicemeat.ui.theme.BlueNeon

@Composable
fun RegistrationLineComponent(iconRegScreen: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
        Image(painter = painterResource(R.drawable.meatline), contentDescription = null)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (iconRegScreen) {
                REGISTRATION_USERNAME -> {
                    Image(
                        painter = painterResource(R.drawable.person_icon),
                        contentDescription = null,
                        modifier = Modifier.padding(bottom = 10.dp).height(40.dp).width(60.dp),)
                }
                REGISTRATION_PASSWORD -> {
                    Image(
                        painter = painterResource(R.drawable.outline_password_24),
                        contentDescription = null,
                        modifier = Modifier.padding(bottom = 10.dp).height(40.dp).width(60.dp),)
                }
                REGISTRATION_EMAIL -> {
                    Image(
                        painter = painterResource(R.drawable.regmailicon),
                        contentDescription = null,
                        modifier = Modifier.padding(bottom = 10.dp).height(40.dp).width(60.dp),)
                }
            }
            Row {
                Box(modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp)
                    .size(15.dp)
                    .clip(CircleShape)
                    .background(if (iconRegScreen == REGISTRATION_EMAIL) BlueNeon else Color.Gray)
                    .padding(5.dp)
                )
                Box(modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp)
                    .size(15.dp)
                    .clip(CircleShape)
                    .background(if (iconRegScreen == REGISTRATION_USERNAME) BlueNeon else Color.Gray)
                    .padding(5.dp)
                )
                Box(modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp)
                    .size(15.dp)
                    .clip(CircleShape)
                    .background(if (iconRegScreen == REGISTRATION_PASSWORD) BlueNeon else Color.Gray)
                    .padding(5.dp)
                )
            }
        }
        Image(painter = painterResource(R.drawable.meatline), contentDescription = null)

    }
}