package com.longhrk.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun ButtonMain(
    modifier: Modifier,
    contentButton: String,
    iconButton: Painter,
    onClickButton: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClickButton,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = iconButton,
                contentDescription = null
            )

            Text(
                text = contentButton,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}