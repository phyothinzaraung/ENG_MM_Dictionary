package com.phyothinzaraung.eng_mm_dictionary.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phyothinzaraung.eng_mm_dictionary.data.Dictionary
import com.phyothinzaraung.eng_mm_dictionary.data.Favorite
import com.phyothinzaraung.eng_mm_dictionary.data.Recent
import com.phyothinzaraung.eng_mm_dictionary.repository.DictionaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DictionaryViewModel:  ViewModel() {

    private val _searchResults = MutableStateFlow<List<Dictionary>>(emptyList())
    val searchResults: StateFlow<List<Dictionary>> = _searchResults.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private lateinit var repository: DictionaryRepository

    fun initRepository(repo: DictionaryRepository) {
        repository = repo
    }

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

    fun insertFavorite(favorite: Favorite){
        viewModelScope.launch {
            repository.insertFavorite(favorite)
        }
    }

    fun deleteFavorite(favorite: Favorite){
        viewModelScope.launch {
            repository.deleteFavorite(favorite)
        }
    }

    private val _favorites = MutableStateFlow<List<Favorite>>(emptyList())
    val favorites: StateFlow<List<Favorite>> = _favorites.asStateFlow()

    fun getFavorites(){
        viewModelScope.launch {
            repository.getFavorites().collect{
                _favorites.value = it
            }
        }
    }

    fun insertRecent(recent: Recent){
        viewModelScope.launch {
            repository.insertRecent(recent = recent)
        }
    }

    fun clearAllRecent(){
        viewModelScope.launch {
            repository.clearAllRecent()
        }
    }

    private val _recent = MutableStateFlow<List<Recent>>(emptyList())
    val recent: StateFlow<List<Recent>> = _recent

    fun getRecent(){
        viewModelScope.launch{
            repository.getRecent().collect{
                _recent.value = it
            }
        }
    }
}