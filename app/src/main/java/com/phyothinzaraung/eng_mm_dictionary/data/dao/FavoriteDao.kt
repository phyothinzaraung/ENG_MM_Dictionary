package com.phyothinzaraung.eng_mm_dictionary.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.phyothinzaraung.eng_mm_dictionary.data.model.Favorite
import kotlinx.coroutines.flow.Flow
@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorite")
    fun getFavorites(): Flow<List<Favorite>>

    @Query("DELETE FROM favorite")
    suspend fun clearFavorites()
}