package com.phyothinzaraung.eng_mm_dictionary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phyothinzaraung.eng_mm_dictionary.data.model.Recent
import com.phyothinzaraung.eng_mm_dictionary.repository.IRecentRepository
import com.phyothinzaraung.eng_mm_dictionary.util.DefaultDispatcherProvider
import com.phyothinzaraung.eng_mm_dictionary.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentViewModel @Inject constructor(
    private val repository: IRecentRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    fun insertRecent(recent: Recent) {
        viewModelScope.launch(dispatcherProvider.io) {
            repository.insertRecent(recent = recent)
        }
    }

    fun clearAllRecent() {
        viewModelScope.launch(dispatcherProvider.io){
            repository.clearAllRecent()
        }
    }

    private val _recent = MutableStateFlow<List<Recent>>(emptyList())
    val recent: StateFlow<List<Recent>> = _recent

    fun getRecent() {
        viewModelScope.launch(dispatcherProvider.io) {
            repository.getRecent().collectLatest {
                _recent.value = it
            }
        }
    }
}