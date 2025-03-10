package ru.take.it.word.learn.game.tools

import android.content.Context
import org.koin.dsl.module
import ru.take.it.word.learn.game.tools.impl.ResManagerImpl

val toolsModule = module {
    single<ResManager>() {
        ResManagerImpl(get<Context>())
    }
}