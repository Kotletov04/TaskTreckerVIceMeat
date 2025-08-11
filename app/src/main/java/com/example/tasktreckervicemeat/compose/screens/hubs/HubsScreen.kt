package com.example.tasktreckervicemeat.compose.screens.hubs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.HubModel
import com.example.domain.model.StageModel
import com.example.tasktreckervicemeat.compose.components.hubs.HubListItemComponent
import com.example.tasktreckervicemeat.ui.theme.Black22

@Preview
@Composable
private fun HubsScreenTest() {
    HubsScreen()
}


@Composable
fun HubsScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize().background(Black22), horizontalAlignment = Alignment.CenterHorizontally) {

        items(3) {
            HubListItemComponent(
                hubImageModel = "",
                title = "Name",
                category = "Category",
                amountUsers = 3,
                adminName = "Kotletov",
                adminImageModel = ""
            )
            Spacer(modifier = Modifier.height(50.dp))
        }

    }
}