package com.example.domain.model

data class TaskModel(
    val id: String,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val executors: List<UserModel>
)
