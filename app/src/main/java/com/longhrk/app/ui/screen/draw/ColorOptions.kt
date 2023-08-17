package com.longhrk.app.ui.screen.draw

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.longhrk.app.R
import com.longhrk.app.ui.extensions.toColor
import com.longhrk.app.ui.viewmodel.drag.DrawSignatureViewModel
import com.longhrk.app.ui.viewmodel.drag.data.DefaultColors.colors
import com.longhrk.app.ui.viewmodel.drag.model.TypeExpanded

@Composable
fun ColorOptions(
    modifier: Modifier,
    drawSignatureViewModel: DrawSignatureViewModel
) {
    val interactionSource = remember { MutableInteractionSource() }

    var currentChecked by remember {
        mutableStateOf("")
    }

    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = stringResource(id = R.string.color),
            color = MaterialTheme.colorScheme.background,
            style = MaterialTheme.typography.labelMedium
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(horizontal = 5.dp)
        ) {
            item {
                Icon(
                    modifier = Modifier
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            drawSignatureViewModel.updateTypeExpanded(TypeExpanded.COLOR_PICKER)
                        }
                        .size(32.dp),
                    painter = painterResource(id = R.drawable.ic_add),
                    tint = MaterialTheme.colorScheme.background,
                    contentDescription = null
                )
            }

            items(colors) {
                Box(modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        currentChecked = it
                    }) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.drawable.ic_color),
                        tint = it.toColor(),
                        contentDescription = it
                    )

                    if (currentChecked == it) {
                        Icon(
                            modifier = Modifier
                                .size(16.dp)
                                .align(Alignment.Center),
                            painter = painterResource(id = R.drawable.ic_checked),
                            tint = MaterialTheme.colorScheme.background,
                            contentDescription = it
                        )
                    }
                }
            }
        }
    }
}