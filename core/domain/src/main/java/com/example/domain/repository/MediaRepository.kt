package com.example.domain.repository

import com.example.domain.model.ImageModel

interface MediaRepository {
    suspend fun getImages(): List<ImageModel>
    suspend fun getAvatarImage(bucket: String, userId: String): ByteArray
    suspend fun putAvatarImage(bucket: String, userId: String, avatar: ByteArray, mimeType: String)
}