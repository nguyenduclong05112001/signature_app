package com.longhrk.app.ui.screen.draw

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.ImageColorPicker
import com.github.skydoves.colorpicker.compose.PaletteContentScale
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.longhrk.app.R
import com.longhrk.app.ui.extensions.toColor
import com.longhrk.app.ui.viewmodel.drag.DrawSignatureViewModel

@Composable
fun ColorPickerOption(
    modifier: Modifier,
    drawSignatureViewModel: DrawSignatureViewModel
) {
    val controller = rememberColorPickerController()

    val currentDrawColor by drawSignatureViewModel.currentDrawColor.collectAsState()

    var colorShowing by remember {
        mutableStateOf(currentDrawColor)
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
                .height(170.dp)
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
                    if (colorEnvelope.fromUser) {
                        colorShowing = "#${colorEnvelope.hexCode}"
                    }
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(colorShowing.toColor())
            )

            Text(
                text = colorShowing,
                color = MaterialTheme.colorScheme.background,
                style = MaterialTheme.typography.labelMedium
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.cancel),
                color = MaterialTheme.colorScheme.background,
                style = MaterialTheme.typography.labelMedium
            )

            Text(
                text = stringResource(id = R.string.done),
                color = MaterialTheme.colorScheme.background,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}