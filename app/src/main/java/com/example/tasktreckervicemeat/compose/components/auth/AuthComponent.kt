package com.example.tasktreckervicemeat.compose.components.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.tasktreckervicemeat.R


@Composable
fun AuthComponent(content: @Composable (() -> Unit)) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable.logo), contentDescription = null, modifier = Modifier.fillMaxWidth())
        content.invoke()
    }


}