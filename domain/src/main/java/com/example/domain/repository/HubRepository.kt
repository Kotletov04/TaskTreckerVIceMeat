package com.example.domain.repository

import com.example.domain.model.HubModel

interface HubRepository {
    suspend fun createHub(hub: HubModel)
    suspend fun getHubById(id: String): HubModel
    suspend fun getAllHubs(): List<HubModel>
}