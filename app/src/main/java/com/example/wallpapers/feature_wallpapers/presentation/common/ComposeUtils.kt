package com.example.wallpapers.feature_wallpapers.presentation.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
	clickable(indication = null,
		interactionSource = remember { MutableInteractionSource() }) {
		onClick()
	}
}

@Composable
fun ToggleVisibilityComponent(
	visible: Boolean,
	content: @Composable () -> Unit,
	modifier: Modifier = Modifier
) {
	AnimatedVisibility(
		enter = expandVertically(),
		exit = shrinkVertically(),
		visible = visible,
		modifier = modifier
	) {
		content()
	}
}