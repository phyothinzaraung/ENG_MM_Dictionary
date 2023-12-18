package com.phyothinzaraung.eng_mm_dictionary.repository

import com.phyothinzaraung.eng_mm_dictionary.data.Dictionary
import kotlinx.coroutines.flow.Flow

interface IDictionaryRepository {

    fun searchWords(searchQuery: String): Flow<List<Dictionary>>

    fun getDictionaryByStripWord(word: String): Flow<Dictionary?>
}