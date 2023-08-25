package com.longhrk.drawview

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DrawBox(
    modifier: Modifier,
    drawController: DrawController,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    bitmapCallback: (ImageBitmap?, Throwable?) -> Unit,
    trackHistory: (undoCount: Int, redoCount: Int) -> Unit
) {
    val offsetCanvasTopStart = remember {
        mutableStateOf(Offset.Zero)
    }

    val offsetCanvasBottomEnd = remember {
        mutableStateOf(Offset.Zero)
    }

    Box(modifier = modifier) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                ComposeView(it).apply {
                    setContent {
                        LaunchedEffect(drawController) {
                            drawController.changeBgColor(backgroundColor)
                            drawController.trackBitmaps(this@apply, this, bitmapCallback)
                            drawController.trackHistory(this, trackHistory)
                        }
                        Canvas(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(drawController.bgColor)
                                .onGloballyPositioned { layout ->
                                    offsetCanvasTopStart.value = layout.boundsInRoot().topLeft
                                    offsetCanvasBottomEnd.value = layout.boundsInRoot().bottomRight
                                }
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onTap = { offset ->
                                            drawController.insertNewPath(offset)
                                            drawController.updateLatestPath(offset)
                                            drawController.pathList
                                        }
                                    )
                                }
                                .pointerInput(Unit) {
                                    detectDragGestures(
                                        onDragStart = { offset ->
                                            drawController.insertNewPath(offset)
                                        },
                                        onDrag = { change, _ ->
                                            if (change.position.x >= offsetCanvasTopStart.value.x
                                                && change.position.x <= offsetCanvasBottomEnd.value.x
                                                && change.position.y >= offsetCanvasTopStart.value.y
                                                && change.position.y <= offsetCanvasBottomEnd.value.y
                                            ) {
                                                val newPoint = change.position
                                                drawController.updateLatestPath(newPoint)
                                            }
                                        }
                                    )
                                }) {
                            drawController.pathList.forEach { pw ->
                                drawPath(
                                    createPath(pw.points),
                                    color = pw.strokeColor,
                                    alpha = pw.alpha,
                                    style = Stroke(
                                        width = pw.strokeWidth,
                                        cap = StrokeCap.Round,
                                        join = StrokeJoin.Round
                                    )
                                )
                            }
                        }
                    }
                }
            },
        )
    }
}





