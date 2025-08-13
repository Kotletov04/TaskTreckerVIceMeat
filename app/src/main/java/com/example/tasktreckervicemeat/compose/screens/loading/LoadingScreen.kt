package com.example.tasktreckervicemeat.compose.screens.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tasktreckervicemeat.compose.components.animation.NeonRabbitLoadingRunAnimationComponent
import com.example.tasktreckervicemeat.ui.theme.Black22


@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Black22), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier.size(200.dp)) {
            NeonRabbitLoadingRunAnimationComponent()
        }
    }
}