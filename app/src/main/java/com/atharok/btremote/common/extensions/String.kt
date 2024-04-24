package com.atharok.btremote.common.extensions

import java.util.Locale

fun String.capitalizeFirstChar(): String = this.replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
}