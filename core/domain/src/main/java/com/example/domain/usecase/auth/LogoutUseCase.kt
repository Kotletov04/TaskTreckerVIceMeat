package com.example.domain.usecase.auth

import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LogoutUseCase(private val authRepository: AuthRepository) {

    /**
     * Завершает сессию авторизованного пользователя
    * */

    operator fun invoke(): Flow<Void> = flow {
        authRepository.logout()
    }
}