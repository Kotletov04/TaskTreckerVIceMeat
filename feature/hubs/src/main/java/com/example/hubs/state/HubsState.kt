package com.example.hubs.state

import android.graphics.Bitmap
import com.example.domain.model.HubModel

data class HubsState(
    val isLoading: Boolean? = null,
    val hubsList: List<HubModel>? = emptyList<HubModel>(),
    val hubsAvatars: List<Bitmap>? = emptyList<Bitmap>(),
    val adminsAvatars: List<Bitmap>? = emptyList<Bitmap>(),
    val hubIsCreated: Boolean? = false,
    val error: String? = ""
)