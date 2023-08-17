package com.longhrk.app.ui.screen.draw

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.longhrk.app.ui.viewmodel.drag.DrawSignatureViewModel
import com.longhrk.app.ui.viewmodel.drag.model.TypeExpanded
import io.ak1.drawbox.DrawBox
import io.ak1.drawbox.rememberDrawController

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ContentDrawScreen(
    modifier: Modifier,
    drawSignatureViewModel: DrawSignatureViewModel
) {
    val context = LocalContext.current
    val controller = rememberDrawController()

    ConstraintLayout(modifier = modifier) {
        val (drawContent, drawOptions) = createRefs()

        DrawBox(
            modifier = Modifier
                .constrainAs(drawContent) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(drawOptions.top)

                    width = Dimension.matchParent
                    height = Dimension.fillToConstraints
                }
                .background(MaterialTheme.colorScheme.background),
            drawController = controller,
            bitmapCallback = { imageBitmap, _ ->
                imageBitmap?.let {
                    drawSignatureViewModel.saveInStorage(
                        bitmap = it.asAndroidBitmap(),
                        context = context
                    )
                }
            }
        )

        DrawOptions(
            drawSignatureViewModel = drawSignatureViewModel,
            modifier = Modifier
                .constrainAs(drawOptions) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                    width = Dimension.matchParent
                    height = Dimension.wrapContent
                }
                .background(MaterialTheme.colorScheme.onBackground)
                .padding(vertical = 10.dp),
            onSave = { controller.saveBitmap() },
            onUnDo = { controller.unDo() },
            onReDo = { controller.reDo() },
            onReset = { controller.reset() },
            onColor = { drawSignatureViewModel.updateTypeExpanded(TypeExpanded.COLOR) },
            onResize = { drawSignatureViewModel.updateTypeExpanded(TypeExpanded.SIZE) },
        )
    }
}