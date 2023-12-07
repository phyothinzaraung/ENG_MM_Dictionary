package com.phyothinzaraung.eng_mm_dictionary.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.phyothinzaraung.eng_mm_dictionary.data.Dictionary
import com.phyothinzaraung.eng_mm_dictionary.data.Favorite
import com.phyothinzaraung.eng_mm_dictionary.viewmodel.DictionaryViewModel
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(viewModel: DictionaryViewModel, navController: NavHostController) {

    val favorites by viewModel.favorites.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = Unit){
        viewModel.getFavorites()
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, currentRoute = Screen.FavoritesScreen.route)
        }
    ) {innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "Favorites",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                modifier = Modifier.padding(16.dp)
            )
            
            LazyColumn {
                items(favorites){item: Favorite ->  
                    FavoriteItem(favorite = item){
                        navController.navigate("details/${item.stripword}")
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteItem(favorite: Favorite, onItemClick: (Favorite) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick }
    ) {
        Text(
            text = favorite.stripword ?: "",
            modifier = Modifier.padding(16.dp)
        )
    }
}
