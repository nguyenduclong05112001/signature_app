package com.longhrk.app.ui.viewmodel.drag.model

data class DrawUIState(
    val redoEnabled: Boolean = false,
    val undoEnabled: Boolean = false,
    val resetEnabled: Boolean = false,
    val saveEnabled: Boolean = false,
)