package com.example.profile

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ProfileCustomFieldsModel
import com.example.domain.usecase.hubs.GetAllHubsUseCase
import com.example.domain.usecase.hubs.GetImagesUseCase
import com.example.domain.usecase.media.PutAvatarImageUseCase
import com.example.domain.usecase.media.RemoteGetAvatarImageUseCase
import com.example.domain.usecase.users.GetCurrentAuthUserUseCase
import com.example.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import com.example.domain.usecase.users.GetUserByIdUseCase
import com.example.profile.state.ProfileState
import com.example.profile.state.UserInfoState
import com.example.profile.state.AvatarState
import com.example.profile.util.ImageCompressor
import kotlinx.coroutines.launch




@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val getAllHubsUseCase: GetAllHubsUseCase,
    private val remoteGetAvatarImageUseCase: RemoteGetAvatarImageUseCase,
    private val getCurrentAuthUserUseCase: GetCurrentAuthUserUseCase,
    private val putAvatarImageUseCase: PutAvatarImageUseCase
): ViewModel() {

    private val _mainState = mutableStateOf(ProfileState())
    val mainState: State<ProfileState> = _mainState


    fun putNewAvatar(userId: String, newAvatar: Bitmap?, mimeType: String) {
        if (newAvatar != null) {
            val compressor = ImageCompressor()
            viewModelScope.launch {
                val newAvatarByteArray = compressor.compressImageByBitmap(
                    bitmap = newAvatar,
                    compressionThreshold = 200 * 1024L
                )
                putAvatarImageUseCase.invoke(
                    bucket = "vicemeat",
                    userId = userId,
                    avatar = newAvatarByteArray!!,
                    mimeType = mimeType
                ).collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            println(true)
                        }
                        is Resource.Error -> {
                            println(result.message)
                        }
                        is Resource.Success -> {
                            println(result.data)
                        }
                    }
                }


            }

        }
    }

    fun getCurrentUserInfo() {
        getCurrentAuthUserUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _mainState.value = _mainState.value.copy(
                        userInfoState = UserInfoState(isLoading = true)
                    )
                }
                is Resource.Error -> {
                    _mainState.value = _mainState.value.copy(
                        userInfoState = UserInfoState(error = result.message)
                    )
                }
                is Resource.Success -> {
                    _mainState.value = _mainState.value.copy(
                        userInfoState = UserInfoState(userInfo = result.data)
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAvatar(bucket: String, userId: String) {
        remoteGetAvatarImageUseCase.invoke(bucket = bucket, userId = userId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _mainState.value = _mainState.value.copy(
                        avatarState = AvatarState(isLoading = true)
                    )
                }
                is Resource.Success -> {
                    val avatar = BitmapFactory.decodeByteArray(result.data!!, 0, result.data!!.size)
                    _mainState.value = _mainState.value.copy(
                        avatarState = AvatarState(avatar = avatar)
                    )
                }
                is Resource.Error -> {
                    _mainState.value = _mainState.value.copy(
                        error = result.message,
                        avatarState = AvatarState(error = result.message)
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


    fun getImages() {
        getImagesUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _mainState.value = _mainState.value.copy(isLoading = true)
                }
                is Resource.Error -> {
                    _mainState.value = _mainState.value.copy(error = result.message)
                }
                is Resource.Success -> {
                    _mainState.value = _mainState.value.copy(localImages = result.data)

                }
            }
        }.launchIn(viewModelScope)
    }

    fun getProfileInfo() {







        val testMap2 = mapOf<String, String>(
            "Ник" to "Kotletov",
            "E-mail" to "aminovfap@gmail.com",
            "Номер телефона" to "+ 7-919-017-96-93",
            "Роль" to "Android developer",
            "Опыт работы" to "30 лет",
        )
        val customData = listOf<ProfileCustomFieldsModel>(
            ProfileCustomFieldsModel(
                userId = "123",
                title = "TEST1",
                fields = testMap2,
                type = "TEST DATA"
            )
        )
        _mainState.value = _mainState.value.copy(profileInfo = customData)
    }

    fun startProfileEditMod() {
        _mainState.value = _mainState.value.copy(editMode = true)
    }

}