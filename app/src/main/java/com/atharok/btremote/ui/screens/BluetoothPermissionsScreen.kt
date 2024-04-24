package com.atharok.btremote.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Key
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.ui.components.CheckMultiplePermissions
import com.atharok.btremote.ui.screens.mainScreens.ActivationScreen

@Composable
fun BluetoothPermissionsScreen(
    permissions: Array<String>,
    arePermissionsGranted: () -> Boolean,
    doAfterGrantPermissions: () -> Unit,
    openSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    StatefulBluetoothPermission(
        permissions = permissions,
        arePermissionsGranted = arePermissionsGranted,
        doAfterGrantPermissions = doAfterGrantPermissions
    ) {
        StatelessBluetoothPermission(
            grantPermission = it,
            openSettings = openSettings,
            modifier = modifier
        )
    }
}

@Composable
private fun StatefulBluetoothPermission(
    permissions: Array<String>,
    arePermissionsGranted: () -> Boolean,
    doAfterGrantPermissions: () -> Unit,
    permissionsScreen: @Composable (grantPermissions: () -> Unit) -> Unit
) {
    CheckMultiplePermissions(
        permissions = permissions,
        arePermissionsGranted = arePermissionsGranted,
        doAfterGrantPermissions = doAfterGrantPermissions,
        permissionsScreen = permissionsScreen
    )
}

@Composable
private fun StatelessBluetoothPermission(
    grantPermission: () -> Unit,
    openSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    ActivationScreen(
        topBarTitle = stringResource(id = R.string.permission),
        image = Icons.Rounded.Key,
        state = stringResource(id = R.string.bluetooth_permission_not_granted),
        message = stringResource(id = R.string.bluetooth_permission_message),
        buttonIcon = Icons.Rounded.Check,
        buttonText = stringResource(id = R.string.bluetooth_permission_button),
        buttonOnClick = grantPermission,
        openSettings = openSettings,
        modifier = modifier
    )
}