package com.example.domain.usecase.auth

import com.example.domain.repository.AuthRepository
import com.example.domain.util.ErrorMessages
import com.example.domain.util.Errors
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import java.io.IOException

class LoginUseCase(private val authRepository: AuthRepository) {

    operator fun invoke(email: String, password: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            val result = authRepository.login(email = email, password = password)
            emit(Resource.Success(data = result))
        } catch (e: IOException) {
            emit(Resource.Error(message = ErrorMessages.ioException))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage?: ErrorMessages.unknownError))
        }
    }

}