package com.longhrk.app.ui.screen.draw

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.longhrk.app.R
import com.longhrk.app.ui.viewmodel.drag.DrawSignatureViewModel

@Composable
fun ColorPickerOption(
    modifier: Modifier,
    drawSignatureViewModel: DrawSignatureViewModel
) {
    val context = LocalContext.current

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
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, bottom = 20.dp),
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