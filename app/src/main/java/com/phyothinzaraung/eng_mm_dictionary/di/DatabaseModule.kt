package com.phyothinzaraung.eng_mm_dictionary.di

import android.content.Context
import androidx.room.Room
import com.phyothinzaraung.eng_mm_dictionary.data.dao.DictionaryDao
import com.phyothinzaraung.eng_mm_dictionary.data.DictionaryDatabase
import com.phyothinzaraung.eng_mm_dictionary.data.dao.FavoriteDao
import com.phyothinzaraung.eng_mm_dictionary.data.dao.RecentDao
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
    fun provideYourDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        DictionaryDatabase::class.java,
        DictionaryDatabase.DB_NAME
    )
        .createFromAsset(DictionaryDatabase.DB_NAME)
        .fallbackToDestructiveMigration()
        .addMigrations(DictionaryDatabase.MIGRATION_1_2)
        .allowMainThreadQueries()
        .build()

    @Singleton
    @Provides
    fun provideDictionaryDao(dictionaryDatabase: DictionaryDatabase): DictionaryDao {
        return dictionaryDatabase.dictionaryDao()
    }

    @Singleton
    @Provides
    fun provideFavoriteDao(dictionaryDatabase: DictionaryDatabase): FavoriteDao {
        return dictionaryDatabase.favoriteDao()
    }

    @Singleton
    @Provides
    fun provideRecentDao(dictionaryDatabase: DictionaryDatabase): RecentDao {
        return dictionaryDatabase.recentDao()
    }
}