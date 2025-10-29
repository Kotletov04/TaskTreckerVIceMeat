package com.example.hubs

import android.graphics.BitmapFactory
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.LogoutUseCase
import com.example.domain.usecase.hubs.CreateNewHubUseCase
import com.example.domain.usecase.hubs.GetAllHubsUseCase
import com.example.domain.usecase.media.RemoteGetAvatarImageUseCase
import com.example.domain.usecase.users.GetCurrentAuthUserUseCase
import com.example.domain.util.Resource
import com.example.hubs.state.HubsState
import com.example.hubs.state.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject




@HiltViewModel
class HubsViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val remoteGetAvatarImageUseCase: RemoteGetAvatarImageUseCase,
    private val getCurrentUser: GetCurrentAuthUserUseCase,
    private val createNewHubUseCase: CreateNewHubUseCase,
    private val getAllHubsUseCase: GetAllHubsUseCase
): ViewModel() {

    private val _state = mutableStateOf(MainHubsState())
    val state: State<MainHubsState> = _state


    val hubTitle = mutableStateOf("")
    val hubIsOpen = mutableStateOf(false)

    fun getCurrentUser() {
        getCurrentUser.invoke().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(userState = UserState(isLoading = true))

                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(userState = UserState(error = result.message))
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(userState = UserState(user = result.data))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun createNewHub() {
        val admin = state.value.userState
        createNewHubUseCase.invoke(
            title = hubTitle.value,
            isOpen = hubIsOpen.value,
            adminName = admin?.user?.username,
            adminId = admin?.user?.id,
        ).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        hubsState = _state.value.hubsState?.copy(isLoading = true)
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        hubsState = _state.value.hubsState?.copy(error = result.message)
                    )
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        hubsState = _state.value.hubsState?.copy(hubIsCreated = result.data)
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAllHubs() {
        getAllHubsUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(hubsState = HubsState(isLoading = true))
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(hubsState = HubsState(error = result.message))
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(hubsState = HubsState(hubsList = result.data))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun cleanCreateHubFields() {
        hubTitle.value = ""
        hubIsOpen.value = false
    }

    fun getCurrentUserAvatar(bucket: String, userId: String) {
        remoteGetAvatarImageUseCase.invoke(bucket = bucket, userId = userId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        userState = _state.value.userState?.copy(error = result.message)
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        userState = _state.value.userState?.copy(isLoading = true)
                    )
                }
                is Resource.Success -> {
                    val avatar = BitmapFactory.decodeByteArray(result.data!!, 0, result.data!!.size)
                    _state.value = _state.value.copy(
                        userState = _state.value.userState?.copy(avatar = avatar)
                    )
                }
            }
        }
    }

    fun logout() {
        logoutUseCase.invoke().launchIn(viewModelScope)
    }

}