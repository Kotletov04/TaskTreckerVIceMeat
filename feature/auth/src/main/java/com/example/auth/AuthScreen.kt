package com.example.auth

import com.example.designsystem.RoundShapeComponent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.designsystem.auth.AuthComponent
import com.example.designsystem.screen.CustomButton
import com.example.designsystem.screen.ErrorAlert
import com.example.designsystem.theme.BlueNeon
import com.example.designsystem.theme.OrangeFF9
import com.example.designsystem.theme.YellowF8
import kotlinx.coroutines.delay

@Composable
internal fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onClickRegNav: () -> Unit,
    onClickLoginNav: () -> Unit
) {

    val state = viewModel.state.value

    // Если разрешение на логин получено, то тогда переводим на основные экраны
    LaunchedEffect(key1 = state.permission) {
        if (state.permission == true) {
            onClickLoginNav()
        }
    }

    AuthMain(
        onClickLogin = {
            viewModel.login()
        },
        onClickReg = {
            onClickRegNav()
        },
        viewModel = viewModel
    )

    if (!state.error.isNullOrBlank()) {
        LaunchedEffect(key1 = state.error) {
            delay(4500)
            viewModel.clearStateError()
        }
        ErrorAlert(text = state.error)
    }

}


@Composable
private fun AuthMain(
    onClickLogin: () -> Unit,
    onClickReg: () -> Unit,
    viewModel: AuthViewModel
) {
    val textFieldIsActive = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val isKeyboardOpen = WindowInsets.ime.getBottom(LocalDensity.current) > 0


    Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.primary).fillMaxSize()) {
        AnimatedVisibility(
            visible = !isKeyboardOpen,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                /*Image(modifier = Modifier.fillMaxWidth().align(Alignment.TopStart), painter = painterResource(MeatViceVectorComponents.RoundShape), contentDescription = null)*/
                RoundShapeComponent(
                    shapeColors = listOf(BlueNeon, Color.White, YellowF8),
                    xOffsetStep = 20.dp,
                    yOffsetStep = 0.dp,
                    height = 190.dp,
                    width = 200.dp,
                    modifier = Modifier.align(Alignment.TopStart).rotate(180f)
                )
                RoundShapeComponent(
                    shapeColors = listOf(BlueNeon, Color.White, OrangeFF9),
                    xOffsetStep = 40.dp,
                    yOffsetStep = 2.dp,
                    height = 210.dp,
                    width = 310.dp,
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
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
            focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onTertiary,
            unfocusedTextColor = Color.Gray,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            errorPlaceholderColor = MaterialTheme.colorScheme.error,
            errorIndicatorColor = MaterialTheme.colorScheme.error,
            errorTextColor = MaterialTheme.colorScheme.error,

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
            focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onTertiary,
            unfocusedTextColor = Color.Gray,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            errorPlaceholderColor = MaterialTheme.colorScheme.error,
            errorIndicatorColor = MaterialTheme.colorScheme.error,
            errorTextColor = MaterialTheme.colorScheme.error,

            ),
        isError = viewModel.state.value.error != ""
    )
}