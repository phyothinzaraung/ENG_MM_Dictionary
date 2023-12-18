package com.phyothinzaraung.eng_mm_dictionary.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary")
data class Dictionary(
    @PrimaryKey
    @ColumnInfo(name = "_id")
    var id: Long,
    @ColumnInfo(index = true)
    var word: String? = null,
    @ColumnInfo(name = "stripword", index = true)
    var stripWord: String? = null,
    var title: String? = null,
    var definition: String? = null,
    var keywords: String? = null,
    var synonym: String? = null,
    var picture: String? = null
)
