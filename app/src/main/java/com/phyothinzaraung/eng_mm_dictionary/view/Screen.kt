
sealed class Screen(val route: String) {
    object SearchScreen : Screen("search")
    object DetailsScreen : Screen("details/{stripWord}")
    object FavoritesScreen : Screen("favorites")
    object RecentScreen: Screen("recent")
}