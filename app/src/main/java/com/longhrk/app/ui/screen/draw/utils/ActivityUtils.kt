package com.longhrk.app.ui.screen.draw.utils

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun SetOrientation(isPortrait: Boolean) {
    val context = LocalContext.current

    val activity = context as? Activity
    activity?.requestedOrientation = if (isPortrait) {
        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    } else {
        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }
}