package com.example.tasktreckervicemeat.compose.screens.auth

import com.example.domain.model.UserModel

data class AuthState(
    val isLoading: Boolean? = false,
    val error: String? = "",
    val permission: Boolean? = null,
    val userIsRegister: Boolean? = false
)
