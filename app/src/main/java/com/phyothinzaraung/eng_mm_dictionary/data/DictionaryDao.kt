package com.phyothinzaraung.eng_mm_dictionary.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDao {
    @Query("SELECT * FROM dictionary WHERE word LIKE '%' || :searchQuery || '%'")
    fun searchWords(searchQuery: String): Flow<List<Dictionary>>

    @Query("SELECT * FROM dictionary WHERE stripWord = :stripWord")
    fun getDictionaryByWord(stripWord: String): Flow<Dictionary?>

}