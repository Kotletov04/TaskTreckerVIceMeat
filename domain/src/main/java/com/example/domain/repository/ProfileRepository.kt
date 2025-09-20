package com.example.domain.repository

import com.example.domain.model.UserModel

interface ProfileRepository {
    suspend fun getProfileByUserId(id: String): UserModel
    suspend fun editProfileByUserId(id: String): Boolean
    suspend fun deleteProfileByUserId(id: String): Boolean

}