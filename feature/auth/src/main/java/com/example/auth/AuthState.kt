package com.example.auth


data class AuthState(
    val isLoading: Boolean? = false,
    val error: String? = "",
    val permission: Boolean? = null,
    val userIsRegister: Boolean? = false
)
