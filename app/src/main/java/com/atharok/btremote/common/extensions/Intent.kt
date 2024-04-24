package com.atharok.btremote.common.extensions

import android.content.Intent
import android.os.Parcelable
import java.io.Serializable

fun <T : Serializable?> Intent.serializable(name: String?, clazz: Class<T>): T? {
    return this.extras?.serializable(name, clazz)
}

fun <T : Parcelable?> Intent.parcelable(name: String?, clazz: Class<T>): T? {
    return this.extras?.parcelable(name, clazz)
}