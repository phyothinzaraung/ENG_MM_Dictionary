package com.phyothinzaraung.eng_mm_dictionary.di

import com.phyothinzaraung.eng_mm_dictionary.data.DictionaryDao
import com.phyothinzaraung.eng_mm_dictionary.data.FavoriteDao
import com.phyothinzaraung.eng_mm_dictionary.data.RecentDao
import com.phyothinzaraung.eng_mm_dictionary.repository.DictionaryRepository
import com.phyothinzaraung.eng_mm_dictionary.repository.IDictionaryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDictionaryRepository(
        dictionaryDao: DictionaryDao,
        favoriteDao: FavoriteDao,
        recentDao: RecentDao
    ): IDictionaryRepository {
        return DictionaryRepository(dictionaryDao, favoriteDao, recentDao)
    }
}