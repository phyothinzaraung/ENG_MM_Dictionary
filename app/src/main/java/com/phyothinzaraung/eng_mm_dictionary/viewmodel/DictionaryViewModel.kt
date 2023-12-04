package com.phyothinzaraung.eng_mm_dictionary.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phyothinzaraung.eng_mm_dictionary.data.Dictionary
import com.phyothinzaraung.eng_mm_dictionary.repository.DictionaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DictionaryViewModel:  ViewModel() {

    private val _searchResults = MutableStateFlow<List<Dictionary>>(emptyList())
    val searchResults: StateFlow<List<Dictionary>> = _searchResults.asStateFlow()

    private lateinit var repository: DictionaryRepository

    fun initRepository(repo: DictionaryRepository) {
        repository = repo
    }

    fun searchWords(query: String) {
        viewModelScope.launch {
            repository.searchWords(query)
                .collect { results ->
                    _searchResults.value = results
                    Log.d("DictionaryViewModel", "searchWords: ${results.size}")
                }
        }
    }

    fun getDictionaryById(id: Long): Flow<Dictionary?>{
        return repository.getDictionaryById(id)
    }
}