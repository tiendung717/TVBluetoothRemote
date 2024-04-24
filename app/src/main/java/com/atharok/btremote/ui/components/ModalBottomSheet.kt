package com.atharok.btremote.ui.components

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.HelpOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HelpModalBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit)
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        windowInsets = WindowInsets(0, 0, 0, 0)
    ) {
        Surface {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_large),
                        end = dimensionResource(id = R.dimen.padding_large),
                        bottom = dimensionResource(id = R.dimen.padding_large)
                    )
            ) {
                Row(
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_large)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        imageVector = Icons.AutoMirrored.Rounded.HelpOutline,
                        contentDescription = stringResource(id = R.string.help),
                        modifier = Modifier.size(dimensionResource(id = R.dimen.help_icon_size)),
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface)
                    )
                    TextTitlePrimary(
                        text = stringResource(id = R.string.help),
                        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_standard))
                    )
                }
                content()
            }
        }
    }
}

@Composable
fun DevicesSelectionScreenHelpModalBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HelpModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {

        // Instruction
        TextTitleTertiary(
            text = stringResource(id = R.string.help_select_device_from_list),
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_large))
        )

        // Missing device
        Section(
            title = stringResource(id = R.string.help_missing_device_title),
            message = stringResource(id = R.string.help_missing_device_message)
        )

        // Initialization error
        Section(
            title = stringResource(id = R.string.help_connection_initialization_error_title),
            message = buildString {
                append(
                    stringResource(
                        id = R.string.help_connection_initialization_error_message_1,
                        stringResource(id = R.string.bluetooth_failed_to_register_app_message),
                        stringResource(id = R.string.bluetooth_failed_to_connect_to_device)
                    )
                )
                append("\n\n")
                append(stringResource(id = R.string.help_connection_initialization_error_check_1))
                append("\n")
                append(stringResource(id = R.string.help_connection_initialization_error_check_2))
                append("\n\n")
                append(stringResource(id = R.string.help_connection_initialization_error_message_2))
            }
        )

        // Connection failure
        Section(
            title = stringResource(id = R.string.help_device_failed_connection_title),
            message = buildString {
                append(stringResource(id = R.string.help_device_failed_connection_message_1))
                append("\n\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_1))
                append("\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_2))
                append("\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_3))
                append("\n\n")
                append(stringResource(id = R.string.help_device_failed_connection_message_2))
                append("\n\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_5))
                append("\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_6))
                append("\n\n")
                append(stringResource(id = R.string.help_device_failed_connection_message_3))
            }
        )
    }
}

@Composable
fun BluetoothScanningScreenHelpModalBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HelpModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {

        // Instruction
        TextTitleTertiary(
            text = stringResource(id = R.string.help_paring_select_device_from_list),
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_large))
        )

        // Missing device
        Section(
            title = stringResource(id = R.string.help_missing_device_title),
            message = buildString {
                append(stringResource(id = R.string.help_pairing_missing_device_message_1))
                append("\n\n")
                append(stringResource(id = R.string.help_pairing_missing_device_check_1))
                append("\n")
                append(stringResource(id = R.string.help_pairing_missing_device_check_2))
                append("\n")
                append(stringResource(id = R.string.help_pairing_missing_device_check_3))
                if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
                    append("\n")
                    append(stringResource(id = R.string.help_pairing_missing_device_check_location_for_android_11_or_less))
                }
            }
        )

        // Initialization error
        Section(
            title = stringResource(id = R.string.help_connection_initialization_error_title),
            message = buildString {
                append(
                    stringResource(
                        id = R.string.help_pairing_connection_initialization_error_message_1,
                        stringResource(id = R.string.bluetooth_failed_to_register_app_message),
                        stringResource(id = R.string.bluetooth_failed_to_connect_to_device)
                    )
                )
                append("\n\n")
                append(stringResource(id = R.string.help_connection_initialization_error_check_1))
                append("\n")
                append(stringResource(id = R.string.help_connection_initialization_error_check_2))
                append("\n\n")
                append(stringResource(id = R.string.help_connection_initialization_error_message_2))
            }
        )

        // Connection failure
        Section(
            title = stringResource(id = R.string.help_device_failed_connection_title),
            message = buildString {
                append(stringResource(id = R.string.help_pairing_device_failed_connection_message_1))
                append("\n\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_1))
                append("\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_2))
                append("\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_3))
            }
        )
    }
}

@Composable
fun RemoteScreenHelpModalBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HelpModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {

        Spacer(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_large))
        )

        // Remote control buttons
        Section(
            title = stringResource(id = R.string.help_remote_control_buttons_are_not_working_title),
            message = buildString {
                append(stringResource(id = R.string.help_remote_control_buttons_are_not_working_message))
                append("\n\n")
                append(stringResource(id = R.string.help_remote_control_buttons_are_not_working_check_1))
                append("\n")
                append(stringResource(id = R.string.help_remote_control_buttons_are_not_working_check_2))
                append("\n")
                append(stringResource(id = R.string.help_remote_control_buttons_are_not_working_check_3))
                append("\n")
                append(stringResource(id = R.string.help_remote_control_buttons_are_not_working_check_4))
            }
        )

        // Keyboard
        Section(
            title = stringResource(id = R.string.keyboard),
            message = stringResource(id = R.string.help_keyboard_wrong_character_sent_message)
        )
    }
}

@Composable
private fun Section(
    title: String,
    message: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        TextTitleSecondary(
            text = title
        )
        TextTitleTertiary(
            text = message,
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_large))
        )
    }
}