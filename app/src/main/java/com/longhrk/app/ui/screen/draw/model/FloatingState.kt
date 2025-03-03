package com.longhrk.app.ui.screen.draw.model

sealed class FloatingState {
    data object None : FloatingState()
    data class Option(val test: Int) : FloatingState()
}