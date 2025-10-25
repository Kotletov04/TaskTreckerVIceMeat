package com.example.designsystem.chats

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.MeatViceIcons


@Composable
@Preview
fun MessageInputTest() {
    val test = remember { mutableStateOf("") }
    MessageInputComponent(messageField = test)
}

@Composable
fun MessageInputComponent(messageField: MutableState<String>) {
    val focusRequester = remember { FocusRequester() }
    TextField(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .onFocusChanged {

            }
            .focusRequester(focusRequester),
        value = messageField.value,
        onValueChange = { newText ->
            messageField.value = newText
        },
        placeholder = {
            Text(
                text = "Написать сообщение...",
                fontSize = 12.sp,
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
            unfocusedIndicatorColor = Color.White,
            unfocusedTextColor = Color.Gray,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            errorPlaceholderColor = MaterialTheme.colorScheme.error,
            errorIndicatorColor = MaterialTheme.colorScheme.error,
            errorTextColor = MaterialTheme.colorScheme.error),
        trailingIcon = {
            Row {
                Image(modifier = Modifier.size(25.dp), painter = painterResource(MeatViceIcons.Clip), contentDescription = null)
                Spacer(modifier = Modifier.width(20.dp))
                Image(modifier = Modifier.size(25.dp), painter = painterResource(MeatViceIcons.SendMessage), contentDescription = null)

            }
        }
        )
        /*isError = viewModel.state.value.error != ""*/
}