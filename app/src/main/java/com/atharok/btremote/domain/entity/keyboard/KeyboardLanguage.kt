package com.atharok.btremote.domain.entity.keyboard

import androidx.annotation.StringRes
import com.atharok.btremote.R

enum class KeyboardLanguage(@StringRes val language: Int) {
    ENGLISH_US(R.string.keyboard_us),
    ENGLISH_UK(R.string.keyboard_uk),
    SPANISH(R.string.keyboard_es),
    FRENCH(R.string.keyboard_fr),
    GERMAN(R.string.keyboard_de)
}