package com.example.wallpapers.navigation

interface WallpapersDestination {
	val route: String
}

object WallpapersScreen : WallpapersDestination {
	override val route = "wallpapers_screen"
}