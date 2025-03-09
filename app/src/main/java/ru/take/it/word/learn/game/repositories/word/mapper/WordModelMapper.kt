package ru.take.it.word.learn.game.repositories.word.mapper

import ru.take.it.word.learn.game.core.db.entity.WordEntity
import ru.take.it.word.learn.game.repositories.word.WordModel

class WordModelMapper {

    fun mapEntityToModel(
        entity: WordEntity
    ): WordModel {
        return WordModel(
            id = entity.primaryId ?: 0,
            value = entity.wordValue,
            translate = entity.wordTranslate
        )
    }

    fun mapModelToEntity(
        model: WordModel
    ): WordEntity {
        return WordEntity(
            primaryId = model.id,
            wordValue = model.value,
            wordTranslate = model.translate
        )
    }
}