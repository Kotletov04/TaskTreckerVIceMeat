package com.example.domain.model

data class HubModel(
    val id: Int,
    val title: String,
    val description: String,
    val hubImage: String,
    val usersId: List<Int>,
    val isOpen: Boolean,
    val stages: List<StageModel>? = emptyList(),
    val chats: List<ChatModel>,
    val notification: List<NotificationModel>
)
