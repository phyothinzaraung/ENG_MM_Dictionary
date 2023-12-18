package com.phyothinzaraung.eng_mm_dictionary.repository

import com.phyothinzaraung.eng_mm_dictionary.data.Recent
import kotlinx.coroutines.flow.Flow

interface IRecentRepository {
    suspend fun insertRecent(recent: Recent)

    fun getRecent(): Flow<List<Recent>>

    suspend fun clearAllRecent()
}