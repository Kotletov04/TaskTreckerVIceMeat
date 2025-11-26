package com.example.domain.usecase.auth

import com.example.domain.repository.AuthRepository
import com.example.domain.util.Errors
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import java.io.IOException

class LoginUseCase(private val authRepository: AuthRepository) {

    /**
    * Производит авторизацию по [email] и [password]. В случае успеха возвращает [true],
     * при пустых полях отрабатывает олшибку [Errors.NoInputData] или [Errors.InvalidData]
    * */

    operator fun invoke(email: String, password: String): Flow<Resource<Boolean>> = flow {
        try {
            if (email == "" && password == "") {
                emit(Resource.Error(message = Errors.NoInputData.error))
                return@flow
            }
            if (email == "" || password == "") {
                emit(Resource.Error(message = Errors.InvalidData.error))
                return@flow
            }
            emit(Resource.Loading())
            val result = authRepository.login(email = email, password = password)
            emit(Resource.Success(data = result))
        } catch (e: IOException) {
            emit(Resource.Error(message = Errors.IOException.error))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage?: Errors.IOException.error))
        }
    }


}