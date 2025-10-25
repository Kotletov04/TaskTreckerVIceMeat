package com.example.profile.state

import com.example.domain.model.UserModel

data class UserInfoState(
    val isLoading: Boolean? = false,
    val error: String? = "",
    val userInfo: UserModel? = null
)