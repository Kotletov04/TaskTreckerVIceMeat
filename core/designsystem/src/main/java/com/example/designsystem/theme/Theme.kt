package com.example.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext



private val darkColorScheme = darkColorScheme(
    primary = Black22,
    secondary = BlueNeon,
    tertiary = PinkFF,
    onTertiary = White,
    tertiaryContainer = Gray61,
    error = OrangeFF,
    errorContainer = OrangeFF5,
    onError = OrangeFF9

)

private val lightColorScheme = lightColorScheme(
    primary = WhiteF7,
    secondary = OrangeEB,
    tertiary = OrangeFB,
    onTertiary = Red91,
    tertiaryContainer = Gray87,
    error = OrangeFF,
    errorContainer = OrangeFF5,
    onError = OrangeFF9
)




@Composable
fun TaskTreckerVIceMeatTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}