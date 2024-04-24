package com.atharok.btremote.domain.usecases

import com.atharok.btremote.domain.entity.ThemeEntity
import com.atharok.btremote.domain.entity.keyboard.KeyboardLanguage
import com.atharok.btremote.domain.repositories.SettingsDataStoreRepository
import kotlinx.coroutines.flow.Flow

class SettingsUseCase(private val repository: SettingsDataStoreRepository) {

    suspend fun saveTheme(themeEntity: ThemeEntity) {
        repository.saveTheme(themeEntity)
    }

    fun getTheme(): Flow<ThemeEntity> = repository.getTheme()

    suspend fun saveUseDynamicColors(useDynamicColors: Boolean) {
        repository.saveUseDynamicColors(useDynamicColors)
    }

    fun useDynamicColors(): Flow<Boolean> = repository.useDynamicColors()

    suspend fun saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme: Boolean) {
        repository.saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme)
    }

    fun useBlackColorForDarkTheme(): Flow<Boolean> = repository.useBlackColorForDarkTheme()

    suspend fun saveMouseSpeed(mouseSpeed: Float) {
        repository.saveMouseSpeed(mouseSpeed)
    }

    fun getMouseSpeed(): Flow<Float> = repository.getMouseSpeed()

    suspend fun saveInvertMouseScrollingDirection(invertScrollingDirection: Boolean) {
        repository.saveInvertMouseScrollingDirection(invertScrollingDirection)
    }

    fun shouldInvertMouseScrollingDirection(): Flow<Boolean> = repository.shouldInvertMouseScrollingDirection()

    suspend fun saveKeyboardLanguage(language: KeyboardLanguage) {
        repository.saveKeyboardLanguage(language)
    }

    fun getKeyboardLanguage(): Flow<KeyboardLanguage> = repository.getKeyboardLanguage()

    suspend fun saveMustClearInputField(mustClearInputField: Boolean) {
        repository.saveMustClearInputField(mustClearInputField)
    }

    fun mustClearInputField(): Flow<Boolean> = repository.mustClearInputField()
}