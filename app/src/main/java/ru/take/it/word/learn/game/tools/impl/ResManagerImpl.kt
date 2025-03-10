package ru.take.it.word.learn.game.tools.impl

import android.content.Context
import androidx.annotation.StringRes
import ru.take.it.word.learn.game.tools.ResManager

class ResManagerImpl(
    private val context: Context,
) : ResManager {

    override fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }
}