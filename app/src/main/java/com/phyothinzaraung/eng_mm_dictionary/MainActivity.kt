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
import com.phyothinzaraung.eng_mm_dictionary.data.DictionaryDatabase
import com.phyothinzaraung.eng_mm_dictionary.repository.DictionaryRepository
import com.phyothinzaraung.eng_mm_dictionary.ui.theme.CustomAppTheme
import com.phyothinzaraung.eng_mm_dictionary.ui.theme.ENG_MM_DictionaryTheme
import com.phyothinzaraung.eng_mm_dictionary.view.DetailsScreen
import com.phyothinzaraung.eng_mm_dictionary.view.FavoritesScreen
import com.phyothinzaraung.eng_mm_dictionary.view.RecentScreen
import com.phyothinzaraung.eng_mm_dictionary.view.Screen
import com.phyothinzaraung.eng_mm_dictionary.view.SearchScreen
import com.phyothinzaraung.eng_mm_dictionary.viewmodel.DictionaryViewModel

class MainActivity : ComponentActivity() {

    private val dictionaryViewModel: DictionaryViewModel by viewModels()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = DictionaryRepository(
            DictionaryDatabase.getInstance(this).dictionaryDao(),
            DictionaryDatabase.getInstance(this).favoriteDao(),
            DictionaryDatabase.getInstance(this).recentDao()
        )
        dictionaryViewModel.initRepository(repository)
        
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
                DetailsScreen(stripWord = stripWord ?: "", viewModel = dictionaryViewModel, navController)
            }
            composable(Screen.FavoritesScreen.route){
                FavoritesScreen(viewModel = dictionaryViewModel,navController)
            }
            composable(Screen.RecentScreen.route){
                RecentScreen(viewModel = dictionaryViewModel, navController)
            }
        }
    }
}




