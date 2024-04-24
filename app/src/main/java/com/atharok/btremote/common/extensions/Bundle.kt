package com.atharok.btremote.common.extensions

import android.os.Build
import android.os.Bundle
import java.io.Serializable

fun <T : Serializable?> Bundle.serializable(name: String?, clazz: Class<T>): T? = when {
    Build.VERSION.SDK_INT >= 33 -> this.getSerializable(name, clazz)
    else -> @Suppress("DEPRECATION") clazz.cast(this.getSerializable(name))
}