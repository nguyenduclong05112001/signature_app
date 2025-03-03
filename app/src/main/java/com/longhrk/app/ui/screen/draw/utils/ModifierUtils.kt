package com.longhrk.app.ui.screen.draw.utils

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import com.longhrk.app.ui.screen.draw.utils.ModifierUtils.MultipleEventsCutter
import com.longhrk.app.ui.screen.draw.utils.ModifierUtils.get

object ModifierUtils {
    internal fun interface MultipleEventsCutter {
        fun processEvent(event: () -> Unit)

        companion object
    }

    internal fun MultipleEventsCutter.Companion.get(): MultipleEventsCutter =
        MultipleEventsCutterImpl()

    private class MultipleEventsCutterImpl : MultipleEventsCutter {
        val delayTime = 300L
        private val now: Long
            get() = System.currentTimeMillis()

        private var lastEventTimeMs: Long = 0

        override fun processEvent(event: () -> Unit) {
            if (now - lastEventTimeMs >= delayTime) {
                event.invoke()
            }
            lastEventTimeMs = now
        }
    }
}

fun Modifier.clickableSingle(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    indicated: Boolean = true,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    val multipleEventsCutter = remember { MultipleEventsCutter.get() }
    this.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = { multipleEventsCutter.processEvent { onClick() } },
        role = role,
        indication = if (indicated) LocalIndication.current else null,
        interactionSource = remember { MutableInteractionSource() }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.clickOutSideToHideKeyBoard() = composed {
    val localSoftwareKeyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    clickableSingle(indicated = false) {
        localSoftwareKeyboardController?.hide()
        focusManager.clearFocus()
    }
}

