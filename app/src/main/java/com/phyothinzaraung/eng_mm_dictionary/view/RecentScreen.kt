package com.phyothinzaraung.eng_mm_dictionary.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.phyothinzaraung.eng_mm_dictionary.data.Recent
import com.phyothinzaraung.eng_mm_dictionary.viewmodel.DictionaryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecentScreen(viewModel: DictionaryViewModel, navController: NavHostController) {

    val recent by viewModel.recent.collectAsState(emptyList())
    LaunchedEffect(key1 = recent){
        viewModel.getRecent()
        if (recent.size > 30){
            viewModel.clearAllRecent()
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, currentRoute = Screen.RecentScreen.route)
        }
    ) {innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "Recent",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                modifier = Modifier.padding(16.dp)
            )

            if (recent.isEmpty()){
                Text(
                    text = "No recent search available",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Gray
                    ),
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }else{
                LazyColumn {
                    items(recent){item: Recent ->
                        RecentItem(recent = item){
                            navController.navigate("details/${it.stripword}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RecentItem(recent: Recent, onItemClick: (Recent) -> Unit) {
    ListItem(item = recent, onItemClick = onItemClick) {
        Text(
            text = it.stripword ?: "",
            modifier = Modifier.padding(start = 16.dp),
            fontSize = 16.sp
        )
    }
}
