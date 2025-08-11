package com.example.domain.model

data class ChatModel(
    val id: Int,
    val users: List<UserModel>,
    val title: String,
    val image: String
)
