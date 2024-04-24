package com.atharok.btremote.data.repositories

import com.atharok.btremote.data.dataStore.SettingsDataStore
import com.atharok.btremote.domain.entity.ThemeEntity
import com.atharok.btremote.domain.entity.keyboard.KeyboardLanguage
import com.atharok.btremote.domain.repositories.SettingsDataStoreRepository
import kotlinx.coroutines.flow.Flow

class SettingsDataStoreRepositoryImpl(
    private val settingsDataStore: SettingsDataStore
): SettingsDataStoreRepository {

    override suspend fun saveTheme(themeEntity: ThemeEntity) {
        settingsDataStore.saveTheme(themeEntity)
    }

    override fun getTheme(): Flow<ThemeEntity> = settingsDataStore.themeFlow

    override suspend fun saveUseDynamicColors(useDynamicColors: Boolean) {
        settingsDataStore.saveUseDynamicColors(useDynamicColors)
    }

    override fun useDynamicColors(): Flow<Boolean> = settingsDataStore.useDynamicColorsFlow

    override suspend fun saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme: Boolean) {
        settingsDataStore.saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme)
    }

    override fun useBlackColorForDarkTheme(): Flow<Boolean> = settingsDataStore.useBlackColorForDarkThemeFlow

    override suspend fun saveMouseSpeed(mouseSpeed: Float) {
        settingsDataStore.saveMouseSpeed(mouseSpeed)
    }

    override fun getMouseSpeed(): Flow<Float> = settingsDataStore.mouseSpeed

    override suspend fun saveInvertMouseScrollingDirection(invertScrollingDirection: Boolean) {
        settingsDataStore.saveInvertMouseScrollingDirection(invertScrollingDirection)
    }

    override fun shouldInvertMouseScrollingDirection(): Flow<Boolean> = settingsDataStore.shouldInvertMouseScrollingDirection

    override suspend fun saveKeyboardLanguage(language: KeyboardLanguage) {
        settingsDataStore.saveKeyboardLanguage(language)
    }

    override fun getKeyboardLanguage(): Flow<KeyboardLanguage> = settingsDataStore.keyboardLanguageFlow

    override suspend fun saveMustClearInputField(mustClearInputField: Boolean) {
        settingsDataStore.saveMustClearInputField(mustClearInputField)
    }

    override fun mustClearInputField(): Flow<Boolean> = settingsDataStore.mustClearInputFieldFlow
}