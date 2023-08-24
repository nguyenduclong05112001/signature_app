package com.longhrk.app.ui.screen.draw

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.longhrk.app.R
import com.longhrk.app.ui.components.HeaderApp
import com.longhrk.app.ui.viewmodel.drag.DrawSignatureViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun DrawSignatureScreen(
    drawSignatureViewModel: DrawSignatureViewModel,
    onBackScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        HeaderApp(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onBackground.copy(0.1f))
                .padding(vertical = 10.dp, horizontal = 15.dp),
            icon = painterResource(id = R.drawable.ic_arrow_back),
            title = stringResource(id = R.string.draw_signature)
        ) { onBackScreen() }

        ContentDrawScreen(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            drawSignatureViewModel = drawSignatureViewModel
        )
    }
}