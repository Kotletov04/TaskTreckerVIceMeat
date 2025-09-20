package com.example.domain.model

data class UserModel(
    val id: String = "",
    val username: String,
    val email: String,
    val hubs: List<String>? = emptyList<String>(),
    val image: String? = "",
    val role: String,
    val profileCustomField: List<ProfileCustomFieldsModel>? = emptyList<ProfileCustomFieldsModel>()
)
