package com.atharok.btremote.data.dataStore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.atharok.btremote.common.extensions.dataStore
import com.atharok.btremote.common.utils.MOUSE_SPEED_DEFAULT_VALUE
import com.atharok.btremote.common.utils.isDynamicColorsAvailable
import com.atharok.btremote.domain.entity.ThemeEntity
import com.atharok.btremote.domain.entity.keyboard.KeyboardLanguage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class SettingsDataStore(private val context: Context) {

    companion object {
        private const val THEME_KEY = "theme_key"
        private const val DYNAMIC_COLORS_KEY = "material_you_key"
        private const val BLACK_COLOR_KEY = "black_color_key"
        private const val MOUSE_SPEED_KEY = "mouse_speed_key"
        private const val INVERT_MOUSE_SCROLLING_DIRECTION_KEY = "invert_mouse_scrolling_direction_key"
        private const val KEYBOARD_LANGUAGE = "keyboard_language"
        private const val MUST_CLEAR_INPUT_FIELD_KEY = "must_clear_input_field_key"
    }

    private val themeKey = stringPreferencesKey(THEME_KEY)
    private val useDynamicColorsKey = booleanPreferencesKey(DYNAMIC_COLORS_KEY)
    private val useBlackColorForDarkThemeKey = booleanPreferencesKey(BLACK_COLOR_KEY)
    private val mouseSpeedKey = floatPreferencesKey(MOUSE_SPEED_KEY)
    private val invertMouseScrollingDirectionKey = booleanPreferencesKey(INVERT_MOUSE_SCROLLING_DIRECTION_KEY)
    private val keyboardLanguageKey = stringPreferencesKey(KEYBOARD_LANGUAGE)
    private val mustClearInputFieldKey = booleanPreferencesKey(MUST_CLEAR_INPUT_FIELD_KEY)

    val themeFlow: Flow<ThemeEntity> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[themeKey] ?: ThemeEntity.SYSTEM.name
        }.map {
            try {
                ThemeEntity.valueOf(it)
            } catch (e: IllegalArgumentException) {
                ThemeEntity.SYSTEM
            }
        }

    suspend fun saveTheme(themeEntity: ThemeEntity) {
        context.dataStore.edit {
            it[themeKey] = themeEntity.name
        }
    }

    val useDynamicColorsFlow: Flow<Boolean> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[useDynamicColorsKey] ?: isDynamicColorsAvailable()
        }

    suspend fun saveUseDynamicColors(useDynamicColors: Boolean) {
        context.dataStore.edit {
            it[useDynamicColorsKey] = if(isDynamicColorsAvailable()) useDynamicColors else false
        }
    }

    val useBlackColorForDarkThemeFlow: Flow<Boolean> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[useBlackColorForDarkThemeKey] ?: false
        }

    suspend fun saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme: Boolean) {
        context.dataStore.edit {
            it[useBlackColorForDarkThemeKey] = useBlackColorForDarkTheme
        }
    }

    val mouseSpeed: Flow<Float> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[mouseSpeedKey] ?: MOUSE_SPEED_DEFAULT_VALUE
        }

    suspend fun saveMouseSpeed(mouseSpeed: Float) {
        context.dataStore.edit {
            it[mouseSpeedKey] = mouseSpeed
        }
    }

    val shouldInvertMouseScrollingDirection: Flow<Boolean> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[invertMouseScrollingDirectionKey] ?: false
        }

    suspend fun saveInvertMouseScrollingDirection(invertScrollingDirection: Boolean) {
        context.dataStore.edit {
            it[invertMouseScrollingDirectionKey] = invertScrollingDirection
        }
    }

    val keyboardLanguageFlow: Flow<KeyboardLanguage> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[keyboardLanguageKey] ?: KeyboardLanguage.ENGLISH_US.name
        }.map {
            try {
                KeyboardLanguage.valueOf(it)
            } catch (e: IllegalArgumentException) {
                KeyboardLanguage.ENGLISH_US
            }
        }

    suspend fun saveKeyboardLanguage(language: KeyboardLanguage) {
        context.dataStore.edit {
            it[keyboardLanguageKey] = language.name
        }
    }

    val mustClearInputFieldFlow: Flow<Boolean> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[mustClearInputFieldKey] ?: true
        }

    suspend fun saveMustClearInputField(mustClearInputField: Boolean) {
        context.dataStore.edit {
            it[mustClearInputFieldKey] = mustClearInputField
        }
    }
}