package com.longhrk.app.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.longhrk.app.R
import com.longhrk.app.ui.components.ButtonMain
import com.longhrk.app.ui.components.HeaderApp
import com.longhrk.app.ui.screen.draw.screen.BaseScreen

@Composable
fun HomeScreen(
    onSettingScreen: () -> Unit,
    onDrawSignature: () -> Unit,
    onBackPress: () -> Unit
) {
    val notifyBackOutApp = stringResource(id = R.string.notify_back_out_app)
    val context = LocalContext.current
    var backPressTime = 0L

    val backHandler = {
        if (backPressTime + 1500 > System.currentTimeMillis()) {
            onBackPress()
        } else {
            Toast.makeText(context, notifyBackOutApp, Toast.LENGTH_LONG).show()
        }
        backPressTime = System.currentTimeMillis()
    }

    val animationPresent by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.home_video_present)
    )

    val isPlaying by remember {
        mutableStateOf(true)
    }

    val progress by animateLottieCompositionAsState(
        composition = animationPresent,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying,
        restartOnPlay = false
    )

    BaseScreen(
        onBackHandler = backHandler,
        topBar = {
            HeaderApp(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(vertical = 10.dp, horizontal = 15.dp),
                icon = painterResource(id = R.drawable.ic_menu),
                onClick = onSettingScreen
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                composition = animationPresent,
                progress = { progress },
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp
                    )
                    .weight(1f)
            ) {
                ButtonMain(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 10.dp),
                    contentButton = stringResource(id = R.string.create),
                    iconButton = painterResource(id = R.drawable.ic_create)
                ) { onDrawSignature() }

                Spacer(modifier = Modifier.size(20.dp))

                ButtonMain(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 10.dp),
                    contentButton = stringResource(id = R.string.generator),
                    iconButton = painterResource(id = R.drawable.ic_generate)
                ) {}
            }
        }
    }
}
