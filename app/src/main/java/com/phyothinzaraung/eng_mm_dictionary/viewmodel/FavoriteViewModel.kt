package com.phyothinzaraung.eng_mm_dictionary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phyothinzaraung.eng_mm_dictionary.data.model.Favorite
import com.phyothinzaraung.eng_mm_dictionary.repository.IFavoriteRepository
import com.phyothinzaraung.eng_mm_dictionary.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: IFavoriteRepository,
    private val dispatcherProvider: DispatcherProvider
) :
    ViewModel() {
    fun insertFavorite(favorite: Favorite) {
        viewModelScope.launch(dispatcherProvider.io) {
            repository.insertFavorite(favorite)
        }
    }

    fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch(dispatcherProvider.io) {
            repository.deleteFavorite(favorite)
        }
    }

    private val _favorites = MutableStateFlow<List<Favorite>>(emptyList())
    val favorites: StateFlow<List<Favorite>> = _favorites.asStateFlow()

    fun getFavorites() {
        viewModelScope.launch(dispatcherProvider.io) {
            repository.getFavorites().collectLatest {
                _favorites.value = it
            }
        }
    }
}