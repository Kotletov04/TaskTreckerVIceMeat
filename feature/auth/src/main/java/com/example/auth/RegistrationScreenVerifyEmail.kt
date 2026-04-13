
package com.example.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.designsystem.MeatViceVectorComponents
import com.example.designsystem.animation.NeonRabbitAnimationComponent
import com.example.designsystem.screen.CustomButton
import com.example.designsystem.screen.ErrorAlert
import com.example.designsystem.theme.Black22
import com.example.designsystem.theme.Montserrat
import com.example.designsystem.theme.OrangeFF9
import com.example.designsystem.theme.PinkFF
import kotlinx.coroutines.delay


@Composable
fun  RegistrationScreenVerifyEmail(
    viewModel: AuthViewModel = hiltViewModel(),
    onClickComplete: () -> Unit,

) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = state.permission) {
        println(state.permission)
        if (viewModel.state.value.permission == true) {
            viewModel.createNewUser()
            onClickComplete()
        }
    }

    RegistrationMainVerifyEmail(
        viewModel = viewModel,
        onClickComplete = {viewModel.verifyEmail()}
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
fun RegistrationMainVerifyEmail(
    viewModel: AuthViewModel = hiltViewModel(),
    onClickComplete: () -> Unit,
) {


    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.background(Black22).fillMaxSize()) {
            Box {
                NeonRabbitAnimationComponent()
                Image(painter = painterResource(MeatViceVectorComponents.RegVerifyTop), contentDescription = null)
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Column(modifier = Modifier.width(260.dp)) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        text = "Мы отправили письмо на:"
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(top = 25.dp, bottom = 25.dp),
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            brush = Brush.horizontalGradient(colors = listOf(PinkFF, OrangeFF9))
                        ),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        text = viewModel.regEmail.value
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        text = "Подтвердите аккаунт, перейдя по ссылке из письма"
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
                CustomButton(onClick = onClickComplete, text = "Завершить")
            }
        }

    }


}
