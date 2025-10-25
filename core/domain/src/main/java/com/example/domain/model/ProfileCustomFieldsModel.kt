package com.example.domain.model

data class ProfileCustomFieldsModel(
    val userId: String,
    val title: String,
    val fields: Map<String, String>,
    val type: String
)
