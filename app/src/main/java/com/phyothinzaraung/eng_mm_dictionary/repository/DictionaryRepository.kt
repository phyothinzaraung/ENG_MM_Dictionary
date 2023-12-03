package com.phyothinzaraung.eng_mm_dictionary.repository

import com.phyothinzaraung.eng_mm_dictionary.data.Dictionary
import com.phyothinzaraung.eng_mm_dictionary.data.DictionaryDao
import kotlinx.coroutines.flow.Flow

class DictionaryRepository(private val dictionaryDao: DictionaryDao) {
    fun searchWords(searchQuery: String): Flow<List<Dictionary>> {
        return dictionaryDao.searchWords(searchQuery)
    }
}