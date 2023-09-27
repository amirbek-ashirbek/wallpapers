package com.example.wallpapers.feature_wallpapers.presentation.settings_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
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
import com.example.wallpapers.R
import com.example.wallpapers.feature_wallpapers.presentation.common.Header
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SettingsScreen(
) {

	SettingsScreenContent()

}

@Composable
fun SettingsScreenContent() {

	Surface(
		modifier = Modifier
			.fillMaxSize()
	) {
		Column {
			Header(
				text = stringResource(id = R.string.settings),
				needsBackButton = false,
				onBackClicked = { }
			)
			Spacer(modifier = Modifier.height(32.dp))
			DarkModeSwitch(
				modifier = Modifier.padding(horizontal = 24.dp)
			)

		}
	}
}

@Composable
fun DarkModeSwitch(
	modifier: Modifier = Modifier
) {

	var checked by remember { mutableStateOf(true) }

	Row(
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween,
		modifier = modifier.fillMaxWidth()
	) {
		Icon(
			painter = painterResource(id = R.drawable.icon_moon),
			contentDescription = null
		)
		Spacer(modifier = Modifier.width(12.dp))
		Text(
			text = "Dark mode"
		)
		Spacer(modifier = Modifier.weight(1f))
		Switch(
			checked = checked,
			onCheckedChange = { checked = it },
		)
	}
}