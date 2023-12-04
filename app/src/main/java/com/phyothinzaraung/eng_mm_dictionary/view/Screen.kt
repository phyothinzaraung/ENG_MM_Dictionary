
sealed class Screen(val route: String) {
    object SearchScreen : Screen("search")
    object DetailsScreen : Screen("details/{id}")
}