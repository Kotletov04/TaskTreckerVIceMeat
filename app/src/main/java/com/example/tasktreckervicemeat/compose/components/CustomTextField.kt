package com.example.tasktreckervicemeat.compose.components

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
@Preview
private fun TestTextField() {
    val test = remember { mutableStateOf("") }
    CustomTextField(value = test.value, onValueChange = {test.value = it})
}


@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,

) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,

    )
}