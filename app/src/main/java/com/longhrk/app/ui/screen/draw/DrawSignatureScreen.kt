package com.longhrk.app.ui.screen.draw

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.longhrk.app.ui.screen.draw.model.FloatingActionButtonModel
import com.longhrk.app.ui.screen.draw.model.OrientationModel
import com.longhrk.app.ui.screen.draw.screen.BaseScreen
import com.longhrk.app.ui.screen.draw.viewmodel.DrawSignatureViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun DrawSignatureScreen(
    drawSignatureViewModel: DrawSignatureViewModel = hiltViewModel<DrawSignatureViewModel>(),
    onBackScreen: () -> Unit
) {
    val floatingActionDraw = FloatingActionButtonModel(
        floatingActionButton = { FloatingActionDraw() },
        drawSignatureViewModel = drawSignatureViewModel
    )

    BaseScreen(
        onBackHandler = onBackScreen,
        orientation = OrientationModel.LANDSCAPE,
        floatingActionModel = floatingActionDraw
    ) {
        ContentDrawScreen(
            modifier = Modifier.fillMaxSize(),
            drawSignatureViewModel = drawSignatureViewModel
        )
    }
}