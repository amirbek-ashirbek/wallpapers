package com.example.wallpapers.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavHostController) {
	val screens = listOf(
		BottomBarScreen.Categories,
		BottomBarScreen.Favourites
	)
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentDestination = navBackStackEntry?.destination
	val bottomBarDestination = screens.any { it.route == currentDestination?.route }

	if (bottomBarDestination) {
		NavigationBar(
			modifier = Modifier
				.clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
		)  {
			screens.forEach { screen ->
				AddItem(
					screen = screen,
					currentDestination = currentDestination,
					navController = navController
				)
			}
		}
	}
}

@Composable
fun RowScope.AddItem(
	screen: BottomBarScreen,
	currentDestination: NavDestination?,
	navController: NavHostController
) {

	val selected = currentDestination?.hierarchy?.any {it.route == screen.route} == true

	NavigationBarItem(
		selected = selected,
		onClick = {
			navController.navigate(screen.route) {
				launchSingleTop = true
				restoreState = true
			} },
		icon = {
			Icon(
				painter = painterResource(id = screen.icon),
				contentDescription = null
			)
		},
		label = {
			Text(
				text = stringResource(id = screen.label)
			)
		}
	)
}