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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.cancellation.CancellationException
import androidx.core.content.edit
import com.example.domain.util.Errors
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
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            throw Exception(Errors.FirebaseAuthInvalidCredentialsException.error)
        } catch (e: CancellationException) {
            throw Exception(Errors.CancellationException.error)
        } catch (e: Exception) {
            throw Exception(Errors.UnknownError.error)
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
            throw Exception(Errors.FirebaseAuthWeakPasswordException.error)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            throw Exception(Errors.FirebaseAuthInvalidCredentialsException.error)
        } catch (e: FirebaseAuthUserCollisionException) {
            throw Exception(Errors.FirebaseAuthUserCollisionException.error)
        }
    }


    override suspend fun verifyCheckEmail(): Boolean {
        try {
            firebaseAuth.currentUser!!.reload()
            return firebaseAuth.currentUser?.isEmailVerified == true
        } catch (e: Exception) {
            throw Exception(Errors.UnknownError.error)
        }

    }

    override suspend fun resetPassword(): Boolean {
        TODO("Здесь должна быть верификация профиля перед удалением аккаунта")
    }




}