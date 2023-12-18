package com.phyothinzaraung.eng_mm_dictionary.repository

import com.phyothinzaraung.eng_mm_dictionary.data.model.Dictionary
import com.phyothinzaraung.eng_mm_dictionary.data.dao.DictionaryDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DictionaryRepository @Inject constructor(
    private val dictionaryDao: DictionaryDao
) : IDictionaryRepository {
    override fun searchWords(searchQuery: String): Flow<List<Dictionary>> {
        return dictionaryDao.searchWords(searchQuery)
    }

    override fun getDictionaryByStripWord(word: String): Flow<Dictionary?>{
        return dictionaryDao.getDictionaryByWord(word)
    }
}