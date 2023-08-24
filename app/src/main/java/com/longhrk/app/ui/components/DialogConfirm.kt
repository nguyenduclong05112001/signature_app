package com.longhrk.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.longhrk.app.R
import com.longhrk.app.ui.components.model.ButtonContent
import com.longhrk.app.ui.components.model.DialogType

@Composable
fun DialogConfirm(
    type: DialogType,
    title: String = stringResource(id = R.string.loading),
    content: String = "",
    buttonContent: ButtonContent = ButtonContent(),
    buttonCancel: ButtonContent = ButtonContent(),
    buttonAgree: ButtonContent = ButtonContent(),
) {
    val localDensity = LocalDensity.current

    val widthDialog = remember {
        mutableStateOf(0.dp)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .onGloballyPositioned {
            widthDialog.value = with(localDensity) {
                (it.size.width / 100 * 90).toDp()
            }
        }
    ) {

        when (type) {
            DialogType.ONE_BUTTON -> {
                DialogView(
                    modifier = Modifier
                        .width(widthDialog.value)
                        .align(Alignment.Center)
                        .background(MaterialTheme.colorScheme.onBackground)
                        .padding(vertical = 10.dp),
                    title = title,
                    content = content,
                    buttonContent = buttonContent,
                )
            }

            DialogType.TWO_BUTTON -> {
                DialogView(
                    modifier = Modifier
                        .width(widthDialog.value)
                        .align(Alignment.Center)
                        .background(MaterialTheme.colorScheme.onBackground)
                        .padding(vertical = 10.dp),
                    title = title,
                    content = content,
                    buttonCancel = buttonCancel,
                    buttonAgree = buttonAgree,
                )
            }

            DialogType.LOADING -> {
                LoadingView(
                    modifier = Modifier
                        .width(widthDialog.value)
                        .align(Alignment.Center)
                        .background(MaterialTheme.colorScheme.onBackground)
                        .padding(vertical = 10.dp),
                    title = title
                )
            }
        }
    }
}