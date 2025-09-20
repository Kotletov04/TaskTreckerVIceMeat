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
        profileCustomFields: List<ProfileCustomFieldsModel> = emptyList<ProfileCustomFieldsModel>(),
        userId: String
    ): UserModel {
        return UserModel(
            id = userId,
            email = email,
            hubs = hubs,
            image = image,
            role = role,
            username = username,
            profileCustomField = profileCustomFields
        )
    }
}

