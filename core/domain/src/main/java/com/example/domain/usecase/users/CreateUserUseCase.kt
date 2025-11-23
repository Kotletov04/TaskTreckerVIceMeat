package com.example.domain.usecase.users

import com.example.domain.model.UserModel
import com.example.domain.repository.UserRepository
import com.example.domain.util.Errors

import com.example.domain.util.Resource
import jdk.jfr.internal.OldObjectSample.emit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class CreateUserUseCase(private val userRepository: UserRepository) {

    operator fun invoke(
        username: String,
        email: String,
        role: String = "User"
    ): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            val result = userRepository.createUser(user = UserModel(username = username, email = email, role = role))
            if (result == true) {
                emit(Resource.Success(data = result))
            } else {
                emit(Resource.Error(message = Errors.UnknownError.error))
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = Errors.IOException.error))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage?: Errors.UnknownError.error))
        }
    }
}