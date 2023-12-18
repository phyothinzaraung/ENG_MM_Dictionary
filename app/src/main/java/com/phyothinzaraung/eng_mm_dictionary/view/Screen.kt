package com.phyothinzaraung.eng_mm_dictionary.view

sealed class Screen(val route: String) {
    data object SearchScreen : Screen("search")
    data object DetailsScreen : Screen("details/{stripWord}")
    data object FavoritesScreen : Screen("favorites")
    data object RecentScreen : Screen("recent")
}