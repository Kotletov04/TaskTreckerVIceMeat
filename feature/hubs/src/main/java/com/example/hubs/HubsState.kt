package com.example.hubs

import com.example.domain.model.HubModel
import com.example.domain.model.ImageModel
import com.example.domain.model.UserModel

data class HubsState(
    val hubIsLoading: Boolean? = false,
    val currentUserIsLoading: Boolean? = false,
    val error: String? = "",
    val testData: List<ImageModel>? = emptyList<ImageModel>(),
    val hubs: List<HubModel>? = null,
    val userIsRegister: Boolean? = false,
    val currentUser: UserModel? = null
)