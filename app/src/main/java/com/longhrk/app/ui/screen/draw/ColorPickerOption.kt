package com.longhrk.app.ui.screen.draw

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.ImageColorPicker
import com.github.skydoves.colorpicker.compose.PaletteContentScale
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.longhrk.app.R
import com.longhrk.app.ui.extensions.toColor
import com.longhrk.app.ui.screen.draw.viewmodel.DrawSignatureViewModel
import com.longhrk.app.ui.viewmodel.drag.model.TypeExpanded
import kotlin.math.roundToInt

@Composable
fun ColorPickerOption(
    modifier: Modifier,
    drawSignatureViewModel: DrawSignatureViewModel
) {
    val interactionSource = remember { MutableInteractionSource() }

    val controller = rememberColorPickerController()

    val currentDrawColor by drawSignatureViewModel.currentDrawColor.collectAsState()

    var colorShowing by remember {
        mutableStateOf(currentDrawColor)
    }

    var redColor by remember {
        mutableIntStateOf(0)
    }

    var greenColor by remember {
        mutableIntStateOf(0)
    }

    var blueColor by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.color_picker),
            color = MaterialTheme.colorScheme.background,
            style = MaterialTheme.typography.labelMedium
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(20.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                ),
        ) {
            ImageColorPicker(
                modifier = Modifier.fillMaxSize(),
                paletteImageBitmap = ImageBitmap.imageResource(R.drawable.color_picker),
                controller = controller,
                paletteContentScale = PaletteContentScale.FIT,
                onColorChanged = { colorEnvelope ->
                    redColor = ((colorEnvelope.color.red) * 255.0f).roundToInt()
                    greenColor = ((colorEnvelope.color.green) * 255.0f).roundToInt()
                    blueColor = ((colorEnvelope.color.blue) * 255.0f).roundToInt()

                    if (colorEnvelope.fromUser) {
                        colorShowing = "#${colorEnvelope.hexCode}"
                    }
                }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(colorShowing.toColor())
                )

                Spacer(modifier = Modifier.size(10.dp))

                BasicTextField(
                    modifier = Modifier
                        .width(100.dp)
                        .background(MaterialTheme.colorScheme.background.copy(0.05f))
                        .border(width = 1.dp, color = MaterialTheme.colorScheme.background)
                        .padding(vertical = 5.dp),
                    readOnly = true,
                    enabled = false,
                    value = colorShowing,
                    singleLine = true,
                    maxLines = 1,
                    textStyle = MaterialTheme.typography.labelMedium.copy(textAlign = TextAlign.Center),
                    onValueChange = {}
                )
            }

            Column(verticalArrangement = Arrangement.SpaceBetween) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BasicTextField(
                        modifier = Modifier
                            .width(70.dp)
                            .background(MaterialTheme.colorScheme.background.copy(0.05f))
                            .padding(vertical = 5.dp),
                        readOnly = true,
                        enabled = false,
                        singleLine = true,
                        maxLines = 1,
                        value = "Red",
                        textStyle = MaterialTheme.typography.labelMedium.copy(textAlign = TextAlign.Center),
                        onValueChange = {}
                    )
                    BasicTextField(
                        modifier = Modifier
                            .width(70.dp)
                            .background(MaterialTheme.colorScheme.background.copy(0.05f))
                            .border(width = 1.dp, color = MaterialTheme.colorScheme.background)
                            .padding(vertical = 5.dp),
                        readOnly = true,
                        enabled = false,
                        singleLine = true,
                        maxLines = 1,
                        value = redColor.toString(),
                        textStyle = MaterialTheme.typography.labelMedium.copy(textAlign = TextAlign.Center),
                        onValueChange = {}
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BasicTextField(
                        modifier = Modifier
                            .width(70.dp)
                            .background(MaterialTheme.colorScheme.background.copy(0.05f))
                            .padding(vertical = 5.dp),
                        readOnly = true,
                        enabled = false,
                        singleLine = true,
                        maxLines = 1,
                        value = "Green",
                        textStyle = MaterialTheme.typography.labelMedium.copy(textAlign = TextAlign.Center),
                        onValueChange = {}
                    )
                    BasicTextField(
                        modifier = Modifier
                            .width(70.dp)
                            .background(MaterialTheme.colorScheme.background.copy(0.05f))
                            .border(width = 1.dp, color = MaterialTheme.colorScheme.background)
                            .padding(vertical = 5.dp),
                        readOnly = true,
                        enabled = false,
                        singleLine = true,
                        maxLines = 1,
                        value = greenColor.toString(),
                        textStyle = MaterialTheme.typography.labelMedium.copy(textAlign = TextAlign.Center),
                        onValueChange = {}
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BasicTextField(
                        modifier = Modifier
                            .width(70.dp)
                            .background(MaterialTheme.colorScheme.background.copy(0.05f))
                            .padding(vertical = 5.dp),
                        readOnly = true,
                        enabled = false,
                        singleLine = true,
                        maxLines = 1,
                        value = "Blue",
                        textStyle = MaterialTheme.typography.labelMedium.copy(textAlign = TextAlign.Center),
                        onValueChange = {}
                    )
                    BasicTextField(
                        modifier = Modifier
                            .width(70.dp)
                            .background(MaterialTheme.colorScheme.background.copy(0.05f))
                            .border(width = 1.dp, color = MaterialTheme.colorScheme.background)
                            .padding(vertical = 5.dp),
                        readOnly = true,
                        enabled = false,
                        singleLine = true,
                        maxLines = 1,
                        value = blueColor.toString(),
                        textStyle = MaterialTheme.typography.labelMedium.copy(textAlign = TextAlign.Center),
                        onValueChange = {}
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    drawSignatureViewModel.updateTypeExpanded(TypeExpanded.NONE)
                },
                text = stringResource(id = R.string.cancel),
                color = MaterialTheme.colorScheme.background,
                style = MaterialTheme.typography.labelMedium
            )

            Text(
                modifier = Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    drawSignatureViewModel.updateCurrentColor(colorShowing)
                    drawSignatureViewModel.updateTypeExpanded(TypeExpanded.NONE)
                },
                text = stringResource(id = R.string.done),
                color = MaterialTheme.colorScheme.background,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}