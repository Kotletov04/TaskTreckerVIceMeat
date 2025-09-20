package com.example.data.dto

import com.example.domain.model.ChatModel
import com.example.domain.model.HubModel
import com.example.domain.model.NotificationModel
import com.example.domain.model.StageModel
import com.example.domain.model.UserModel

data class HubDto(
    val title: String = "",
    val category: String = "",
    val description: String = "",
    val hubImage: String = "",
    val users: List<String> = emptyList<String>(),
    val adminName: String = "",
    val adminId: String = "",
    val isOpen: Boolean = false,
    val stages: List<String> = emptyList<String>(),
    val chats: List<String> = emptyList<String>(),
    val notification: List<String> = emptyList<String>()
) {
    fun toHubModel(
        id: String,
    ): HubModel {
        return HubModel(
            id = id,
            title = title,
            category = category,
            description = description,
            hubImage = hubImage,
            users = users,
            adminName = adminName,
            adminId = adminId,
            isOpen = isOpen,
            stages = stages,
            chats = chats,
            notification = notification,
        )
    }
}
