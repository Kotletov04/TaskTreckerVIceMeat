package com.example.domain.usecase.media

import com.example.domain.repository.MediaRepository
import com.example.domain.util.ErrorMessages
import com.example.domain.util.Errors
import com.example.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.IOException

class RemoteGetAvatarImageUseCase(private val mediaRepository: MediaRepository) {

    operator fun invoke(bucket: String, userId: String): Flow<Resource<ByteArray>> = flow {
        try {
            emit(Resource.Loading())
            val result = mediaRepository.getAvatarImage(bucket = bucket, userId = userId)
            emit(Resource.Success(data = result))
        } catch (e: IOException) {
            emit(Resource.Error(message = ErrorMessages.ioException))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage))
        }
    }

}