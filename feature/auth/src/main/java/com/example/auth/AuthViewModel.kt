package com.example.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import com.example.domain.util.ErrorMessages
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.example.domain.util.Resource
import javax.inject.Inject
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.RegisterUseCase
import com.example.domain.usecase.auth.VerifyCheckEmailUseCase
import com.example.domain.usecase.auth.CheckAuthUseCase
import com.example.domain.usecase.users.CreateUserUseCase


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val verifyCheckEmailUseCase: VerifyCheckEmailUseCase,
    private val checkAuthUseCase: CheckAuthUseCase,
    private val createNewUser: CreateUserUseCase
): ViewModel() {

    val email = mutableStateOf("")
    val password = mutableStateOf("")

    val regEmail = mutableStateOf("")
    val regPassword = mutableStateOf("")
    val repPassword = mutableStateOf("")
    val username = mutableStateOf("")

    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state

    fun login() {
        if (!loginCheckFields()) return
        loginUseCase.invoke(email = email.value, password = password.value).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = AuthState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = AuthState(error = result.message)
                }
                is Resource.Success -> {
                    _state.value = AuthState(permission = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loginCheckFields(): Boolean {
        if (email.value == "") {
            _state.value = _state.value.copy(error = ErrorMessages.emptyEmail)
            return false
        }
        if (password.value == "") {
            _state.value = _state.value.copy(error = ErrorMessages.emptyPassword)
            return false
        }
        return true

    }

    fun register() {
        registerUseCase.invoke(email = regEmail.value, password = regPassword.value).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = AuthState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = AuthState(error = result.message)
                }
                is Resource.Success -> {
                    _state.value = AuthState(userIsRegister = true)
                }
            }

        }.launchIn(viewModelScope)
    }

    fun checkRegistrationEmail(): Boolean {
        if (regEmail.value == "") {
            _state.value = _state.value.copy(error = ErrorMessages.emptyEmail)
            return false
        }
        return true
    }

    fun checkRegistrationUsername(): Boolean {
        if (username.value == "") {
            _state.value = _state.value.copy(error = ErrorMessages.emptyUsername)
            return false
        }
        return true
    }

    fun checkRegistrationPassword(): Boolean {
        if (regPassword.value == "") {
            _state.value = _state.value.copy(error = ErrorMessages.emptyPassword)
            return false
        }
        if (repPassword.value == "") {
            _state.value = _state.value.copy(error = ErrorMessages.emptyRepeatPassword)
            return false
        }
        if (regPassword.value.length <= 6) {
            _state.value = _state.value.copy(error = ErrorMessages.invalidLenghtPassword)
            return false
        }
        if (regPassword.value != repPassword.value) {
            _state.value = _state.value.copy(error = ErrorMessages.passwordDoNotMatch)
            return false
        }
        return true
    }

    fun verifyEmail() {
        verifyCheckEmailUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = AuthState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = AuthState(error = result.message)
                }
                is Resource.Success -> {
                    _state.value = AuthState(permission = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun checkAuth() {
        checkAuthUseCase.invoke().onEach { result ->
            _state.value = AuthState(permission = result)
        }.launchIn(viewModelScope)
    }

    fun clearStateError() {
        _state.value = _state.value.copy(error = "")
    }

    fun createNewUser() {
        createNewUser.invoke(username = username.value, email = regEmail.value).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = AuthState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = AuthState(error = result.message)
                }
                is Resource.Success -> {

                }
            }
        }.launchIn(viewModelScope)
    }

}