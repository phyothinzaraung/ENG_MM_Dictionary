package com.phyothinzaraung.eng_mm_dictionary.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent")
data class Recent(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Long = 0L,
    var word: String? = null,
    var stripword: String? = null
)
