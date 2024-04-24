package com.atharok.btremote.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.HelpOutline
import androidx.compose.material.icons.rounded.LinkOff
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R

@Composable
private fun OverflowMenu(
    topBarAction: @Composable (showMenu: () -> Unit) -> Unit,
    content: @Composable (closeDropdownMenu: () -> Unit) -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    topBarAction { showMenu = !showMenu }

    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { showMenu = false }
    ) {
        content { showMenu = false }
    }
}

// ---- DropdownMenu ----

@Composable
fun MoreOverflowMenu(
    modifier: Modifier = Modifier,
    content: @Composable (closeDropdownMenu: () -> Unit) -> Unit
) {
    OverflowMenu(
        topBarAction = {
            MoreAction(
                showMenu = it,
                modifier = modifier
            )
        },
        content = content
    )
}

@Composable
fun KeyboardOverflowMenu(
    modifier: Modifier = Modifier,
    content: @Composable (closeDropdownMenu: () -> Unit) -> Unit
) {
    OverflowMenu(
        topBarAction = {
            KeyboardAction(
                showKeyboard = it,
                modifier = modifier
            )
        },
        content = content
    )
}

// ---- DropdownMenuItem ----

@Composable
private fun DefaultDropdownMenuItem(
    onClick: () -> Unit,
    image: ImageVector,
    title: String,
    modifier: Modifier = Modifier
) {
    DropdownMenuItem(
        text = {
            TextStandardPrimary(title)
        },
        onClick = onClick,
        modifier = modifier,
        leadingIcon = {
            Icon(
                imageVector = image,
                contentDescription = title
            )
        }
    )
}

@Composable
fun HelpDropdownMenuItem(
    showHelp: () -> Unit,
    modifier: Modifier = Modifier
) {
    DefaultDropdownMenuItem(
        onClick = showHelp,
        image = Icons.AutoMirrored.Rounded.HelpOutline,
        title = stringResource(id = R.string.help),
        modifier = modifier
    )
}

@Composable
fun SettingsDropdownMenuItem(
    showSettingsScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    DefaultDropdownMenuItem(
        onClick = showSettingsScreen,
        image = Icons.Rounded.Settings,
        title = stringResource(id = R.string.settings),
        modifier = modifier
    )
}

@Composable
fun DisconnectDropdownMenuItem(
    disconnect: () -> Unit,
    modifier: Modifier = Modifier
) {
    DefaultDropdownMenuItem(
        onClick = disconnect,
        image = Icons.Rounded.LinkOff,
        title = stringResource(id = R.string.disconnect),
        modifier = modifier
    )
}