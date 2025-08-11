package com.example.domain.repository

import com.example.domain.model.UserModel

interface AuthRepository {
    suspend fun login(email: String, password: String): Boolean
    suspend fun checkAuth(): Boolean
    suspend fun logout()
    suspend fun deleteAccount(email: String): Boolean
    suspend fun register(email: String, password: String): Boolean
    suspend fun resetPassword(): Boolean
    suspend fun verifyCheckEmail(): Boolean
}