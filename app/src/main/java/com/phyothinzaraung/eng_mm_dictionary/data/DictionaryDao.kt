package com.phyothinzaraung.eng_mm_dictionary.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDao {
    @Query("SELECT * FROM dictionary WHERE word LIKE '%' || :searchQuery || '%'")
    fun searchWords(searchQuery: String): Flow<List<Dictionary>>

    @Query("SELECT * FROM dictionary WHERE _id = :id")
    fun getDictionaryById(id: Long): Flow<Dictionary?>
}