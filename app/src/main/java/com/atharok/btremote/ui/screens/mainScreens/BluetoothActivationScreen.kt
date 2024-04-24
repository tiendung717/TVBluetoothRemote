package com.atharok.btremote.ui.screens.mainScreens

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bluetooth
import androidx.compose.material.icons.rounded.BluetoothDisabled
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.REQUEST_ENABLE_BT
import com.atharok.btremote.common.utils.checkBluetoothConnectPermission
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.SettingsAction
import com.atharok.btremote.ui.components.TextTitleSecondary
import com.atharok.btremote.ui.components.TextTitleTertiary
import com.atharok.btremote.ui.components.buttons.MaterialButton

@Composable
fun BluetoothActivationScreen(
    openSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    ActivationScreen(
        topBarTitle = stringResource(id = R.string.activation),
        image = Icons.Rounded.BluetoothDisabled,
        state = stringResource(id = R.string.bluetooth_disabled_info),
        message = stringResource(id = R.string.bluetooth_disabled_message),
        buttonIcon = Icons.Rounded.Bluetooth,
        buttonText = stringResource(id = R.string.bluetooth_enabled_button),
        buttonOnClick = {
            if (checkBluetoothConnectPermission(context)) {
                (context as? Activity)?.let { activity ->
                    val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
                }
            }
        },
        openSettings = openSettings,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivationScreen(
    topBarTitle: String,
    image: ImageVector,
    state: String,
    message: String,
    buttonIcon: ImageVector,
    buttonText: String,
    buttonOnClick: () -> Unit,
    openSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppScaffold(
        title = topBarTitle,
        modifier = modifier,
        scrollBehavior = null,
        topBarActions = {
            SettingsAction(openSettings)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_large),
                        vertical = dimensionResource(id = R.dimen.padding_small)
                    ),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding( // Permet de centrer verticalement, indépendamment du inner Padding.
                            top = innerPadding.calculateBottomPadding() / 2f,
                            bottom = innerPadding.calculateTopPadding() / 2f
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        imageVector = image,
                        contentDescription = null,
                        modifier = Modifier
                            .size(96.dp)
                            .padding(vertical = dimensionResource(id = R.dimen.padding_standard))
                            .aspectRatio(1f),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                    )

                    TextTitleSecondary(
                        text = state,
                        Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_standard)),
                        textAlign = TextAlign.Center
                    )

                    TextTitleTertiary(
                        text = message,
                        textAlign = TextAlign.Center
                    )
                }

                MaterialButton(
                    onClick = buttonOnClick,
                    modifier = Modifier.fillMaxWidth(),
                    text = buttonText,
                    icon = buttonIcon,
                )
            }
        }
    }
}