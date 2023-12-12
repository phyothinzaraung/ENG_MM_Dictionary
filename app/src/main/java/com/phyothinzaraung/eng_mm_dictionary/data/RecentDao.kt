package com.phyothinzaraung.eng_mm_dictionary.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(recent: Recent)

    @Query("SELECT * FROM recent")
    fun getRecent(): Flow<List<Recent>>

    @Query("DELETE FROM recent")
    suspend fun clearRecent()
}