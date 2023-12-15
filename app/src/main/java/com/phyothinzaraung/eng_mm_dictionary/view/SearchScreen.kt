package com.phyothinzaraung.eng_mm_dictionary.view

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.phyothinzaraung.eng_mm_dictionary.data.Dictionary
import com.phyothinzaraung.eng_mm_dictionary.viewmodel.DictionaryViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: DictionaryViewModel, navController: NavHostController) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val searchResults by viewModel.searchResults.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val resultLauncher: ActivityResultLauncher<Intent> =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val speechResult: ArrayList<String>? = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                // Assuming you want the first recognized phrase if available, otherwise default to "apple"
                searchQuery = speechResult?.getOrNull(0) ?: "apple"
            }
        }

    fun launchSpeechToText() {
        val speechToTextIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechToTextIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        speechToTextIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        speechToTextIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something!!!")
        resultLauncher.launch(speechToTextIntent)
    }

    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                      launchSpeechToText()
            },
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_btn_speak_now),
                contentDescription = "speak")
        }
    }

    LaunchedEffect(key1 = searchQuery){
        if(searchQuery.isNotBlank()) {
            viewModel.searchWords(searchQuery)
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, currentRoute = currentRoute)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                trailingIcon = trailingIconView
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(36.dp)
                    )
                }
            }else{
                LazyColumn {
                    items(searchResults) { dictionary ->
                        DictionaryItem(dictionary = dictionary){
                            navController.navigate("details/${dictionary.stripWord}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DictionaryItem(dictionary: Dictionary, onItemClick: (Dictionary) -> Unit) {
    ListItem(item = dictionary, onItemClick = onItemClick) {
        Text(
            text = it.stripWord ?: "",
            modifier = Modifier.padding(start = 16.dp),
            fontSize = 16.sp
            )
    }
}
