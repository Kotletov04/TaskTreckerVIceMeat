package com.example.domain.usecase.media

import com.example.domain.repository.MediaRepository
import com.example.domain.util.Errors
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException


class PutAvatarImageUseCase(private val mediaRepository: MediaRepository) {

    operator fun invoke(bucket: String, userId: String, avatar: ByteArray, mimeType: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            mediaRepository.putAvatarImage(bucket = bucket, userId = userId, avatar = avatar, mimeType = mimeType)
            emit(Resource.Success(data = true))
        } catch (e: IOException) {
            emit(Resource.Error(message = Errors.IOException.error))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage?: Errors.UnknownError.error))
        }
    }


}