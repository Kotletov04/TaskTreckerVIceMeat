package com.example.data.repository

import com.example.data.dto.ChatDto
import com.example.data.dto.HubDto
import com.example.data.dto.UserDto
import com.example.domain.model.HubModel
import com.example.domain.repository.HubRepository
import com.example.domain.util.ErrorMessages
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.jvm.java

class HubRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDb: FirebaseFirestore
): HubRepository {

    override suspend fun createHub(hub: HubModel) {
        firebaseDb.collection("hubs").add(hub)
    }

    override suspend fun getHubById(id: String): HubModel {
        try {
            val result = firebaseDb.collection("hubs").document(id).get().await().toObject(HubDto::class.java)
            return result!!.toHubModel(id = id)
        } catch (e: Exception) {
            throw Exception(e.localizedMessage)
        }
    }

    override suspend fun getAllHubs(): List<HubModel> {
        val hubs = firebaseDb.collection("hubs").get().await()
        val hubModels = hubs.toObjects(HubDto::class.java).mapIndexed { index, data ->
            data.toHubModel(id = hubs.documents[index].id)
        }
        return hubModels
    }
    
}
