package com.longhrk.app.ui.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.longhrk.app.R
import com.longhrk.app.ui.components.HeaderApp
import com.longhrk.app.ui.components.OptionSetting

@Composable
fun SettingScreen(onBackScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderApp(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            icon = painterResource(id = R.drawable.ic_arrow_back),
            title = stringResource(id = R.string.setting)
        ) { onBackScreen() }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            OptionSetting(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .clickable {

                    }
                    .background(MaterialTheme.colorScheme.surface), textContent = "Here test"
            )
        }
    }
}