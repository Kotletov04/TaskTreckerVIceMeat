package com.example.data.dto

import com.example.domain.model.UserModel

data class TaskDto(
    val title: String = "",
    val description: String = "",
    val isComplete: Boolean = false,
    val executors: List<String> = emptyList<String>()
)
