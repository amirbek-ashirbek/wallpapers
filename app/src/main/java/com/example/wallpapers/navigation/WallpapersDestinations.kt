package com.example.wallpapers.navigation

interface WallpapersDestination {
	val route: String
}

object CategoriesScreen : WallpapersDestination {
	override val route = "categories_screen"
}

object WallpapersScreen : WallpapersDestination {
	override val route = "wallpapers_screen"
	const val routeWithArgs = "wallpapers_screen/{categoryId}"
}