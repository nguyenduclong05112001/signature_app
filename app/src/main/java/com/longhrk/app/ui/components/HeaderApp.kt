package com.longhrk.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun HeaderApp(
    modifier: Modifier,
    icon: Painter,
    title: String = "",
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            modifier = Modifier
                .size(
                    if (title.trim().isEmpty()) 32.dp else 24.dp
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onClick()
                },
            painter = icon,
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = null
        )

        if (title.trim().isNotEmpty()) {
            Spacer(modifier = Modifier.size(15.dp))

            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}