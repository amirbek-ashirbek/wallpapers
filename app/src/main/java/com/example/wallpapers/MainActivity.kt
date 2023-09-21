package com.example.wallpapers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.WallpapersScreen
import com.example.wallpapers.navigation.WallpapersScreen
import com.example.wallpapers.ui.theme.WallpapersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			WallpapersTheme {
				val navController = rememberNavController()
				WallpapersNavHost(navController = navController)
			}
		}
	}
}

@Composable
fun WallpapersNavHost(
	navController: NavHostController,
	modifier: Modifier = Modifier
) {
	NavHost(
		navController = navController,
		startDestination = WallpapersScreen.route,
		modifier = modifier
	) {
		composable(route = WallpapersScreen.route) {
			WallpapersScreen()
		}
	}
}