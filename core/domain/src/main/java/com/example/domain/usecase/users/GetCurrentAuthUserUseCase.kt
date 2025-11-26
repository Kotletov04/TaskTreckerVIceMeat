package com.example.domain.usecase.users

import com.example.domain.model.UserModel
import com.example.domain.repository.UserRepository
import com.example.domain.util.Errors
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetCurrentAuthUserUseCase(private val userRepository: UserRepository) {

    /**
     * Возвращает [UserModel] текущего авторизованного пользователя.
     * */

    operator fun invoke(): Flow<Resource<UserModel>> = flow {
        try {
            emit(Resource.Loading())
            val user = userRepository.getCurrentUser()
            if (user != null) {
                emit(Resource.Success(data = user))
            } else {
                emit(Resource.Error(message = Errors.FirebaseAuthInvalidUserException.error))
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = Errors.IOException.error))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage?: Errors.UnknownError.error))
        }
    }

}