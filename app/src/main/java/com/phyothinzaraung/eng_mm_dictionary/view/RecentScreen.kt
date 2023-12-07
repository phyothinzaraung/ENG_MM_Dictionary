package com.phyothinzaraung.eng_mm_dictionary.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecentScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, currentRoute = Screen.RecentScreen.route)
        }
    ) {innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(text = "Recent")
        }
    }
}