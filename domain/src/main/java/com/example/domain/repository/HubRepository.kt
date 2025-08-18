package com.example.domain.repository

import com.example.domain.model.HubModel

interface HubRepository {

    suspend fun getAllHubs(userId: Int)
    suspend fun getAllHubsByUserId(userId: Int): List<HubModel>
    suspend fun getAllOpenHubs(limit: Int): List<HubModel>
    suspend fun getHubById(hubId: Int): List<HubModel>
    suspend fun createHub()
    suspend fun saveHub()
    suspend fun deleteHub(userId: Int, hubId: Int): Boolean
    suspend fun editHub(hubId: Int)

}