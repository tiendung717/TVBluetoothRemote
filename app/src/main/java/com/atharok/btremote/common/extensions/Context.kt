package com.atharok.btremote.common.extensions

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.atharok.btremote.common.utils.DATA_STORE_PREFERENCES_SETTINGS_NAME

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_PREFERENCES_SETTINGS_NAME)