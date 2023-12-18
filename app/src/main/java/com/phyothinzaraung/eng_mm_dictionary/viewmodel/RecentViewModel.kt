package com.phyothinzaraung.eng_mm_dictionary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phyothinzaraung.eng_mm_dictionary.data.Recent
import com.phyothinzaraung.eng_mm_dictionary.repository.IRecentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentViewModel @Inject constructor(private val repository: IRecentRepository): ViewModel() {

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