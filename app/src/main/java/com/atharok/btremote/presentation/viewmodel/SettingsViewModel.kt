package com.atharok.btremote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atharok.btremote.domain.entity.ThemeEntity
import com.atharok.btremote.domain.entity.keyboard.KeyboardLanguage
import com.atharok.btremote.domain.entity.keyboard.layout.DEKeyboardLayout
import com.atharok.btremote.domain.entity.keyboard.layout.ESKeyboardLayout
import com.atharok.btremote.domain.entity.keyboard.layout.FRKeyboardLayout
import com.atharok.btremote.domain.entity.keyboard.layout.KeyboardLayout
import com.atharok.btremote.domain.entity.keyboard.layout.UKKeyboardLayout
import com.atharok.btremote.domain.entity.keyboard.layout.USKeyboardLayout
import com.atharok.btremote.domain.usecases.SettingsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform.getKoin

class SettingsViewModel(
    private val useCase: SettingsUseCase
): ViewModel() {

    val theme: Flow<ThemeEntity> = useCase.getTheme()

    fun changeTheme(newTheme: ThemeEntity) = viewModelScope.launch {
        useCase.saveTheme(newTheme)
    }

    val useDynamicColors: Flow<Boolean> = useCase.useDynamicColors()

    fun setUseDynamicColors(useDynamicColors: Boolean) = viewModelScope.launch {
        useCase.saveUseDynamicColors(useDynamicColors)
    }

    val useBlackColorForDarkTheme: Flow<Boolean> = useCase.useBlackColorForDarkTheme()

    fun setUseBlackColorForDarkTheme(useBlackColorForDarkTheme: Boolean) = viewModelScope.launch {
        useCase.saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme)
    }

    fun saveMouseSpeed(mouseSpeed: Float) = viewModelScope.launch {
        useCase.saveMouseSpeed(mouseSpeed)
    }

    fun getMouseSpeed(): Flow<Float> = useCase.getMouseSpeed()

    fun saveInvertMouseScrollingDirection(invertScrollingDirection: Boolean) = viewModelScope.launch {
        useCase.saveInvertMouseScrollingDirection(invertScrollingDirection)
    }

    fun shouldInvertMouseScrollingDirection(): Flow<Boolean> = useCase.shouldInvertMouseScrollingDirection()

    fun changeKeyboardLanguage(language: KeyboardLanguage) = viewModelScope.launch {
        useCase.saveKeyboardLanguage(language)
    }

    val keyboardLanguage: Flow<KeyboardLanguage> = useCase.getKeyboardLanguage()

    fun getKeyboardLayout(language: KeyboardLanguage): KeyboardLayout {
        return when(language) {
            KeyboardLanguage.ENGLISH_US -> getKoin().get<USKeyboardLayout>()
            KeyboardLanguage.ENGLISH_UK -> getKoin().get<UKKeyboardLayout>()
            KeyboardLanguage.SPANISH -> getKoin().get<ESKeyboardLayout>()
            KeyboardLanguage.FRENCH -> getKoin().get<FRKeyboardLayout>()
            KeyboardLanguage.GERMAN -> getKoin().get<DEKeyboardLayout>()
        }
    }

    fun saveMustClearInputField(mustClearInputField: Boolean) = viewModelScope.launch {
        useCase.saveMustClearInputField(mustClearInputField)
    }

    fun mustClearInputField(): Flow<Boolean> = useCase.mustClearInputField()
}