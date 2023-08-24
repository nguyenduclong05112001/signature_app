package com.longhrk.app.ui.components.model

data class ButtonContent(
    val textContent: String = "",
    val onClick: () -> Unit = {}
)