package com.longhrk.app.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

val LightColors = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    error = errorLight,
    errorContainer = errorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight
)

val DarkColors = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    error = errorDark,
    errorContainer = errorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark
)

@Composable
fun LongHRK_Signature_Them(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        } else {
            if (darkTheme) DarkColors else LightColors
        }

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = longHRKShapes,
        typography = LongHRKTypography,
        content = content
    )
}
