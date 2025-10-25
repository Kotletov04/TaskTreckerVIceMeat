package com.example.profile.state

import com.example.domain.model.ImageModel
import com.example.domain.model.ProfileCustomFieldsModel


data class ProfileState(
    val error: String? = "",
    val isLoading: Boolean? = false,
    val localImages: List<ImageModel>? = emptyList<ImageModel>(),
    val editMode: Boolean? = false,

    val profileInfo: List<ProfileCustomFieldsModel>? = null,
    val userInfoState: UserInfoState? = UserInfoState(),
    val avatarState: AvatarState? = AvatarState()
)