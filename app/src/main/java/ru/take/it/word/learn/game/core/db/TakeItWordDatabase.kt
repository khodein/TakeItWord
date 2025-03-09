package ru.take.it.word.learn.game.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.dsl.module
import ru.take.it.word.learn.game.core.db.dao.WordsDao
import ru.take.it.word.learn.game.core.db.entity.WordEntity

@Database(
    version = 1,
    entities = [
        WordEntity::class
    ],
)
abstract class TakeItWordDatabase : RoomDatabase() {

    abstract fun wordsDao(): WordsDao

    companion object {
        const val DATABASE_NAME = "TakeItWordDatabase"
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get<Context>(),
            TakeItWordDatabase::class.java,
            TakeItWordDatabase.DATABASE_NAME,
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    single<WordsDao> { get<TakeItWordDatabase>().wordsDao() }
}