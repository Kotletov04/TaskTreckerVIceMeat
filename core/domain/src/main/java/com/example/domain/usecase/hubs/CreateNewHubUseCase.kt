package com.example.domain.usecase.hubs

import com.example.domain.model.HubModel
import com.example.domain.model.UserModel
import com.example.domain.repository.HubRepository
import com.example.domain.util.ErrorMessages
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import java.io.IOException

class CreateNewHubUseCase(private val hubRepository: HubRepository) {

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
            emit(Resource.Error(message = ErrorMessages.ioException))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage?: ErrorMessages.unknownError))
        }
    }



}

