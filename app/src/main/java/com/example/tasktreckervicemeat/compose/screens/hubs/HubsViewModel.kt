package com.example.tasktreckervicemeat.compose.screens.hubs

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.hubs.GetImagesUseCase
import com.example.domain.util.Resource
import com.example.tasktreckervicemeat.compose.screens.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HubsViewModel @Inject constructor(private val getImagesUseCase: GetImagesUseCase): ViewModel() {

    private val _state = mutableStateOf(HubsState())
    val state: State<HubsState> = _state

    fun getImages() {
        getImagesUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = HubsState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = HubsState(error = result.message)
                }
                is Resource.Success -> {
                    _state.value = HubsState(testData = result.data)
                    println(state.value)
                }
            }
        }.launchIn(viewModelScope)
    }

}