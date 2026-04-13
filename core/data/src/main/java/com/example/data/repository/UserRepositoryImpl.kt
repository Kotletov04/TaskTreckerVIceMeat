package com.example.data.repository

import android.util.Log
import com.example.data.dto.UserDto
import com.example.domain.model.UserModel
import com.example.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseDb: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
): UserRepository {

    override suspend fun getAllUsers(): List<UserModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUser(): UserModel? {
        val authUserUid = firebaseAuth.currentUser!!.uid
        val user = firebaseDb.collection("users").document(authUserUid).get().await().toObject(UserDto::class.java)
        if (user != null) {
            return user.toUserModel(userId = authUserUid)
        } else {
            return null
        }
    }

    override suspend fun getUserById(id: String): UserModel? {
        val baseUserInfo = firebaseDb.collection("users").whereEqualTo("id", id).get().await()
        return baseUserInfo.first().toObject(UserDto::class.java).toUserModel(userId = baseUserInfo.first().id)
    }

    override suspend fun createUser(user: UserModel): Boolean {
        try {
            val authUserUid = firebaseAuth.currentUser!!.uid
            val userDto = UserDto(
                email = user.email,
                role = user.role,
                username = user.username
            )
            firebaseDb.collection("users").document(authUserUid).set(userDto).await()
            return true
        } catch (e: Exception) {
            return false
        }


    }
}