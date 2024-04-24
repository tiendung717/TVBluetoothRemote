package com.atharok.btremote.ui.screens.mainScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.buttons.MaterialButton
import com.atharok.btremote.ui.screens.LoadingScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectingScreen(
    deviceName: String,
    cancelConnection: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppScaffold(
        title = stringResource(id = R.string.connection),
        modifier = modifier,
        scrollBehavior = null
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(
                    horizontal = dimensionResource(id = R.dimen.padding_large),
                    vertical = dimensionResource(id = R.dimen.padding_small)
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier)

            LoadingScreen(
                message = stringResource(id = R.string.bluetooth_device_connecting_message, deviceName),
                modifier = Modifier.padding( // Permet de centrer verticalement, indépendamment du inner Padding.
                    top = innerPadding.calculateBottomPadding() / 2f,
                    bottom = innerPadding.calculateTopPadding() / 2f
                )
            )

            MaterialButton(
                onClick = cancelConnection,
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = android.R.string.cancel),
                icon = Icons.Rounded.Cancel,
            )
        }
    }
}