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

    companion object{
        private const val DB_NAME = "dictionary.db"

        @Volatile
        private var INSTANCE: DictionaryDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("""CREATE TABLE IF NOT EXISTS `favorite` 
                        (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `word` TEXT, `stripword` TEXT)""")

                db.execSQL("""CREATE TABLE IF NOT EXISTS `recent` 
                        (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `word` TEXT, `stripword` TEXT)""")
            }
        }

        fun getInstance(context: Context): DictionaryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DictionaryDatabase::class.java,
                    DB_NAME)
                    .createFromAsset(DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
