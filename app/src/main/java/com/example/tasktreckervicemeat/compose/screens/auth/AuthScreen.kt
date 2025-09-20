package com.example.tasktreckervicemeat.compose.screens.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import com.example.tasktreckervicemeat.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktreckervicemeat.compose.components.auth.AuthComponent
import com.example.tasktreckervicemeat.compose.components.screen.CustomButton
import com.example.tasktreckervicemeat.compose.components.screen.ErrorAlert
import com.example.tasktreckervicemeat.ui.theme.Black22
import com.example.tasktreckervicemeat.ui.theme.BlueNeon
import com.example.tasktreckervicemeat.ui.theme.OrangeFF
import kotlinx.coroutines.delay

@Composable
fun AuthScreen(viewModel: AuthViewModel, onClickReg: () -> Unit, onClickLogin: () -> Unit) {
    val textFieldIsActive = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val isKeyboardOpen = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    val state = viewModel.state.value
    Box(modifier = Modifier.background(color = Black22).fillMaxSize()) {
        AnimatedVisibility(
            visible = !isKeyboardOpen,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                Image(modifier = Modifier.fillMaxWidth(), painter = painterResource(R.drawable.roundshape), contentDescription = null)
                Image(modifier = Modifier.fillMaxWidth(), painter = painterResource(R.drawable.roundshape2), contentDescription = null)
            }
        }

        Box(modifier = Modifier.fillMaxSize().imePadding(), contentAlignment = Alignment.Center) {
            AuthComponent {
                TextFields(viewModel = viewModel, textFieldIsActive = textFieldIsActive, focusRequester = focusRequester)
                Row(modifier = Modifier.fillMaxWidth().padding(top = 10.dp), horizontalArrangement = Arrangement.Center) {
                    CustomButton(onClick = onClickLogin, text = "Войти")
                    CustomButton(onClick = onClickReg, text = "Регистрация")
                }
            }
        }
        if (!state.error.isNullOrBlank()) {
            LaunchedEffect(key1 = state.error) {
                delay(4500)
                viewModel.clearStateError()
            }
            ErrorAlert(text = state.error)
        }

    }

}

@Composable
private fun TextFields(viewModel: AuthViewModel, textFieldIsActive: MutableState<Boolean>, focusRequester: FocusRequester) {

    TextField(
        modifier = Modifier
            .padding(10.dp)
            .onFocusChanged { focusState ->
                textFieldIsActive.value = focusState.isFocused
            }
            .focusRequester(focusRequester),
        value = viewModel.email.value,
        onValueChange = { newText ->
            viewModel.email.value = newText
        },
        placeholder = {
            Text(
                text = "E-mail",
                fontSize = 15.sp,
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = BlueNeon,
            unfocusedIndicatorColor = Color.White,
            unfocusedTextColor = Color.Gray,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            errorPlaceholderColor = OrangeFF,
            errorIndicatorColor = OrangeFF,
            errorTextColor = OrangeFF,

            ),
        isError = viewModel.state.value.error != ""


    )
    TextField(
        modifier = Modifier
            .padding(10.dp)
            .onFocusChanged { focusState ->
                textFieldIsActive.value = focusState.isFocused
            }
            .focusRequester(focusRequester),
        value = viewModel.password.value,
        onValueChange = { newText ->
            viewModel.password.value = newText
        },
        placeholder = {
            Text(
                text = "Пароль",
                fontSize = 15.sp,
            )
        },
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = BlueNeon,
            unfocusedIndicatorColor = Color.White,
            unfocusedTextColor = Color.Gray,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            errorPlaceholderColor = OrangeFF,
            errorIndicatorColor = OrangeFF,
            errorTextColor = OrangeFF,

            ),
        isError = viewModel.state.value.error != ""
    )
}