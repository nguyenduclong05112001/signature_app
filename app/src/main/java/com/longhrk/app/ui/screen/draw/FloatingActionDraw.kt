package com.longhrk.app.ui.screen.draw

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun FloatingActionDraw() {
    val density = LocalDensity.current
    val screenWidth = with(density) { LocalConfiguration.current.screenWidthDp.dp.toPx() }

    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    var isAtEdge by remember { mutableStateOf(false) }

    val animatedWidth by animateDpAsState(
        targetValue = if (isAtEdge) 6.dp else 48.dp,
        animationSpec = tween(durationMillis = 500)
    )
    val animatedHeight by animateDpAsState(
        targetValue = 48.dp,
        animationSpec = tween(durationMillis = 500)
    )
    val animatedCornerRadius by animateFloatAsState(
        targetValue = if (isAtEdge) 16f else 100f,
        animationSpec = tween(durationMillis = 500)
    )

    Box(
        modifier = Modifier
            .size(animatedWidth, animatedHeight)
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .clip(shape = RoundedCornerShape(animatedCornerRadius.dp))
            .border(
                width = 2.dp,
                color = Color.White,
                shape = RoundedCornerShape(animatedCornerRadius.dp)
            )
            .background(
                color = Color.Transparent,
            )
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        offsetX = if (offsetX > screenWidth / 2) {
                            screenWidth - if (isAtEdge) {
                                12.dp.let { with(density) { it.toPx() } }
                            } else {
                                48.dp.let { with(density) { it.toPx() } }
                            }
                        } else {
                            0f
                        }
                        isAtEdge = true
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                        isAtEdge = false
                    }
                )
            }
    )
}
