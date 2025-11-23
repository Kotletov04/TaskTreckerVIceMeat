package com.example.domain.usecase.auth

import com.example.domain.repository.AuthRepository
import com.example.domain.util.Errors
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class VerifyCheckEmailUseCase(private val authRepository: AuthRepository) {

    operator fun invoke(): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            val emailIsValid = authRepository.verifyCheckEmail()
            emit(Resource.Success(data = emailIsValid))
        } catch (e: IOException) {
            emit(Resource.Error(message = Errors.IOException.error))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage?: Errors.UnknownError.error))
        }
    }
}