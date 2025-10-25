package com.example.data.dto

import com.example.domain.model.ProfileCustomFieldsModel
import com.example.domain.model.UserModel

data class UserDto(
    val email: String = "",
    val hubs: List<String> = emptyList<String>(),
    val image: String = "",
    val role: String = "",
    val username: String = "",
) {
    fun toUserModel(
        userId: String
    ): UserModel {
        return UserModel(
            id = userId,
            email = email,
            hubs = hubs,
            role = role,
            username = username,
        )
    }
}

