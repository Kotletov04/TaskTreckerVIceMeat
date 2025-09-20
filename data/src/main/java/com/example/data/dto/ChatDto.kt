package com.example.data.dto

import com.example.domain.model.ChatModel
import com.example.domain.model.UserModel

data class ChatDto(
    val users: List<String> = emptyList<String>(),
    val title: String = "",
    val image: String = ""
) {
    fun toChatModel(
        id: String,
        users: List<UserModel>
    ): ChatModel {
        return ChatModel(
            id = id,
            users = users,
            title = title,
            image = image
        )
    }
}