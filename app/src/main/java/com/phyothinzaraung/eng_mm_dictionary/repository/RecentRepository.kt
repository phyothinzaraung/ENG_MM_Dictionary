package com.phyothinzaraung.eng_mm_dictionary.repository

import com.phyothinzaraung.eng_mm_dictionary.data.model.Recent
import com.phyothinzaraung.eng_mm_dictionary.data.dao.RecentDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecentRepository @Inject constructor(
    private val recentDao: RecentDao
): IRecentRepository {
    override suspend fun insertRecent(recent: Recent){
        recentDao.insert(recent)
    }

    override fun getRecent(): Flow<List<Recent>> {
        return recentDao.getRecent()
    }

    override suspend fun clearAllRecent(){
        recentDao.clearRecent()
    }
}