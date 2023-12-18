package com.phyothinzaraung.eng_mm_dictionary.viewmodel

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phyothinzaraung.eng_mm_dictionary.data.Dictionary
import com.phyothinzaraung.eng_mm_dictionary.repository.IDictionaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(private val repository: IDictionaryRepository) :
    ViewModel() {

    private val _searchResults = MutableStateFlow<List<Dictionary>>(emptyList())
    val searchResults: StateFlow<List<Dictionary>> = _searchResults.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var textToSpeech: TextToSpeech? = null

    fun searchWords(query: String) {
        Log.e(">>>> ", query)
        _isLoading.value = true
        viewModelScope.launch {
            repository.searchWords(query)
                .collectLatest { results ->
                    _searchResults.value = results
                    Log.e(">>>> RESULT ", "$query \t ${results.size}")

                    _isLoading.value = false
                }
        }
    }

    fun getDictionaryByStripWord(word: String): Flow<Dictionary?> {
        return repository.getDictionaryByStripWord(word)
    }

    fun textToSpeech(context: Context, text: String) {
        textToSpeech = TextToSpeech(context) {
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.let { textToSpeech ->
                    textToSpeech.setLanguage(Locale.US)
                    textToSpeech.setSpeechRate(1.0f)
                    textToSpeech.speak(
                        text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }
        }
    }
}