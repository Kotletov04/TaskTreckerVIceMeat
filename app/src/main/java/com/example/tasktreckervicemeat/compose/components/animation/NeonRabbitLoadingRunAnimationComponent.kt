package com.example.tasktreckervicemeat.compose.components.animation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.tasktreckervicemeat.R


@Composable
fun NeonRabbitLoadingRunAnimationComponent() {

    val preloaderLottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_animation_run))

    val preloaderProgress by animateLottieCompositionAsState(
        composition = preloaderLottieComposition,
        isPlaying = true,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = {preloaderProgress},
        modifier = Modifier
    )
}