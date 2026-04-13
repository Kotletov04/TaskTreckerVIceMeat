package com.example.domain.usecase.hubs

import com.example.domain.model.HubModel
import com.example.domain.model.UserModel
import com.example.domain.repository.HubRepository
import com.example.domain.util.Errors
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import java.io.IOException






class CreateNewHubUseCase(private val hubRepository: HubRepository) {

    /**
     * Создает новый HUB. Обязательные параметры:
     * - [title]: Название Hub`a
     * - [adminName]: Имя админа - то есть создателя хаба
     * - [adminId]: Id админа
     * - [isOpen]: Определяет будет ли Hub виден у всех пользователей в ленте или только у приглашенных
     * Поле является необязательным [invitedUsers] - Пользователи, приглашенный в созданный хаб изначально.
     */

    operator fun invoke(
        title: String?,
        adminName: String?,
        adminId: String?,
        isOpen: Boolean,
        invitedUsers: List<UserModel>? = emptyList<UserModel>()
    ): Flow<Resource<Boolean>> = flow {
        try {
            if (title.isNullOrEmpty()) {
                emit(Resource.Error(message = "Название пустое!"))
                return@flow
            }
            if (adminName.isNullOrEmpty() || adminId.isNullOrEmpty()) {
                emit(Resource.Error(message = "Неизвестная ошибка"))
                return@flow
            }
            emit(Resource.Loading())
            hubRepository.createHub(hub = HubModel(title = title, adminName = adminName, adminId = adminId, isOpen = isOpen))
            emit(Resource.Success(data = true))
        } catch (e: IOException) {
            emit(Resource.Error(message = Errors.IOException.error))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage?: Errors.UnknownError.error))
        }
    }



}

