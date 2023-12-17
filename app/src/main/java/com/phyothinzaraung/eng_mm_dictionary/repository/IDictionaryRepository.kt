package com.phyothinzaraung.eng_mm_dictionary.repository

import com.phyothinzaraung.eng_mm_dictionary.data.Dictionary
import com.phyothinzaraung.eng_mm_dictionary.data.Favorite
import com.phyothinzaraung.eng_mm_dictionary.data.Recent
import kotlinx.coroutines.flow.Flow

interface IDictionaryRepository {

    fun searchWords(searchQuery: String): Flow<List<Dictionary>>

    fun getDictionaryByStripWord(word: String): Flow<Dictionary?>

    suspend fun insertFavorite(favorite: Favorite)

    suspend fun deleteFavorite(favorite: Favorite)

    fun getFavorites(): Flow<List<Favorite>>

    suspend fun insertRecent(recent: Recent)

    fun getRecent(): Flow<List<Recent>>

    suspend fun clearAllRecent()
}