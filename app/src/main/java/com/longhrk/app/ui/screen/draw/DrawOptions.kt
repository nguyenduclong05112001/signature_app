package com.longhrk.app.ui.screen.draw

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.longhrk.app.R
import com.longhrk.app.ui.extensions.toColor
import com.longhrk.app.ui.viewmodel.drag.DrawSignatureViewModel
import com.longhrk.app.ui.viewmodel.drag.model.TypeExpanded.COLOR
import com.longhrk.app.ui.viewmodel.drag.model.TypeExpanded.COLOR_PICKER
import com.longhrk.app.ui.viewmodel.drag.model.TypeExpanded.NONE
import com.longhrk.app.ui.viewmodel.drag.model.TypeExpanded.SIZE

@Composable
fun DrawOptions(
    drawSignatureViewModel: DrawSignatureViewModel,
    modifier: Modifier,
    onUnDo: () -> Unit,
    onReDo: () -> Unit,
    onColor: () -> Unit,
    onResize: () -> Unit,
    onReset: () -> Unit,
    onSave: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    val currentTypeExpanded by drawSignatureViewModel.currentTypeExpanded.collectAsState()
    val currentDrawColor by drawSignatureViewModel.currentDrawColor.collectAsState()
    val currentStateUI by drawSignatureViewModel.currentStateUI.collectAsState()

    Column(modifier = modifier) {

        when (currentTypeExpanded) {
            COLOR -> {
                ColorOptions(
                    modifier = Modifier.fillMaxWidth(),
                    drawSignatureViewModel = drawSignatureViewModel
                )
            }

            COLOR_PICKER -> {
                ColorPickerOption(
                    modifier = Modifier.fillMaxWidth(),
                    drawSignatureViewModel = drawSignatureViewModel
                )
            }

            SIZE -> {
                SizeOption(
                    modifier = Modifier.fillMaxWidth(),
                    drawSignatureViewModel = drawSignatureViewModel
                )
            }

            NONE -> {}
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(modifier = Modifier
                .weight(1f)
                .padding(7.dp)
                .clickable(
                    enabled = currentStateUI.saveEnabled,
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onSave()
                }) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_save),
                    tint = if (currentStateUI.saveEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                        0.5f
                    ),
                    contentDescription = null
                )
            }

            Box(modifier = Modifier
                .weight(1f)
                .padding(7.dp)
                .clickable(
                    enabled = currentStateUI.undoEnabled,
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onUnDo()
                }
            ) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_undo),
                    tint = if (currentStateUI.undoEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                        0.5f
                    ),
                    contentDescription = null
                )
            }

            Box(modifier = Modifier
                .weight(1f)
                .padding(7.dp)
                .clickable(
                    enabled = currentStateUI.redoEnabled,
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onReDo()
                }) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_redo),
                    tint = if (currentStateUI.redoEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                        0.5f
                    ),
                    contentDescription = null
                )
            }

            Box(modifier = Modifier
                .weight(1f)
                .padding(7.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onColor()
                }) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_color),
                    tint = currentDrawColor.toColor(),
                    contentDescription = null
                )
            }

            Box(modifier = Modifier
                .weight(1f)
                .padding(7.dp)
                .clickable(
                    enabled = currentStateUI.resetEnabled,
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onReset()
                }) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_restart),
                    tint = if (currentStateUI.resetEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                        0.5f
                    ),
                    contentDescription = null
                )
            }

            Box(modifier = Modifier
                .weight(1f)
                .padding(7.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onResize()
                }) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_size),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
            }
        }
    }
}