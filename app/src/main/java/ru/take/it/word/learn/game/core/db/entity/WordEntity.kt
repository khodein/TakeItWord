package ru.take.it.word.learn.game.core.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "TakeItWord_WordTable",
)
class WordEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("id") val primaryId: Int? = null,
    @ColumnInfo("wordValue") val wordValue: String,
    @ColumnInfo("wordTranslate") val wordTranslate: String,
)