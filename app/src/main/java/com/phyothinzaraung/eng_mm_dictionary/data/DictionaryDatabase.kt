package com.phyothinzaraung.eng_mm_dictionary.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Dictionary::class, Favorite::class, Recent::class], version = 2, exportSchema = false)
abstract class DictionaryDatabase: RoomDatabase() {

    abstract fun dictionaryDao(): DictionaryDao
    abstract fun favoriteDao(): FavoriteDao

    abstract fun recentDao(): RecentDao

    companion object {
        const val DB_NAME = "dictionary.db"

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("""CREATE TABLE IF NOT EXISTS `favorite` 
                        (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `word` TEXT, `stripword` TEXT)""")

                db.execSQL("""CREATE TABLE IF NOT EXISTS `recent` 
                        (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `word` TEXT, `stripword` TEXT)""")
            }
        }
    }
}
