package com.phyothinzaraung.eng_mm_dictionary.repository

import com.phyothinzaraung.eng_mm_dictionary.data.model.Favorite
import kotlinx.coroutines.flow.Flow

interface IFavoriteRepository {

    suspend fun insertFavorite(favorite: Favorite)

    suspend fun deleteFavorite(favorite: Favorite)

    fun getFavorites(): Flow<List<Favorite>>
}