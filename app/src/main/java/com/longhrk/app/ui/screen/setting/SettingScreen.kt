package com.longhrk.app.ui.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.longhrk.app.R
import com.longhrk.app.ui.components.HeaderApp
import com.longhrk.app.ui.components.OptionSetting
import com.longhrk.app.ui.extensions.getVersionName

@Composable
fun SettingScreen(onBackScreen: () -> Unit) {
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderApp(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onBackground.copy(0.1f))
                .padding(vertical = 10.dp, horizontal = 15.dp),
            icon = painterResource(id = R.drawable.ic_arrow_back),
            title = stringResource(id = R.string.setting)
        ) { onBackScreen() }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            OptionSetting(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    }
                    .background(MaterialTheme.colorScheme.onBackground.copy(0.1f))
                    .padding(vertical = 10.dp, horizontal = 15.dp),
                textContent = stringResource(id = R.string.terms_of_use)
            )

            OptionSetting(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    }
                    .background(MaterialTheme.colorScheme.onBackground.copy(0.1f))
                    .padding(vertical = 10.dp, horizontal = 15.dp),
                textContent = stringResource(id = R.string.privacy_policy)
            )

            OptionSetting(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    }
                    .background(MaterialTheme.colorScheme.onBackground.copy(0.1f))
                    .padding(vertical = 10.dp, horizontal = 15.dp),
                textContent = stringResource(id = R.string.help)
            )

            Text(
                modifier = Modifier.padding(15.dp),
                text = stringResource(id = R.string.let_me_know_how_i_can_do_better),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Light)
            )

            OptionSetting(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    }
                    .background(MaterialTheme.colorScheme.onBackground.copy(0.1f))
                    .padding(vertical = 10.dp, horizontal = 15.dp),
                textContent = stringResource(id = R.string.feedback)
            )

            Text(
                modifier = Modifier.padding(15.dp),
                text = stringResource(id = R.string.follow_me),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Light)
            )


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.version) + ":  " + context.getVersionName(),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
            )
        }
    }
}