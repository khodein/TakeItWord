package ru.take.it.word.learn.game

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.take.it.word.learn.game.core.db.databaseModule
import ru.take.it.word.learn.game.core.navigation.dialog.dialogNavigationModule
import ru.take.it.word.learn.game.core.navigation.screen.screenNavigationModule
import ru.take.it.word.learn.game.repositories.word.wordRepositoryModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                screenNavigationModule,
                dialogNavigationModule,
                databaseModule,
                wordRepositoryModule,
            )
        }
    }
}