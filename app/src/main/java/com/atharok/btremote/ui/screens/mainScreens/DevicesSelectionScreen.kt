package com.atharok.btremote.ui.screens.mainScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.atharok.btremote.R
import com.atharok.btremote.domain.entity.DeviceEntity
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.CustomCard
import com.atharok.btremote.ui.components.DevicesSelectionScreenHelpModalBottomSheet
import com.atharok.btremote.ui.components.HelpAction
import com.atharok.btremote.ui.components.PairingNewDeviceAction
import com.atharok.btremote.ui.components.SettingsAction
import com.atharok.btremote.ui.components.TextTitleSecondary
import com.atharok.btremote.ui.components.TextTitleTertiary

@Composable
fun DevicesSelectionScreen(
    devices: List<DeviceEntity>,
    findBondedDevices: () -> Unit,
    startService: (DeviceEntity) -> Unit,
    stopService: () -> Unit,
    openBluetoothPairingNewDeviceScreen: () -> Unit,
    openSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showHelpBottomSheet: Boolean by remember { mutableStateOf(false) }
    StatelessDevicesSelectionScreen(
        devices = devices,
        findBondedDevices = findBondedDevices,
        startService = startService,
        stopService = stopService,
        openBluetoothPairingNewDeviceScreen = openBluetoothPairingNewDeviceScreen,
        openSettings = openSettings,
        showHelpBottomSheet = showHelpBottomSheet,
        onShowHelpBottomSheetChanged = { showHelpBottomSheet = it },
        modifier = modifier
    )
}

@Composable
private fun StatelessDevicesSelectionScreen(
    devices: List<DeviceEntity>,
    findBondedDevices: () -> Unit,
    startService: (DeviceEntity) -> Unit,
    stopService: () -> Unit,
    openBluetoothPairingNewDeviceScreen: () -> Unit,
    openSettings: () -> Unit,
    showHelpBottomSheet: Boolean,
    onShowHelpBottomSheetChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        stopService()
        findBondedDevices()
    }

    AppScaffold(
        title = stringResource(id = R.string.devices),
        modifier = modifier,
        topBarActions = {
            PairingNewDeviceAction(openBluetoothPairingNewDeviceScreen)
            HelpAction(
                showHelp = { onShowHelpBottomSheetChanged(!showHelpBottomSheet) }
            )
            SettingsAction(openSettings)
        }
    ) { innerPadding ->

        DevicesListView(
            devices = devices,
            onItemClick = startService,
            modifier = Modifier,
            contentPadding = innerPadding,
            topContent = {
                TextTitleTertiary(
                    text = stringResource(id = R.string.paired_devices),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_standard))
                        .padding(
                            start = dimensionResource(id = R.dimen.padding_small),
                            top = dimensionResource(id = R.dimen.padding_small)
                        )
                )
            }
        )

        HelpBottomSheet(
            showHelpBottomSheet = showHelpBottomSheet,
            onShowHelpBottomSheetChanged = onShowHelpBottomSheetChanged
        )
    }
}

@Composable
fun DevicesListView(
    devices: List<DeviceEntity>,
    onItemClick: (DeviceEntity) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    topContent: @Composable LazyItemScope.() -> Unit = {}
) {
    CustomLazyColumn(
        list = devices,
        modifier = modifier,
        contentPadding = contentPadding,
        item = { device ->
            DeviceItemView(
                name = device.name,
                macAddress = device.macAddress,
                icon = device.imageVector,
                onClick = { onItemClick(device) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_standard),
                        vertical = dimensionResource(id = R.dimen.padding_small)
                    )
            )
        },
        topContent = topContent
    )
}

@Composable
private fun DeviceItemView(
    name: String,
    macAddress: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomCard(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius)),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() }
                .padding(dimensionResource(id = R.dimen.padding_medium))
                /*.height(IntrinsicSize.Max)*/,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Image(
                imageVector = icon,
                contentDescription = "",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(dimensionResource(id = R.dimen.padding_standard)),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                TextTitleSecondary(text = name)
                TextTitleTertiary(text = macAddress)
            }
        }
    }
}

@Composable
private fun <T> CustomLazyColumn(
    list: List<T>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    item: @Composable (T) -> Unit = {},
    topContent: @Composable LazyItemScope.() -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        item { topContent() }
        items(list) { item -> item(item) }
    }
}

@Composable
private fun HelpBottomSheet(
    showHelpBottomSheet: Boolean,
    onShowHelpBottomSheetChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    if(showHelpBottomSheet) {
        DevicesSelectionScreenHelpModalBottomSheet(
            onDismissRequest = { onShowHelpBottomSheetChanged(false) },
            modifier = modifier
        )
    }
}