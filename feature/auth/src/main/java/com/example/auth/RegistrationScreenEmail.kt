package com.example.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.designsystem.MeatViceIcons
import com.example.designsystem.MeatViceVectorComponents
import com.example.designsystem.auth.AuthComponent
import com.example.designsystem.auth.RegistrationLineComponent
import com.example.designsystem.screen.CustomButton
import com.example.designsystem.screen.ErrorAlert
import com.example.designsystem.theme.Black22
import com.example.designsystem.theme.BlueNeon
import com.example.designsystem.theme.Montserrat
import com.example.designsystem.theme.OrangeFF
import kotlinx.coroutines.delay



@Composable
fun RegistrationScreenEmail(
    viewModel: AuthViewModel = hiltViewModel(),
    onClickNextStep: () -> Unit,
    onClickBack: () -> Unit
) {

    val state = viewModel.state.value

    RegistrationEmailMain(
        viewModel = viewModel,
        onClickNextStep = {
            if (viewModel.checkRegistrationEmail()) {
                onClickNextStep()
            }
        },
        onClickBack = onClickBack
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
private fun RegistrationEmailMain(
    viewModel: AuthViewModel,
    onClickNextStep: () -> Unit,
    onClickBack: () -> Unit
) {
    val textFieldIsActive = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val isKeyboardOpen = WindowInsets.ime.getBottom(LocalDensity.current) > 0

    Box(modifier = Modifier.background(color = Black22).fillMaxSize()) {
        AnimatedVisibility(
            visible = !isKeyboardOpen,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                Image(modifier = Modifier.fillMaxWidth(), painter = painterResource(MeatViceVectorComponents.RegTop), contentDescription = null)
                Image(modifier = Modifier.fillMaxWidth(), painter = painterResource(MeatViceVectorComponents.RegBottom), contentDescription = null)
            }

        }

        Box(modifier = Modifier.fillMaxSize().imePadding(), contentAlignment = Alignment.Center) {
            AuthComponent {
                Text(
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(top = 10.dp),
                    text = "Регистрация"
                )
                EmailTextField(viewModel = viewModel, textFieldIsActive = textFieldIsActive, focusRequester = focusRequester)
                Row(modifier = Modifier.fillMaxWidth().padding(top = 10.dp), horizontalArrangement = Arrangement.Center) {
                    CustomButton(onClick = onClickNextStep, text = "Далее")
                }
                Spacer(modifier = Modifier.height(20.dp))
                RegistrationLineComponent(iconRegScreen = painterResource(MeatViceIcons.Mail), stage = 1)
            }
        }

    }
}

@Composable
private fun EmailTextField(viewModel: AuthViewModel, textFieldIsActive: MutableState<Boolean>, focusRequester: FocusRequester) {
    TextField(
        modifier = Modifier
            .padding(10.dp)
            .onFocusChanged { focusState ->
                textFieldIsActive.value = focusState.isFocused
            }
            .focusRequester(focusRequester),
        value = viewModel.regEmail.value,
        onValueChange = { newText ->
            viewModel.regEmail.value = newText
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
}