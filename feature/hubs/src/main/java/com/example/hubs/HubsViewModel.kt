package com.example.hubs

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.LogoutUseCase
import com.example.domain.usecase.hubs.CreateNewHubUseCase
import com.example.domain.usecase.hubs.GetAllHubsUseCase
import com.example.domain.usecase.hubs.GetImagesUseCase
import com.example.domain.usecase.users.GetCurrentAuthUserUseCase
import com.example.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HubsViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val getImagesUseCase: GetImagesUseCase,
    private val getCurrentUser: GetCurrentAuthUserUseCase,
    private val createNewHubUseCase: CreateNewHubUseCase,
    private val getAllHubsUseCase: GetAllHubsUseCase
): ViewModel() {

    private val _state = mutableStateOf(HubsState())
    val state: State<HubsState> = _state
    val hubTitle = mutableStateOf("")
    val hubIsOpen = mutableStateOf(false)


    fun getImages() {
        getImagesUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = HubsState(hubIsLoading = true)
                }
                is Resource.Error -> {
                    _state.value = HubsState(error = result.message)
                }
                is Resource.Success -> {
                    _state.value = HubsState(testData = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getCurrentUser() {
        getCurrentUser.invoke().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(currentUserIsLoading = true)
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message)
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(currentUser = result.data, currentUserIsLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun createNewHub() {
        createNewHubUseCase.invoke(
            title = hubTitle.value,
            isOpen = hubIsOpen.value,
            adminName = state.value.currentUser!!.username,
            adminId = state.value.currentUser!!.id,
        ).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(hubIsLoading = true)
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message)
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(hubIsLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAllHubs() {
        getAllHubsUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(hubIsLoading = true)
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message)
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(hubs = result.data, hubIsLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun cleanCreateHubFields() {
        hubTitle.value = ""
        hubIsOpen.value = false
    }

    fun logout() {
        logoutUseCase.invoke().launchIn(viewModelScope)
    }

}