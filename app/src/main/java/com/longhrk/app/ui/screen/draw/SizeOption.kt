package com.longhrk.app.ui.screen.draw

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.longhrk.app.R
import com.longhrk.app.ui.extensions.toColor
import com.longhrk.app.ui.viewmodel.drag.DrawSignatureViewModel

@Composable
fun SizeOption(
    modifier: Modifier,
    drawSignatureViewModel: DrawSignatureViewModel
) {
    val currentColor by drawSignatureViewModel.currentDrawColor.collectAsState()
    val currentWidth by drawSignatureViewModel.currentStrokeWidth.collectAsState()

    var sliderValues by remember {
        mutableFloatStateOf(currentWidth)
    }

    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = stringResource(id = R.string.draw_size),
            color = MaterialTheme.colorScheme.background,
            style = MaterialTheme.typography.labelMedium
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 5.dp),
        ) {
            Slider(
                value = sliderValues,
                onValueChange = {
                    sliderValues = it
                    drawSignatureViewModel.updateCurrentStrokeWidth(it)
                },
                colors = SliderDefaults.colors(
                    thumbColor = currentColor.toColor()
                ),
                valueRange = 1f..100f
            )
        }
    }
}