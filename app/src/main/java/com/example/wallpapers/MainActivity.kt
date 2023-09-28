package com.example.wallpapers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.wallpapers.destinations.CategoriesScreenDestination
import com.example.wallpapers.destinations.FavouritesScreenDestination
import com.example.wallpapers.destinations.SingleWallpaperScreenDestination
import com.example.wallpapers.destinations.WallpapersScreenDestination
import com.example.wallpapers.navigation.BottomBar
import com.example.wallpapers.ui.theme.WallpapersTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.utils.currentDestinationAsState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	private val mainViewModel: MainViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		enableEdgeToEdge()

		super.onCreate(savedInstanceState)

		setContent {
			val isDarkTheme by mainViewModel.isDarkTheme.collectAsStateWithLifecycle(initialValue = isSystemInDarkTheme())
			WallpapersTheme(
				darkTheme = isDarkTheme ?: isSystemInDarkTheme()
			) {
				Box(
					modifier = Modifier
						.fillMaxSize()
				) {
					WallpapersApp()
				}
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