package com.example.designsystem.hubs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.MeatViceIcons
import com.example.designsystem.R
import com.example.designsystem.ThemePreviews
import com.example.designsystem.theme.Black22
import com.example.designsystem.theme.TaskTreckerVIceMeatTheme


@Composable
@ThemePreviews
@Preview
private fun TestHubsTopBar() {
    TaskTreckerVIceMeatTheme(
        dynamicColor = false
    ) {
        HubsTopBarComponent(onClickCreate = {})
    }

}


@Composable
fun HubsTopBarComponent(onClickCreate: () -> Unit) {
    Box(
        modifier = Modifier
            .height(35.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(MeatViceIcons.Search),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onTertiary
            )
            Icon(
                modifier = Modifier.clickable { onClickCreate.invoke() },
                painter = painterResource(MeatViceIcons.AddTask),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onTertiary
            )
            Icon(
                painter = painterResource(MeatViceIcons.Settings),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onTertiary
            )
        }
    }


}