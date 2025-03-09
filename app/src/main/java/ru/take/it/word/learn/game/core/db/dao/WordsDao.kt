package ru.take.it.word.learn.game.core.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.take.it.word.learn.game.core.db.entity.WordEntity

@Dao
interface WordsDao {

    @Query("SELECT * FROM takeitword_wordtable")
    suspend fun getAll(): List<WordEntity>

    @Insert
    suspend fun insertAll(vararg words: WordEntity)

    @Delete
    fun delete(word: WordEntity)
}