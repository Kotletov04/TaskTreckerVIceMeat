package com.example.domain.usecase.auth

import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CheckAuthUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(): Flow<Boolean> = flow {
        val result = authRepository.checkAuth()
        emit(result)
    }
}