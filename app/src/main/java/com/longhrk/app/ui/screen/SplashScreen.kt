package com.longhrk.app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import kotlin.math.max
import com.longhrk.app.R as resApp

@Composable
fun SplashScreen(onNextScreen: () -> Unit) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(resApp.raw.animation_llad8t9j)
    )

    var isPlaying by remember {
        mutableStateOf(true)
    }

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying,
        restartOnPlay = false
    )

    LaunchedEffect(Unit) {
        delay(2000)
        isPlaying = false
    }

    LaunchedEffect(isPlaying) {
        if (!isPlaying) {
            onNextScreen()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LottieAnimation(
            modifier = Modifier.align(Alignment.Center),
            composition = composition,
            progress = { progress },
        )
    }
}