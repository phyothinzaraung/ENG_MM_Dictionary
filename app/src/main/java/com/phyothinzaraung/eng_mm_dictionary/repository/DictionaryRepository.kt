package com.phyothinzaraung.eng_mm_dictionary.repository

import com.phyothinzaraung.eng_mm_dictionary.data.Dictionary
import com.phyothinzaraung.eng_mm_dictionary.data.DictionaryDao
import com.phyothinzaraung.eng_mm_dictionary.data.Favorite
import com.phyothinzaraung.eng_mm_dictionary.data.FavoriteDao
import com.phyothinzaraung.eng_mm_dictionary.data.Recent
import com.phyothinzaraung.eng_mm_dictionary.data.RecentDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DictionaryRepository @Inject constructor(
    private val dictionaryDao: DictionaryDao,
    private val favoriteDao: FavoriteDao,
    private val recentDao: RecentDao) : IDictionaryRepository {
    override fun searchWords(searchQuery: String): Flow<List<Dictionary>> {
        return dictionaryDao.searchWords(searchQuery)
    }

    override fun getDictionaryByStripWord(word: String): Flow<Dictionary?>{
        return dictionaryDao.getDictionaryByWord(word)
    }

    override suspend fun insertFavorite(favorite: Favorite){
        favoriteDao.insertFavorite(favorite)
    }

    override suspend fun deleteFavorite(favorite: Favorite){
        favoriteDao.deleteFavorite(favorite)
    }

    override fun getFavorites(): Flow<List<Favorite>>{
        return favoriteDao.getFavorites()
    }

    override suspend fun insertRecent(recent: Recent){
        recentDao.insert(recent)
    }

    override fun getRecent(): Flow<List<Recent>>{
        return recentDao.getRecent()
    }

    override suspend fun clearAllRecent(){
        recentDao.clearRecent()
    }
}