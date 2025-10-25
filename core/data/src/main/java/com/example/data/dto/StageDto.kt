package com.example.data.dto

import com.google.firebase.Timestamp

data class StageDto(
    val title: String = "",
    val taskIsCompleted: Boolean = false,
    val date: Timestamp = Timestamp.now(),
    val subtasks: List<String> = emptyList<String>(),
    val users: List<String> = emptyList<String>()
)
