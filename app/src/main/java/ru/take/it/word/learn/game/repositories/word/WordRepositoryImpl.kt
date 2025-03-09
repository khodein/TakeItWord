package ru.take.it.word.learn.game.repositories.word

import org.koin.dsl.module
import ru.take.it.word.learn.game.core.db.dao.WordsDao
import ru.take.it.word.learn.game.repositories.word.mapper.WordModelMapper

private class WordRepositoryImpl(
    private val wordsDao: WordsDao,
    private val wordModelMapper: WordModelMapper,
) : WordRepository {

    override suspend fun setWordsList(list: List<WordModel>) {
        val entities = list.map(wordModelMapper::mapModelToEntity)
        wordsDao.insertAll(words = entities.toTypedArray())
    }

    override suspend fun getWordsList(): List<WordModel> {
        val entities = wordsDao.getAll()
        return entities.map(wordModelMapper::mapEntityToModel)
    }
}

val wordRepositoryModule = module {
    factory { WordModelMapper() }
    factory<WordRepository> { WordRepositoryImpl(get(), get()) }
}