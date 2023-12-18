package com.phyothinzaraung.eng_mm_dictionary.di

import com.phyothinzaraung.eng_mm_dictionary.util.DefaultDispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoroutinesModule {
    @Binds
    @Singleton
    fun bindCoroutinesDispatchers(
        impl: DefaultDispatcherProvider
    ): DefaultDispatcherProvider
}