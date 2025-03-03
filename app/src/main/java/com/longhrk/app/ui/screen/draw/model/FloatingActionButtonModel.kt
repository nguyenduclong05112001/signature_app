package com.longhrk.app.ui.screen.draw.model

import androidx.compose.runtime.Composable
import com.longhrk.app.ui.screen.draw.viewmodel.DrawSignatureViewModel

data class FloatingActionButtonModel(
    val floatingActionButton: @Composable () -> Unit = {},
    val drawSignatureViewModel: DrawSignatureViewModel
)