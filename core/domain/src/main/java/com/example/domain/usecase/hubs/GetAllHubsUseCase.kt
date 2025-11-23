package com.example.domain.usecase.hubs

import com.example.domain.model.HubModel
import com.example.domain.repository.HubRepository
import com.example.domain.util.Errors
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException


class GetAllHubsUseCase(private val hubRepository: HubRepository) {

    operator fun invoke(): Flow<Resource<List<HubModel>>> = flow {
        try {
            emit(Resource.Loading())
            val result = hubRepository.getAllHubs()
            emit(Resource.Success(data = result))
        } catch (e: IOException) {
            emit(Resource.Error(message = Errors.IOException.error))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage?: Errors.UnknownError.error))
        }

    }

}