package com.example.tasktreckervicemeat.compose.screens.hubs

import com.example.domain.model.ImageModel

data class HubsState(
    val isLoading: Boolean? = false,
    val error: String? = "",
    val testData: List<ImageModel>? = emptyList<ImageModel>(),
    val userIsRegister: Boolean? = false
)