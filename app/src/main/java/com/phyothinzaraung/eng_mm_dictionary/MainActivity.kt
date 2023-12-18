package com.phyothinzaraung.eng_mm_dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.phyothinzaraung.eng_mm_dictionary.ui.theme.CustomAppTheme
import com.phyothinzaraung.eng_mm_dictionary.ui.theme.ENG_MM_DictionaryTheme
import com.phyothinzaraung.eng_mm_dictionary.view.DetailsScreen
import com.phyothinzaraung.eng_mm_dictionary.view.FavoritesScreen
import com.phyothinzaraung.eng_mm_dictionary.view.RecentScreen
import com.phyothinzaraung.eng_mm_dictionary.view.Screen
import com.phyothinzaraung.eng_mm_dictionary.view.SearchScreen
import com.phyothinzaraung.eng_mm_dictionary.viewmodel.DictionaryViewModel
import com.phyothinzaraung.eng_mm_dictionary.viewmodel.FavoriteViewModel
import com.phyothinzaraung.eng_mm_dictionary.viewmodel.RecentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val dictionaryViewModel: DictionaryViewModel by viewModels()
    private val recentViewModel: RecentViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ENG_MM_DictionaryTheme {
                CustomAppTheme {
                    navController = rememberNavController()
                    NavGraph(navController = navController)
                }

            }
        }
    }

    @Composable
    fun NavGraph(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = Screen.SearchScreen.route
        ) {
            composable(Screen.SearchScreen.route) {
                SearchScreen(navController = navController, viewModel = dictionaryViewModel)
            }
            composable(
                route = Screen.DetailsScreen.route,
                arguments = listOf(navArgument("stripWord") { type = NavType.StringType })
            ) { backStackEntry ->
                val stripWord = backStackEntry.arguments?.getString("stripWord")
                DetailsScreen(
                    stripWord = stripWord ?: "",
                    dictionaryViewModel = dictionaryViewModel,
                    favoriteViewModel = favoriteViewModel,
                    recentViewModel = recentViewModel,
                    navController = navController
                )
            }
            composable(Screen.FavoritesScreen.route) {
                FavoritesScreen(viewModel = favoriteViewModel, navController)
            }
            composable(Screen.RecentScreen.route) {
                RecentScreen(viewModel = recentViewModel, navController)
            }
        }
    }
}




