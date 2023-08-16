package com.longhrk.app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.longhrk.app.R
import io.ak1.drawbox.DrawBox
import io.ak1.drawbox.rememberDrawController

@Composable
fun DrawSignatureScreen() {
    val controller = rememberDrawController()

    var isShowAdvance by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DrawBox(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(MaterialTheme.colorScheme.background),
            drawController = controller,
            bitmapCallback = { imageBitmap, throwable ->

            }
        )

        if (isShowAdvance) {
            ColorOption(modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onBackground)
                .padding(all = 5.dp),
                onAction = {})
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onBackground)
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(modifier = Modifier.weight(1f)) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_save),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
            }

            Box(modifier = Modifier
                .weight(1f)
                .padding(7.dp)
                .clickable {
                    controller.unDo()
                }
            ) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_undo),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
            }

            Box(modifier = Modifier
                .weight(1f)
                .padding(7.dp)
                .clickable {
                    controller.reDo()
                }) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_redo),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
            }

            Box(modifier = Modifier
                .weight(1f)
                .padding(7.dp)
                .clickable {
                    isShowAdvance = !isShowAdvance
                }) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_color),
                    tint = MaterialTheme.colorScheme.background,
                    contentDescription = null
                )
            }

            Box(modifier = Modifier
                .weight(1f)
                .padding(7.dp)
                .clickable {
                    controller.reset()
                }) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_restart),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
            }

            Box(modifier = Modifier
                .weight(1f)
                .padding(7.dp)
                .clickable {

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

@Composable
fun ColorOption(
    modifier: Modifier,
    onAction: (TypeExpanded) -> Unit
) {
    var currentChecked by remember {
        mutableStateOf("")
    }

    val colors = arrayListOf(
        "#C0C0C0",
        "#808080",
        "#000000",
        "#FF0000",
        "#800000",
        "#FFFF00",
        "#808000",
        "#00FF00",
        "#008000",
        "#00FFFF",
        "#008080",
        "#0000FF",
        "#000080",
        "#FF00FF",
        "#800080"
    )

    Column(modifier = modifier) {
        Text(
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
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.ic_add),
                    tint = MaterialTheme.colorScheme.background,
                    contentDescription = null
                )
            }

            items(colors) {
                Box(modifier = Modifier.padding(horizontal = 5.dp).clickable {
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

fun String.toColor() = Color(android.graphics.Color.parseColor(this))

enum class TypeExpanded {
    COLOR, SIZE, NONE
}

@Preview(showBackground = true)
@Composable
fun DrawSignatureScreenPreview() {
    DrawSignatureScreen()
}

@Preview(showBackground = true)
@Composable
fun ColorOptionPreview() {
    ColorOption(modifier = Modifier.fillMaxWidth(), {

    })
}