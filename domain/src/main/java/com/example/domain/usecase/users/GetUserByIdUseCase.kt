package com.example.domain.usecase.users

import com.example.domain.model.UserModel
import com.example.domain.repository.ProfileRepository
import com.example.domain.repository.UserRepository
import com.example.domain.util.ErrorMessages
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetUserByIdUseCase(private val userRepository: UserRepository) {

    operator fun invoke(id: String): Flow<Resource<UserModel>> = flow {
        try {
            emit(Resource.Loading())
            val user = userRepository.getUserById(id = id)
            emit(Resource.Success(data = user))
        } catch (e: IOException) {
            emit(Resource.Error(message = ErrorMessages.ioException))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage))
        }
    }

}