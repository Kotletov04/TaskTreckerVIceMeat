package com.example.tasktreckervicemeat.compose.components.hubs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasktreckervicemeat.R
import com.example.tasktreckervicemeat.ui.theme.Black22

@Composable
@Preview
private fun TestHubsTopBar() {
    HubsTopBarComponent()
}



@Composable
fun HubsTopBarComponent() {

    Box(
        modifier = Modifier.height(35.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(20.dp))
        .background(Black22)
        .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(modifier = Modifier.fillMaxWidth().height(15.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Icon(
                painter = painterResource(R.drawable.search_icon),
                contentDescription = null,
                tint = Color.White
            )
            Icon(
                painter = painterResource(R.drawable.taskaddicon),
                contentDescription = null,
                tint = Color.White
            )
            Icon(
                painter = painterResource(R.drawable.settings_icon),
                contentDescription = null,
                tint = Color.White
            )
        }
    }

}