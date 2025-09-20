package com.example.data.repository

import android.content.Context
import com.example.data.dto.UserDto
import com.example.domain.model.UserModel
import com.example.domain.repository.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.example.domain.util.ErrorMessages
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.cancellation.CancellationException
import androidx.core.content.edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import retrofit2.HttpException
import kotlin.coroutines.suspendCoroutine

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
): AuthRepository {

    override suspend fun login(email: String, password: String): Boolean {
        try {
            val authTask = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            return authTask.user != null
        } catch (e: FirebaseAuthInvalidUserException) {
            throw Exception(ErrorMessages.firebaseAuthInvalidUserException)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            throw Exception(ErrorMessages.firebaseAuthInvalidCredentialsException)
        } catch (e: CancellationException) {
            throw Exception(ErrorMessages.cancellationException)
        } catch (e: Exception) {
            throw Exception(ErrorMessages.unknownError)
        }

    }

    override suspend fun checkAuth(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
    }

    override suspend fun deleteAccount(email: String): Boolean {
        TODO("Здесь должна быть верификация профиля перед удалением аккаунта")
    }

    override suspend fun register(email: String, password: String): Boolean {
        try {
            val registerTask = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            registerTask.user!!.sendEmailVerification()
            return registerTask.user != null
        } catch (e: FirebaseAuthWeakPasswordException) {
            throw Exception(ErrorMessages.firebaseAuthWeakPasswordException)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            throw Exception(ErrorMessages.firebaseAuthInvalidCredentialsException)
        } catch (e: FirebaseAuthUserCollisionException) {
            throw Exception(ErrorMessages.firebaseAuthUserCollisionException)
        }
    }


    override suspend fun verifyCheckEmail(): Boolean {
        try {
            firebaseAuth.currentUser!!.reload()
            return firebaseAuth.currentUser?.isEmailVerified == true
        } catch (e: Exception) {
            throw Exception(ErrorMessages.unknownError)
        }

    }

    override suspend fun resetPassword(): Boolean {
        TODO("Здесь должна быть верификация профиля перед удалением аккаунта")
    }




}