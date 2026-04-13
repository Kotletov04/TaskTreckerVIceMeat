package com.example.domain.usecase.auth

import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow



class CheckAuthUseCase(private val authRepository: AuthRepository) {

    /**
     * Проверяет авторизован ли пользователь, который запускает приложение. Возвращает [true], если авторизован.
    */

    operator fun invoke(): Flow<Boolean> = flow {
        val result = authRepository.checkAuth()
        emit(result)
    }
}