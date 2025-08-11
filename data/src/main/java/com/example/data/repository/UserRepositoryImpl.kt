package com.example.data.repository

import com.example.data.dto.UserDto
import com.example.domain.model.UserModel
import com.example.domain.repository.UserRepository
import com.example.domain.util.ErrorMessages
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseDb: FirebaseFirestore
): UserRepository {

    override suspend fun getUserByUid(uid: String): UserModel {
        try {
            val userDto = firebaseDb.collection("users").whereEqualTo("uid", uid).get().await()
            return userDto.first().toObject(UserDto::class.java).toUserModel()
        } catch (e: Exception) {
            throw Exception(ErrorMessages.unknownError)
        } catch (e: NullPointerException) {
            throw Exception(ErrorMessages.notFound)
        }

    }
}