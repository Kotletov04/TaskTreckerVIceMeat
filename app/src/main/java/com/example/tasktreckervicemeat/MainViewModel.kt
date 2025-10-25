package com.example.tasktreckervicemeat

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.CheckAuthUseCase
import com.example.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import androidx.compose.runtime.State
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkAuthUseCase: CheckAuthUseCase
): ViewModel() {


    private val _state = mutableStateOf(AppState())
    val state: State<AppState> = _state

    init {
        checkAuthUser()
    }

    private fun checkAuthUser() {
        checkAuthUseCase.invoke().onEach {
            if (it == true) {
                _state.value = AppState(startStrategy = StartStrategies.AuthorizedStart())
            } else {
                _state.value = AppState(startStrategy = StartStrategies.AnonymousStart())
            }
        }.launchIn(viewModelScope)
    }


}