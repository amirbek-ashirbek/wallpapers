package com.example.wallpapers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.categories_screen.CategoriesScreen
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen.WallpapersScreen
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen.WallpapersViewModel
import com.example.wallpapers.navigation.CategoriesScreen
import com.example.wallpapers.navigation.WallpapersScreen
import com.example.wallpapers.ui.theme.WallpapersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
//		enableEdgeToEdge()
		super.onCreate(savedInstanceState)
		setContent {
			WallpapersTheme {
				val navController = rememberNavController()
				Surface(
					modifier = Modifier
						.fillMaxSize()
						.navigationBarsPadding()
				) {
					WallpapersNavHost(navController = navController)
				}
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
		startDestination = CategoriesScreen.route,
		modifier = modifier
	) {
		composable(route = CategoriesScreen.route) {
			CategoriesScreen(navController = navController)
		}
		composable(route = WallpapersScreen.routeWithArgs) {
			val viewModel = hiltViewModel<WallpapersViewModel>()
			val wallpapers = viewModel.wallpapers.collectAsLazyPagingItems()
			val uiState by viewModel.uiState.collectAsStateWithLifecycle()
			WallpapersScreen(
				uiState = uiState,
				wallpapers = wallpapers
			)
		}
	}
}