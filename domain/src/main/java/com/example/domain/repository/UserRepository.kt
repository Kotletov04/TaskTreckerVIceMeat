package com.example.domain.repository

import com.example.domain.model.UserModel

interface UserRepository {
    suspend fun getAllUsers(): List<UserModel>
    suspend fun getCurrentUser(): UserModel?
    suspend fun getUserById(id: String): UserModel
    suspend fun createUser(user: UserModel): Boolean
}