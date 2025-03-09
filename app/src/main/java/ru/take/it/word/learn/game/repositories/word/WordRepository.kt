package ru.take.it.word.learn.game.repositories.word

interface WordRepository {
    suspend fun setWordsList(list: List<WordModel>)
    suspend fun getWordsList(): List<WordModel>
}