package com.longhrk.app.ui.screen

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.longhrk.app.R
import com.longhrk.app.core.GetDimension

@Composable
fun HomeScreen(
    onNextScreen: () -> Unit,
    onBackPress: () -> Unit
) {
    val notifyBackOutApp = stringResource(id = R.string.notify_back_out_app)
    val context = LocalContext.current
    var backPressTime = 0L

    BackHandler {
        if (backPressTime + 1500 > System.currentTimeMillis()) {
            onBackPress()
        } else {
            Toast.makeText(context, notifyBackOutApp, Toast.LENGTH_LONG).show()
        }
        backPressTime = System.currentTimeMillis()
    }

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.home_video_present)
    )

    val isPlaying by remember {
        mutableStateOf(true)
    }

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying,
        restartOnPlay = false
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress }
        )
    }
}
