package com.example.domain.model

import java.sql.Timestamp

data class StageModel(
    val id: String,
    val title: String,
    val length: Int,
    val taskIsCompleted: Boolean,
    val date: Timestamp,
    val subtasks: List<TaskModel>? = emptyList(),
    val users: List<UserModel>
)
