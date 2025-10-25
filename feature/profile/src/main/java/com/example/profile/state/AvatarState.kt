package com.example.profile.state

import android.graphics.Bitmap

data class AvatarState(
    val isLoading: Boolean? = false,
    val error: String? = "",
    val avatar: Bitmap? = null
)