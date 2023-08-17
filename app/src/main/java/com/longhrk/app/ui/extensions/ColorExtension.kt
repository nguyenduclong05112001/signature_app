package com.longhrk.app.ui.extensions

import androidx.compose.ui.graphics.Color

fun String.toColor() = Color(android.graphics.Color.parseColor(this))