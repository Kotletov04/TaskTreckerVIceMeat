package com.example.tasktreckervicemeat.compose.screens.profile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ProfileCustomFieldsModel
import com.example.domain.usecase.hubs.GetAllHubsUseCase
import com.example.domain.usecase.hubs.GetImagesUseCase
import com.example.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import com.example.domain.usecase.users.GetUserByIdUseCase
import dagger.hilt.android.qualifiers.ApplicationContext

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val getAllHubsUseCase: GetAllHubsUseCase,
): ViewModel() {

    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    fun getImages() {
        getImagesUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = ProfileState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = ProfileState(error = result.message)
                }
                is Resource.Success -> {
                    _state.value = ProfileState(testData = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getProfileInfo() {

        val testMap2 = mapOf<String, String>(
            "Ник" to "Kotletov",
            "E-mail" to "aminovfap@gmail.com",
            "Ник1" to "Kotletov",
            "Ник2" to "Kotletov",
            "Ник3" to "Kotletov",
            "Ник4" to "Kotletov",
            "Ник5" to "Kotletov",
            "Ник6" to "Kotletov",
            "Ник7" to "Kotletov",
            "Ник8" to "Kotletov",
            "Ник9" to "Kotletov",
            "Ник10" to "Kotletov",
        )
        val customData = listOf<ProfileCustomFieldsModel>(
            ProfileCustomFieldsModel(
                userId = "123",
                title = "TEST1",
                fields = testMap2,
                type = "TEST DATA"
            ),
            ProfileCustomFieldsModel(
                userId = "123",
                title = "TEST2",
                fields = testMap2,
                type = "TEST DATA"
            ),
            ProfileCustomFieldsModel(
                userId = "123",
                title = "TEST3",
                fields = testMap2,
                type = "TEST DATA"
            ),
            ProfileCustomFieldsModel(
                userId = "123",
                title = "TEST4",
                fields = testMap2,
                type = "TEST DATA"
            ),
        )
        _state.value = _state.value.copy(profileInfo = customData)
    }


    fun startProfileEditMod() {
        _state.value = _state.value.copy(editMode = true)
    }

}