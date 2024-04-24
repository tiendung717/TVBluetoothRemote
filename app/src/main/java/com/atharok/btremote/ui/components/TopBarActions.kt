package com.atharok.btremote.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.BluetoothSearching
import androidx.compose.material.icons.automirrored.rounded.HelpOutline
import androidx.compose.material.icons.rounded.ControlCamera
import androidx.compose.material.icons.rounded.Keyboard
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Mouse
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R

// ---- Actions ----

@Composable
private fun TopAppBarAction(
    onClick: () -> Unit,
    image: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = image,
            contentDescription = contentDescription
        )
    }
}

@Composable
fun NavigateUpAction(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarAction(
        onClick = navigateUp,
        image = Icons.AutoMirrored.Rounded.ArrowBack,
        contentDescription = stringResource(id = R.string.back),
        modifier = modifier
    )
}

@Composable
fun SettingsAction(
    openSettingsScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarAction(
        onClick = openSettingsScreen,
        image = Icons.Rounded.Settings,
        contentDescription = stringResource(id = R.string.settings),
        modifier = modifier
    )
}

@Composable
fun PairingNewDeviceAction(
    openBluetoothPairingScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarAction(
        onClick = openBluetoothPairingScreen,
        image = Icons.AutoMirrored.Rounded.BluetoothSearching,
        contentDescription = stringResource(id = R.string.pairing_a_device),
        modifier = modifier
    )
}

@Composable
fun HelpAction(
    showHelp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarAction(
        onClick = showHelp,
        image = Icons.AutoMirrored.Rounded.HelpOutline,
        contentDescription = stringResource(id = R.string.help),
        modifier = modifier
    )
}

@Composable
fun RefreshAction(
    refresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarAction(
        onClick = refresh,
        image = Icons.Rounded.Refresh,
        contentDescription = stringResource(id = R.string.refresh),
        modifier = modifier
    )
}

@Composable
fun KeyboardAction(
    showKeyboard: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarAction(
        onClick = showKeyboard,
        image = Icons.Rounded.Keyboard,
        contentDescription = stringResource(id = R.string.keyboard),
        modifier = modifier
    )
}

@Composable
fun DirectionButtonsAction(
    showDirectionButtons: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarAction(
        onClick = showDirectionButtons,
        image = Icons.Rounded.ControlCamera,
        contentDescription = stringResource(id = R.string.direction_arrows),
        modifier = modifier
    )
}

@Composable
fun MouseAction(
    showMousePad: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarAction(
        onClick = showMousePad,
        image = Icons.Rounded.Mouse,
        contentDescription = stringResource(id = R.string.mouse),
        modifier = modifier
    )
}

@Composable
fun MoreAction(
    showMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarAction(
        onClick = showMenu,
        image = Icons.Rounded.MoreVert,
        contentDescription = stringResource(id = R.string.more),
        modifier = modifier
    )
}