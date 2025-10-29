package com.example.hubs.state

import android.graphics.Bitmap
import com.example.domain.model.UserModel

data class UserState(
    val isLoading: Boolean? = false,
    val user: UserModel? = null,
    val avatar: Bitmap? = null,
    val error: String? = ""
)
