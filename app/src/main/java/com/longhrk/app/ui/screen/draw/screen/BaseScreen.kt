package com.longhrk.app.ui.screen.draw.screen

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.longhrk.app.ui.screen.draw.model.FloatingActionButtonModel
import com.longhrk.app.ui.screen.draw.model.OrientationModel
import com.longhrk.app.ui.screen.draw.utils.clickOutSideToHideKeyBoard


@Composable
fun BaseScreen(
    onBackHandler: () -> Unit,
    orientation: OrientationModel = OrientationModel.PORTRAIT,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionModel: FloatingActionButtonModel? = null,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val activity = context as? Activity

    BackHandler {
        onBackHandler.invoke()
    }

    SideEffect {
        activity?.requestedOrientation = when (orientation) {
            OrientationModel.PORTRAIT -> ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            OrientationModel.LANDSCAPE -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .clickOutSideToHideKeyBoard(),
        topBar = topBar,
        bottomBar = bottomBar,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            content.invoke()
            floatingActionModel?.floatingActionButton?.invoke()
        }
    }
}
