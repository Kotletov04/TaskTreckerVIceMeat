package com.example.designsystem.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.designsystem.MeatViceVectorComponents


@Composable
fun AuthComponent(content: @Composable (() -> Unit)) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(MeatViceVectorComponents.Logo
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            tint = MaterialTheme.colorScheme.onTertiary
        )
        content.invoke()
    }


}