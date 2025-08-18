package com.example.domain.repository

import com.example.domain.model.ImageModel

interface MediaRepository {
    suspend fun getImages(): List<ImageModel>
}