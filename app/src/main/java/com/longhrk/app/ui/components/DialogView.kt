package com.longhrk.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.longhrk.app.ui.components.model.ButtonContent

@Composable
fun DialogView(
    modifier: Modifier,
    title: String,
    content: String,
    buttonContent: ButtonContent
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )

        Divider(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
                .height(1.dp),
            color = MaterialTheme.colorScheme.primary,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 10.dp),
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Justify,
            fontWeight = FontWeight.Light
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                modifier = Modifier
                    .padding(end = 15.dp)
                    .align(Alignment.CenterEnd),
                onClick = buttonContent.onClick
            ) {
                Text(
                    text = buttonContent.textContent,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun DialogView(
    modifier: Modifier,
    title: String,
    content: String,
    buttonCancel: ButtonContent,
    buttonAgree: ButtonContent,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Start,
        )

        Divider(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
                .height(1.dp),
            color = MaterialTheme.colorScheme.primary,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 10.dp),
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Justify,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            OutlinedButton(
                onClick = buttonCancel.onClick
            ) {
                Text(
                    text = buttonCancel.textContent,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.width(15.dp))

            OutlinedButton(
                onClick = buttonAgree.onClick
            ) {
                Text(
                    text = buttonAgree.textContent,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}