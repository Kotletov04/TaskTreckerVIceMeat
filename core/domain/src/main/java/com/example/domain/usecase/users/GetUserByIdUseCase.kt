package com.example.domain.usecase.users

import com.example.domain.model.UserModel
import com.example.domain.repository.ProfileRepository
import com.example.domain.repository.UserRepository
import com.example.domain.util.Errors
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetUserByIdUseCase(private val userRepository: UserRepository) {
    /**
    * Находит пользователя по его [id], возвращая [UserModel]
    * */
    operator fun invoke(id: String): Flow<Resource<UserModel>> = flow {
        try {
            if (id.isEmpty()) {
                emit(Resource.Error(Errors.FirebaseAuthInvalidUserException.error))
                return@flow
            }
            emit(Resource.Loading())
            val user = userRepository.getUserById(id = id)
            emit(Resource.Success(data = user!!))
        } catch (e: IOException) {
            emit(Resource.Error(message = Errors.IOException.error))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage?: Errors.UnknownError.error))
        } catch (e: NullPointerException) {
            emit(Resource.Error(message = Errors.FirebaseAuthInvalidUserException.error))
        }
    }

}