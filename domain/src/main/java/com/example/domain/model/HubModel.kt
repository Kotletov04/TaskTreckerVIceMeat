package com.example.domain.model

data class HubModel(
    val id: String? = "",
    val title: String,
    val category: String? = "",
    val description: String? = "",
    val hubImage: String? = "",
    val users: List<String>? = emptyList(),
    val adminName: String? = "",
    val adminId: String? = "",
    val isOpen: Boolean,
    val stages: List<String>? = emptyList(),
    val chats: List<String>? = emptyList(),
    val notification: List<String>? = emptyList(),
)
