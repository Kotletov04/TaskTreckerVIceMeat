package com.example.domain.usecase.auth

import com.example.domain.repository.AuthRepository
import com.example.domain.util.Errors
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import com.example.domain.model.UserModel
import com.example.domain.usecase.users.CreateUserUseCase

class RegisterUseCase(private val authRepository: AuthRepository) {

    /**
     * Регистрирует нового пользователя по [email] и [password]. Возвращает [true] при успешной регистрации.
     * Пароль не может быть меньше 8 знаков, иначе возвращает [Errors.FirebaseAuthWeakPasswordException].
     * UseCase не создает [UserModel], при регистрации, его нужно использовать вместе с другим UseCase [CreateUserUseCase]
    * */

    operator fun invoke(email: String, password: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            if (password.length < 8) {
                emit(Resource.Error(message = Errors.FirebaseAuthWeakPasswordException.error))
                return@flow
            }
            val result = authRepository.register(email = email, password = password)
            emit(Resource.Success(data = result))
        } catch (e: IOException) {
            emit(Resource.Error(message = Errors.IOException.error))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage?: Errors.UnknownError.error))
        }
    }

}