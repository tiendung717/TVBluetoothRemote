package com.atharok.btremote.ui.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.MOUSE_SPEED_DEFAULT_VALUE
import com.atharok.btremote.common.utils.SOURCE_CODE_LINK
import com.atharok.btremote.common.utils.WEB_SITE_LINK
import com.atharok.btremote.common.utils.isDynamicColorsAvailable
import com.atharok.btremote.domain.entity.ThemeEntity
import com.atharok.btremote.domain.entity.keyboard.KeyboardLanguage
import com.atharok.btremote.presentation.viewmodel.SettingsViewModel
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.ListDialog
import com.atharok.btremote.ui.components.NavigateUpAction
import com.atharok.btremote.ui.components.TextStandardPrimary
import com.atharok.btremote.ui.components.TextStandardSecondary
import com.atharok.btremote.ui.components.TextTitleSettings
import kotlinx.coroutines.flow.Flow

@Composable
fun SettingsScreen(
    navigateUp: () -> Unit,
    openThirdLibrariesScreen: () -> Unit,
    settingsViewModel: SettingsViewModel,
    modifier: Modifier = Modifier
) {
    AppScaffold(
        title = stringResource(id = R.string.settings),
        modifier = modifier,
        navigateUp = {
            NavigateUpAction(navigateUp)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {

            val context = LocalContext.current
            val uriHandler = LocalUriHandler.current
            val horizontalPadding = dimensionResource(id = R.dimen.padding_large)
            val verticalPadding = dimensionResource(id = R.dimen.padding_medium)

            // ---- Appearance ----

            TitleItem(
                text = stringResource(id = R.string.appearance),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            ThemeItem(
                themeFlow = settingsViewModel.theme,
                onThemeChange = { settingsViewModel.changeTheme(it) },
                context = context,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            OLEDBlackColorsSwitchItem(
                useBlackColorForDarkThemeFlow = settingsViewModel.useBlackColorForDarkTheme,
                onUseBlackColorForDarkThemeChange = { settingsViewModel.setUseBlackColorForDarkTheme(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            if(isDynamicColorsAvailable()) {
                DynamicColorsSwitchItem(
                    useDynamicColorsFlow = settingsViewModel.useDynamicColors,
                    onUseDynamicColorsChange = { settingsViewModel.setUseDynamicColors(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = horizontalPadding,
                            vertical = verticalPadding
                        )
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = verticalPadding)
            )

            // ---- Mouse ----

            TitleItem(
                text = stringResource(id = R.string.mouse),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            MouseSpeedItem(
                mouseSpeedFlow = settingsViewModel.getMouseSpeed(),
                onMouseSpeedChange = { settingsViewModel.saveMouseSpeed(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            InvertMouseScrollingDirectionSwitchItem(
                shouldInvertMouseScrollingDirectionFlow = settingsViewModel.shouldInvertMouseScrollingDirection(),
                onShouldInvertMouseScrollingDirectionChange = { settingsViewModel.saveInvertMouseScrollingDirection(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = verticalPadding)
            )
            
            // ---- Keyboard and Input Field ----

            TitleItem(
                text = stringResource(id = R.string.keyboard_and_input_field),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            KeyboardLanguageItem(
                languageFlow = settingsViewModel.keyboardLanguage,
                onLanguageChange = { settingsViewModel.changeKeyboardLanguage(it) },
                context = context,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            MustClearedInputFieldSwitchItem(
                mustClearInputFieldFlow = settingsViewModel.mustClearInputField(),
                onMustClearInputFieldChange = { settingsViewModel.saveMustClearInputField(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = verticalPadding)
            )

            // ---- About ----

            TitleItem(
                text = stringResource(id = R.string.about),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val activity = LocalContext.current as Activity
                TextItem(
                    text = stringResource(id = R.string.language),
                    modifier = Modifier
                        .clickable {
                            activity.startActivity(
                                Intent(
                                    Settings.ACTION_APP_LOCALE_SETTINGS,
                                    Uri.fromParts("package", activity.packageName, null)
                                )
                            )
                        }
                        .fillMaxWidth()
                        .padding(
                            horizontal = horizontalPadding,
                            vertical = verticalPadding
                        )
                )
            }

            TextItem(
                text = stringResource(id = R.string.third_party_library),
                modifier = Modifier
                    .clickable {
                        openThirdLibrariesScreen()
                    }
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            TextItem(
                text = stringResource(id = R.string.website),
                modifier = Modifier
                    .clickable {
                        uriHandler.openUri(WEB_SITE_LINK)
                    }
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            TextItem(
                text = stringResource(id = R.string.source_code),
                modifier = Modifier
                    .clickable {
                        uriHandler.openUri(SOURCE_CODE_LINK)
                    }
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )
        }
    }
}

@Composable
private fun ThemeItem(
    themeFlow: Flow<ThemeEntity>,
    onThemeChange: (ThemeEntity) -> Unit,
    context: Context,
    modifier: Modifier = Modifier
) {
    val theme: ThemeEntity by themeFlow
        .collectAsStateWithLifecycle(initialValue = ThemeEntity.SYSTEM)

    var isShowingDialog by remember { mutableStateOf(false) }

    SettingsListDialogItem(
        value = theme,
        onValueChange = onThemeChange,
        items = ThemeEntity.entries,
        convertValueToString = { context.getString(it.stringRes) },
        showDialog = isShowingDialog,
        onShowDialogChange = { isShowingDialog = it },
        title = R.string.theme,
        dialogMessage = null,
        modifier = modifier
    )
}
@Composable
private fun OLEDBlackColorsSwitchItem(
    useBlackColorForDarkThemeFlow: Flow<Boolean>,
    onUseBlackColorForDarkThemeChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val useBlackColorForDarkTheme: Boolean by useBlackColorForDarkThemeFlow
        .collectAsStateWithLifecycle(initialValue = false)

    SettingsSwitchItem(
        primaryText = stringResource(id = R.string.theme_black),
        secondaryText = stringResource(id = R.string.theme_black_oled_info),
        checked = useBlackColorForDarkTheme,
        onCheckedChange = onUseBlackColorForDarkThemeChange,
        modifier = modifier
    )
}

@Composable
private fun DynamicColorsSwitchItem(
    useDynamicColorsFlow: Flow<Boolean>,
    onUseDynamicColorsChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val useDynamicColors: Boolean by useDynamicColorsFlow
        .collectAsStateWithLifecycle(initialValue = true)

    SettingsSwitchItem(
        primaryText = stringResource(id = R.string.dynamic_colors),
        secondaryText = null,
        checked = useDynamicColors,
        onCheckedChange = onUseDynamicColorsChange,
        modifier = modifier
    )
}

@Composable
private fun MouseSpeedItem(
    mouseSpeedFlow: Flow<Float>,
    onMouseSpeedChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val mouseSpeed by mouseSpeedFlow
        .collectAsStateWithLifecycle(initialValue = MOUSE_SPEED_DEFAULT_VALUE)

    Column(
        modifier = modifier
    ) {
        TextStandardPrimary(
            text = stringResource(id = R.string.mouse_pointer_speed) + " (x$mouseSpeed)",
            modifier = Modifier.fillMaxWidth()
        )
        Slider(
            value = mouseSpeed,
            onValueChange = onMouseSpeedChange,
            valueRange = 1f..5f,
            steps = 15,
        )
    }
}

@Composable
private fun InvertMouseScrollingDirectionSwitchItem(
    shouldInvertMouseScrollingDirectionFlow: Flow<Boolean>,
    onShouldInvertMouseScrollingDirectionChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val shouldInvertMouseScrollingDirection: Boolean by shouldInvertMouseScrollingDirectionFlow
        .collectAsStateWithLifecycle(initialValue = false)

    SettingsSwitchItem(
        primaryText = stringResource(id = R.string.invert_mouse_scrolling_direction),
        secondaryText = null,
        checked = shouldInvertMouseScrollingDirection,
        onCheckedChange = onShouldInvertMouseScrollingDirectionChange,
        modifier = modifier
    )
}

@Composable
private fun KeyboardLanguageItem(
    languageFlow: Flow<KeyboardLanguage>,
    onLanguageChange: (KeyboardLanguage) -> Unit,
    context: Context,
    modifier: Modifier = Modifier
) {
    val language: KeyboardLanguage by languageFlow
        .collectAsStateWithLifecycle(initialValue = KeyboardLanguage.ENGLISH_US)

    var isShowingDialog by remember { mutableStateOf(false) }

    SettingsListDialogItem(
        value = language,
        onValueChange = onLanguageChange,
        items = KeyboardLanguage.entries,
        convertValueToString = { context.getString(it.language) },
        showDialog = isShowingDialog,
        onShowDialogChange = { isShowingDialog = it },
        title = R.string.keyboard_language,
        dialogMessage = stringResource(id = R.string.keyboard_language_info),
        modifier = modifier
    )
}

@Composable
private fun MustClearedInputFieldSwitchItem(
    mustClearInputFieldFlow: Flow<Boolean>,
    onMustClearInputFieldChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val mustClearInputField: Boolean by mustClearInputFieldFlow
        .collectAsStateWithLifecycle(initialValue = true)

    SettingsSwitchItem(
        primaryText = stringResource(id = R.string.clear_input_field),
        secondaryText = null,
        checked = mustClearInputField,
        onCheckedChange = onMustClearInputFieldChange,
        modifier = modifier
    )
}

// --- Reusable components ----

@Composable
fun <T> SettingsListDialogItem(
    value: T,
    onValueChange: (T) -> Unit,
    items: List<T>,
    convertValueToString: (T) -> String,
    showDialog: Boolean,
    onShowDialogChange: (Boolean) -> Unit,
    @StringRes title: Int,
    dialogMessage: String?,
    modifier: Modifier = Modifier
) {
    if(showDialog) {
        ListDialog(
            confirmButtonText = stringResource(android.R.string.ok),
            dismissButtonText = stringResource(android.R.string.cancel),
            onConfirmation = { index ->
                onValueChange(items[index])
                onShowDialogChange(false)
            },
            onDismissRequest = { onShowDialogChange(false) },
            dialogTitle = stringResource(title),
            dialogMessage = dialogMessage,
            items = items.map { convertValueToString(it) },
            defaultItemIndex = items.indexOf(value)
        )
    }

    Column(
        modifier = Modifier
            .clickable { onShowDialogChange(true) }
            .then(modifier)
    ) {
        TextStandardPrimary(text = stringResource(id = title))
        TextStandardSecondary(text = convertValueToString(value))
    }
}

@Composable
private fun SettingsSwitchItem(
    primaryText: String,
    secondaryText: String?,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .clickable { onCheckedChange(!checked) }
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            TextStandardPrimary(text = primaryText)
            secondaryText?.let {
                TextStandardSecondary(text = it)
            }
        }

        Switch(
            checked = checked,
            onCheckedChange = null//onCheckedChange
        )
    }
}

@Composable
private fun TitleItem(
    text: String,
    modifier: Modifier = Modifier
) {
    TextTitleSettings(
        text = text,
        modifier = modifier
    )
}

@Composable
private fun TextItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        TextStandardPrimary(
            text = text,
            modifier = Modifier.fillMaxSize()
        )
    }
}