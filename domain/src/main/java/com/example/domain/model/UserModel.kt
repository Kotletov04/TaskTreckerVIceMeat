package com.example.domain.model

data class UserModel(
    val id: String,
    val username: String,
    val email: String,
    val image: String? = "",
    val role: String,
    val hubs: List<Int>
)
