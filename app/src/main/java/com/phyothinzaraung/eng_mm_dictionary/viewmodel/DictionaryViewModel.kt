package com.phyothinzaraung.eng_mm_dictionary.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phyothinzaraung.eng_mm_dictionary.data.Dictionary
import com.phyothinzaraung.eng_mm_dictionary.repository.DictionaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(private val repository: DictionaryRepository):  ViewModel() {

    private val _searchResults = MutableStateFlow<List<Dictionary>>(emptyList())
    val searchResults: StateFlow<List<Dictionary>> = _searchResults.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun searchWords(query: String) {
        _isLoading.value = true
        viewModelScope.launch {
            repository.searchWords(query)
                .collect { results ->
                    _searchResults.value = results
                    _isLoading.value = false
                    Log.d("DictionaryViewModel", "searchWords: ${results.size}")
                }
        }
    }

    fun getDictionaryByStripWord(word: String): Flow<Dictionary?>{
        return repository.getDictionaryByStripWord(word)
    }
}