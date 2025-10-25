package com.example.data.repository

import com.example.data.dto.ProfileCustomFieldsDto
import com.example.data.dto.UserDto
import com.example.domain.model.ProfileCustomFieldsModel
import com.example.domain.model.UserModel
import com.example.domain.repository.ProfileRepository
import com.example.domain.util.ErrorMessages
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val firebaseDb: FirebaseFirestore
): ProfileRepository {

    override suspend fun getProfileByUserId(id: String): UserModel {
        try {
            val baseUserInfo = firebaseDb.collection("users").whereEqualTo("id", id).get().await()
            val userModel = baseUserInfo.first().toObject(UserDto::class.java).toUserModel(userId = "1")
            return userModel
        } catch (e: Exception) {
            throw Exception(ErrorMessages.unknownError)
        } catch (e: NullPointerException) {
            throw Exception(ErrorMessages.notFound)
        }
    }

    override suspend fun getCustomProfileFieldsByUserId(id: String): List<ProfileCustomFieldsModel> {
        try {
            val customProfileFields = firebaseDb.collection("profile_custom_fields").whereEqualTo("userId", id).get().await()
            val customFieldsModel = customProfileFields.map { it.toObject(ProfileCustomFieldsDto::class.java).toProfileCustomFieldsModel() }
            TODO("Not yet implemented")
        } catch (e: Exception) {
            throw Exception(ErrorMessages.unknownError)
        }
    }

    override suspend fun editProfileByUserId(id: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProfileByUserId(id: String): Boolean {
        TODO("Not yet implemented")
    }
}