package com.example.domain.usecase.hubs

import com.example.domain.model.ImageModel
import com.example.domain.repository.MediaRepository
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetImagesUseCase(private val mediaRepository: MediaRepository) {
    operator fun invoke(): Flow<Resource<List<ImageModel>>> = flow {

        val result = mediaRepository.getImages()
        emit(Resource.Success(data = result))
    }
}