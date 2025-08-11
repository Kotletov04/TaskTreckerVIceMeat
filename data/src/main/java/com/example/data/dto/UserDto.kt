package com.example.data.dto

import com.example.domain.model.UserModel

data class UserDto(
    val email: String,
    val hubs: List<Int>,
    val image: String,
    val role: String,
    val uid: String,
    val username: String
) {
    fun toUserModel(): UserModel {
        return UserModel(
            id = uid,
            email = email,
            hubs = hubs,
            image = image,
            role = role,
            username = username
        )
    }
}

