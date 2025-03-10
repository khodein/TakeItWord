package ru.take.it.word.learn.game.tools

import androidx.annotation.StringRes

interface ResManager {
    fun getString(@StringRes stringResId: Int): String
}