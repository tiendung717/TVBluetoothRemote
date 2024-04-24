package com.atharok.btremote.ui.screens

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.ui.components.SimpleDialog

@Composable
fun BluetoothNotSupportScreen() {
    val activity = (LocalContext.current as? Activity)
    SimpleDialog(
        confirmButtonText = null,
        dismissButtonText = stringResource(id = R.string.close),
        onConfirmation = { activity?.finish() },
        onDismissRequest = { activity?.finish() },
        dialogTitle = stringResource(id = R.string.error),
        dialogText = stringResource(id = R.string.bluetooth_not_supported_message)
    )
}