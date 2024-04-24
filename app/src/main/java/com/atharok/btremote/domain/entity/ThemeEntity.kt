package com.atharok.btremote.domain.entity

import androidx.annotation.StringRes
import com.atharok.btremote.R

enum class ThemeEntity(@StringRes val stringRes: Int) {
    SYSTEM(R.string.theme_system),
    LIGHT(R.string.theme_light),
    DARK(R.string.theme_dark)
}