package com.example.wallpapers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.NavGraphs
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.destinations.CategoriesScreenDestination
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.destinations.FavouritesScreenDestination
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.destinations.SingleWallpaperScreenDestination
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.destinations.WallpapersScreenDestination
import com.example.wallpapers.navigation.BottomBar
import com.example.wallpapers.ui.theme.WallpapersTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.utils.currentDestinationAsState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
//		enableEdgeToEdge()
		super.onCreate(savedInstanceState)
		setContent {
			WallpapersTheme {
				WallpapersApp()
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpapersApp(
	modifier: Modifier = Modifier
) {

	val bottomBarState = rememberSaveable { mutableStateOf(false) }
	val navController = rememberNavController()
	val currentDestination by navController.currentDestinationAsState()

	when (currentDestination) {
		CategoriesScreenDestination -> {
			bottomBarState.value = true
		}
		WallpapersScreenDestination -> {
			bottomBarState.value = true
		}
		FavouritesScreenDestination -> {
			bottomBarState.value = true
		}
		SingleWallpaperScreenDestination -> {
			bottomBarState.value = false
		}
	}

	Scaffold(
		bottomBar = {
			AnimatedVisibility(
				visible = bottomBarState.value,
				enter = expandVertically(),
				exit = shrinkVertically()
			) {
				BottomBar(navController = navController)
			}
		},
		content = { padding ->
			DestinationsNavHost(
				navController = navController,
				navGraph = NavGraphs.root,
				modifier = Modifier
					.padding(padding)
			)
		}
	)

}