package com.phyothinzaraung.eng_mm_dictionary.di

import android.content.Context
import com.phyothinzaraung.eng_mm_dictionary.data.DictionaryDao
import com.phyothinzaraung.eng_mm_dictionary.data.DictionaryDatabase
import com.phyothinzaraung.eng_mm_dictionary.data.FavoriteDao
import com.phyothinzaraung.eng_mm_dictionary.data.RecentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDictionaryDao(@ApplicationContext context: Context): DictionaryDao {
        return DictionaryDatabase.getInstance(context).dictionaryDao()
    }

    @Singleton
    @Provides
    fun provideFavoriteDao(@ApplicationContext context: Context): FavoriteDao {
        return DictionaryDatabase.getInstance(context).favoriteDao()
    }

    @Singleton
    @Provides
    fun provideRecentDao(@ApplicationContext context: Context): RecentDao {
        return DictionaryDatabase.getInstance(context).recentDao()
    }
}