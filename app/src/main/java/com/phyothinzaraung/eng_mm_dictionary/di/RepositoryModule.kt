package com.phyothinzaraung.eng_mm_dictionary.di

import com.phyothinzaraung.eng_mm_dictionary.data.DictionaryDao
import com.phyothinzaraung.eng_mm_dictionary.data.FavoriteDao
import com.phyothinzaraung.eng_mm_dictionary.data.RecentDao
import com.phyothinzaraung.eng_mm_dictionary.repository.DictionaryRepository
import com.phyothinzaraung.eng_mm_dictionary.repository.FavoriteRepository
import com.phyothinzaraung.eng_mm_dictionary.repository.IDictionaryRepository
import com.phyothinzaraung.eng_mm_dictionary.repository.IFavoriteRepository
import com.phyothinzaraung.eng_mm_dictionary.repository.IRecentRepository
import com.phyothinzaraung.eng_mm_dictionary.repository.RecentRepository
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
        dictionaryDao: DictionaryDao
    ): IDictionaryRepository {
        return DictionaryRepository(dictionaryDao)
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(
        favoriteDao: FavoriteDao
    ): IFavoriteRepository {
        return FavoriteRepository(favoriteDao)
    }

    @Provides
    @Singleton
    fun provideRecentRepository(
        recentDao: RecentDao
    ): IRecentRepository{
        return RecentRepository(recentDao)
    }
}