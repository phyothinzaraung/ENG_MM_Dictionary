package com.phyothinzaraung.eng_mm_dictionary.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationBar(navController: NavHostController, currentRoute: String?) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NavigationBarItem(
                selected = currentRoute == Screen.SearchScreen.route,
                onClick = { navController.navigate(Screen.SearchScreen.route){
                    popUpTo(navController.graph.startDestinationId){
                        saveState = true
                    }
                    launchSingleTop = true
                }
                          },
                icon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
                label = { Text(text = "Search") }
            )

            NavigationBarItem(
                selected = currentRoute == Screen.FavoritesScreen.route,
                onClick = { navController.navigate(Screen.FavoritesScreen.route){
                    popUpTo(navController.graph.startDestinationId){
                        saveState = true
                    }
                    launchSingleTop = true
                }
                          },
                icon = { Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null) },
                label = { Text(text = "Favorites") }
            )

            NavigationBarItem(
                selected = currentRoute == Screen.RecentScreen.route,
                onClick = { navController.navigate(Screen.RecentScreen.route){
                    popUpTo(navController.graph.startDestinationId){
                        saveState = true
                    }
                    launchSingleTop = true
                } },
                icon = { Icon(imageVector = Icons.Default.List, contentDescription = null) },
                label = { Text(text = "Recent") }
            )
        }
    }
}
