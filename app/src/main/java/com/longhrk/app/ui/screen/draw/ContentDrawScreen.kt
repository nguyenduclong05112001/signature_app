package com.longhrk.app.ui.screen.draw

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.layout.findRootCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.longhrk.app.R
import com.longhrk.app.ui.components.DialogConfirm
import com.longhrk.app.ui.components.model.ButtonContent
import com.longhrk.app.ui.components.model.DialogType
import com.longhrk.app.ui.extensions.toColor
import com.longhrk.app.ui.viewmodel.drag.DrawSignatureViewModel
import com.longhrk.app.ui.viewmodel.drag.model.TypeExpanded
import io.ak1.drawbox.DrawBox
import io.ak1.drawbox.rememberDrawController

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ContentDrawScreen(
    modifier: Modifier,
    drawSignatureViewModel: DrawSignatureViewModel
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val currentStateUI by drawSignatureViewModel.currentStateUI.collectAsState()

    val drawController = rememberDrawController().apply {
        changeBgColor(MaterialTheme.colorScheme.onBackground.copy(0.3f))
        trackHistory(
            scope = coroutineScope,
            trackHistory = { currentUndo, currentRedo ->
                drawSignatureViewModel.updateCurrentStateUI(
                    currentStateUI.copy(
                        resetEnabled = currentUndo != 0 || currentRedo != 0,
                        saveEnabled = currentUndo != 0,
                        redoEnabled = currentRedo != 0,
                        undoEnabled = currentUndo != 0,
                    )
                )
            }
        )
    }

    val currentDrawColor by drawSignatureViewModel.currentDrawColor.collectAsState()
    val currentStrokeWidth by drawSignatureViewModel.currentStrokeWidth.collectAsState()
    val dialogState by drawSignatureViewModel.dialogCurrent.collectAsState()
    val uriShare by drawSignatureViewModel.uriFromPhoto.collectAsState()

    LaunchedEffect(currentDrawColor) {
        drawController.changeColor(currentDrawColor.toColor())
    }

    LaunchedEffect(currentStrokeWidth) {
        drawController.changeStrokeWidth(currentStrokeWidth)
    }

    Box(modifier = modifier) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (drawContent, drawOptions) = createRefs()

            DrawBox(
                modifier = Modifier
                    .constrainAs(drawContent) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(drawOptions.top)

                        width = Dimension.matchParent
                        height = Dimension.value(200.dp)
                    },
                drawController = drawController,
                bitmapCallback = { imageBitmap, _ ->
                    imageBitmap?.let {
                        drawSignatureViewModel.saveInStorage(
                            bitmap = it.asAndroidBitmap(),
                            context = context
                        )
                    }
                }
            )

            DrawOptions(
                drawSignatureViewModel = drawSignatureViewModel,
                modifier = Modifier
                    .constrainAs(drawOptions) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)

                        width = Dimension.matchParent
                        height = Dimension.wrapContent
                    }
                    .background(MaterialTheme.colorScheme.onBackground)
                    .padding(vertical = 10.dp),
                onSave = {
                    drawSignatureViewModel.updateDialog(DialogType.TWO_BUTTON)
                },
                onUnDo = { drawController.unDo() },
                onReDo = { drawController.reDo() },
                onReset = { drawController.reset() },
                onColor = { drawSignatureViewModel.updateTypeExpanded(TypeExpanded.COLOR) },
                onResize = { drawSignatureViewModel.updateTypeExpanded(TypeExpanded.SIZE) },
            )
        }

        when (dialogState) {
            DialogType.ONE_BUTTON -> {
                DialogConfirm(
                    type = DialogType.ONE_BUTTON,
                    title = stringResource(id = R.string.notification),
                    content = stringResource(id = R.string.save_success),
                    buttonContent = ButtonContent(
                        textContent = stringResource(id = R.string.done),
                        onClick = {
                            drawSignatureViewModel.updateDialog(null)
                            drawSignatureViewModel.shareUriOutApplication(
                                context = context,
                                uri = uriShare
                            )
                        }
                    ),
                )
            }

            DialogType.TWO_BUTTON -> {
                DialogConfirm(
                    type = DialogType.TWO_BUTTON,
                    title = stringResource(id = R.string.confirm),
                    content = stringResource(id = R.string.content_ofconfirm_dialog),
                    buttonCancel = ButtonContent(
                        textContent = stringResource(id = R.string.cancel),
                        onClick = {
                            drawSignatureViewModel.updateDialog(null)
                        }
                    ),
                    buttonAgree = ButtonContent(
                        textContent = stringResource(id = R.string.save),
                        onClick = {
                            drawController.saveBitmap()
                        }
                    ),
                )
            }

            else -> {
                //hide
            }
        }

    }
}