package com.example.tasktreckervicemeat.compose.screens.profile

import com.example.domain.model.ImageModel
import com.example.domain.model.ProfileCustomFieldsModel
import com.example.domain.model.UserModel

data class ProfileState(
    val error: String? = "",
    val isLoading: Boolean? = false,
    val data: UserModel? = null,
    val testData: List<ImageModel>? = emptyList<ImageModel>(),
    val editMode: Boolean? = false,
    val profileInfo: List<ProfileCustomFieldsModel>? = null
)