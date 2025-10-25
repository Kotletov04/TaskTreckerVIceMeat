package com.example.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.designsystem.animation.NeonRabbitLoadingRunAnimationComponent
import com.example.designsystem.theme.Black22


@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Black22), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier.size(200.dp)) {
            NeonRabbitLoadingRunAnimationComponent()
        }
    }
}