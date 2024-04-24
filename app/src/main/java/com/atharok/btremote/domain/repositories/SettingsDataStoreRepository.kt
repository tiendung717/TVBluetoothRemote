package com.atharok.btremote.domain.repositories

import com.atharok.btremote.domain.entity.ThemeEntity
import com.atharok.btremote.domain.entity.keyboard.KeyboardLanguage
import kotlinx.coroutines.flow.Flow

interface SettingsDataStoreRepository {
    suspend fun saveTheme(themeEntity: ThemeEntity)
    fun getTheme(): Flow<ThemeEntity>

    suspend fun saveUseDynamicColors(useDynamicColors: Boolean)
    fun useDynamicColors(): Flow<Boolean>

    suspend fun saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme: Boolean)
    fun useBlackColorForDarkTheme(): Flow<Boolean>

    suspend fun saveMouseSpeed(mouseSpeed: Float)
    fun getMouseSpeed(): Flow<Float>

    suspend fun saveInvertMouseScrollingDirection(invertScrollingDirection: Boolean)
    fun shouldInvertMouseScrollingDirection(): Flow<Boolean>

    suspend fun saveKeyboardLanguage(language: KeyboardLanguage)
    fun getKeyboardLanguage(): Flow<KeyboardLanguage>

    suspend fun saveMustClearInputField(mustClearInputField: Boolean)
    fun mustClearInputField(): Flow<Boolean>
}