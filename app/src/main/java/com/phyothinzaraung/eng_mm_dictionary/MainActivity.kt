package com.phyothinzaraung.eng_mm_dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.phyothinzaraung.eng_mm_dictionary.data.DictionaryDatabase
import com.phyothinzaraung.eng_mm_dictionary.repository.DictionaryRepository
import com.phyothinzaraung.eng_mm_dictionary.ui.theme.CustomAppTheme
import com.phyothinzaraung.eng_mm_dictionary.ui.theme.ENG_MM_DictionaryTheme
import com.phyothinzaraung.eng_mm_dictionary.view.SearchScreen
import com.phyothinzaraung.eng_mm_dictionary.viewmodel.DictionaryViewModel

class MainActivity : ComponentActivity() {
    private val dictionaryViewModel: DictionaryViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = DictionaryRepository(DictionaryDatabase.getInstance(this).dictionaryDao())
        dictionaryViewModel.initRepository(repository)
        
        setContent {
            ENG_MM_DictionaryTheme {
                CustomAppTheme {
                    SearchScreen(viewModel = dictionaryViewModel)
                }

            }
        }
    }
}

