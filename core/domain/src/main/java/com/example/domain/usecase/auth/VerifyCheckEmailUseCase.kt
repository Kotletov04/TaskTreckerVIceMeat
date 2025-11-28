package com.example.domain.usecase.auth

import com.example.domain.repository.AuthRepository
import com.example.domain.util.Errors
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class VerifyCheckEmailUseCase(private val authRepository: AuthRepository) {

    /**
    * Проверяет подтвердил ли пользователь свою почту при регистрации.
     * Если почта подтверждена по письму, отправленному на почту, то возвращает [true]
    * */

    operator fun invoke(): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            val emailIsValid = authRepository.verifyCheckEmail()
            if (emailIsValid == false) {
                emit(Resource.Error(Errors.NotConfirmedEmail.error))
                return@flow
            }
            emit(Resource.Success(data = true))
        } catch (e: IOException) {
            emit(Resource.Error(message = Errors.IOException.error))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage?: Errors.UnknownError.error))
        }
    }
}