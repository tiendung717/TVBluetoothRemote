package com.atharok.btremote.common.extensions

import android.os.Build
import android.os.Bundle
import android.os.Parcelable

fun <T : Parcelable?> Bundle.parcelable(name: String?, clazz: Class<T>): T? = when {
    Build.VERSION.SDK_INT >= 33 -> this.getParcelable(name, clazz)
    else -> @Suppress("DEPRECATION") clazz.cast(this.getParcelable(name))
}