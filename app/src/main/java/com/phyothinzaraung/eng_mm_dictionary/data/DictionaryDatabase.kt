package com.phyothinzaraung.eng_mm_dictionary.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Dictionary::class], version = 1, exportSchema = false)
abstract class DictionaryDatabase: RoomDatabase() {

    abstract fun dictionaryDao(): DictionaryDao

    companion object{
        private const val DB_NAME = "dictionary.db"

        @Volatile
        private var INSTANCE: DictionaryDatabase? = null

        fun getInstance(context: Context): DictionaryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DictionaryDatabase::class.java,
                    DB_NAME)
                    .createFromAsset(DB_NAME)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
