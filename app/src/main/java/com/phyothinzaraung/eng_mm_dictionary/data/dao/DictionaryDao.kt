package com.phyothinzaraung.eng_mm_dictionary.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.phyothinzaraung.eng_mm_dictionary.data.model.Dictionary
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDao {
    @Query("SELECT * FROM dictionary WHERE word LIKE '%' || :searchQuery || '%'")
    fun searchWords(searchQuery: String): Flow<List<Dictionary>>

    @Query("SELECT * FROM dictionary WHERE stripWord = :stripWord")
    fun getDictionaryByWord(stripWord: String): Flow<Dictionary?>

}