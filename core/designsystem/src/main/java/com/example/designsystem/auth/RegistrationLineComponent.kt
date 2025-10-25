package com.example.designsystem.auth

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.designsystem.MeatViceVectorComponents
import com.example.designsystem.theme.BlueNeon

@Composable
fun RegistrationLineComponent(iconRegScreen: Painter, stage: Int) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
        Image(painter = painterResource(MeatViceVectorComponents.MeatLine), contentDescription = null)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = iconRegScreen,
                contentDescription = null,
                modifier = Modifier.padding(bottom = 10.dp).height(40.dp).width(60.dp),
                tint = MaterialTheme.colorScheme.onTertiary
                )
            Row {
                Box(modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp)
                    .size(15.dp)
                    .clip(CircleShape)
                    .background(if (stage == 1) BlueNeon else Color.Gray)
                    .padding(5.dp)
                )
                Box(modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp)
                    .size(15.dp)
                    .clip(CircleShape)
                    .background(if (stage == 2) BlueNeon else Color.Gray)
                    .padding(5.dp)
                )
                Box(modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp)
                    .size(15.dp)
                    .clip(CircleShape)
                    .background(if (stage == 3) BlueNeon else Color.Gray)
                    .padding(5.dp)
                )
            }
        }
        Image(painter = painterResource(MeatViceVectorComponents.MeatLine), contentDescription = null)

    }
}