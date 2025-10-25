package com.example.domain.model

data class UserModel(
    val id: String = "",
    val username: String,
    val email: String,
    val hubs: List<String>? = emptyList<String>(),
    val role: String,
)
