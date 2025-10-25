package com.example.domain.repository

import com.example.domain.model.TaskModel

interface TasksRepository {
    suspend fun getAllTasks(): List<TaskModel>

}