package com.example.wallpapers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.NavGraphs
import com.example.wallpapers.ui.theme.WallpapersTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
//		enableEdgeToEdge()
		super.onCreate(savedInstanceState)
		setContent {
			WallpapersTheme {
				DestinationsNavHost(navGraph = NavGraphs.root)
			}
		}
	}
}