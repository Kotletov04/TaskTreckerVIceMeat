package com.example.data.dto

import com.example.domain.model.ProfileCustomFieldsModel

data class ProfileCustomFieldsDto(
    val userId: String = "",
    val title: String = "",
    val fields: Map<String, String> = emptyMap<String, String>(),
    val type: String = ""
) {
    fun toProfileCustomFieldsModel(): ProfileCustomFieldsModel {
        return ProfileCustomFieldsModel(
            userId = userId,
            title = title,
            fields = fields,
            type = type
        )
    }
}
