package com.phyothinzaraung.eng_mm_dictionary.di

import com.phyothinzaraung.eng_mm_dictionary.repository.DictionaryRepository
import com.phyothinzaraung.eng_mm_dictionary.repository.FavoriteRepository
import com.phyothinzaraung.eng_mm_dictionary.repository.IDictionaryRepository
import com.phyothinzaraung.eng_mm_dictionary.repository.IFavoriteRepository
import com.phyothinzaraung.eng_mm_dictionary.repository.IRecentRepository
import com.phyothinzaraung.eng_mm_dictionary.repository.RecentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindDictionaryRepository(repository: DictionaryRepository): IDictionaryRepository

    @Binds
    fun bindFavoriteRepository(repository: FavoriteRepository): IFavoriteRepository

    @Binds
    fun bindRecentRepository(repository: RecentRepository): IRecentRepository

}