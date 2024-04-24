package com.atharok.btremote.ui.screens.mainScreens

import android.bluetooth.BluetoothDevice
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.atharok.btremote.R
import com.atharok.btremote.common.extensions.parcelable
import com.atharok.btremote.common.utils.checkBluetoothConnectPermission
import com.atharok.btremote.domain.entity.DeviceEntity
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.BluetoothScanningScreenHelpModalBottomSheet
import com.atharok.btremote.ui.components.HelpAction
import com.atharok.btremote.ui.components.NavigateUpAction
import com.atharok.btremote.ui.components.OnLifecycleEvent
import com.atharok.btremote.ui.components.RefreshAction
import com.atharok.btremote.ui.components.SettingsAction
import com.atharok.btremote.ui.components.SystemBroadcastReceiver
import com.atharok.btremote.ui.components.TextTitleTertiary

@Composable
fun BluetoothScanningScreen(
    navigateUp: () -> Unit,
    openSettings: () -> Unit,
    isDiscovering: Boolean,
    startDiscovery: () -> Unit,
    cancelDiscovery: () -> Unit,
    connectToDevice: (DeviceEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    StatefulBluetoothScanningScreen(
        navigateUp = navigateUp,
        startDiscovery = startDiscovery,
        cancelDiscovery = cancelDiscovery
    ) { devices: List<DeviceEntity> ->
        var showHelpBottomSheet: Boolean by remember { mutableStateOf(false) }
        StatelessBluetoothScanningScreen(
            isDiscovering = isDiscovering,
            devices = devices,
            navigateUp = navigateUp,
            openSettings = openSettings,
            startDiscovery = startDiscovery,
            connectToDevice = connectToDevice,
            showHelpBottomSheet = showHelpBottomSheet,
            onShowHelpBottomSheetChanged = { showHelpBottomSheet = it },
            modifier = modifier
        )
    }
}

@Composable
private fun StatefulBluetoothScanningScreen(
    navigateUp: () -> Unit,
    startDiscovery: () -> Unit,
    cancelDiscovery: () -> Unit,
    contents: @Composable (devices: List<DeviceEntity>) -> Unit
) {
    BackHandler(enabled = true, onBack = navigateUp)

    val devices = remember { mutableStateListOf<DeviceEntity>() }

    DisposableEffect(Unit) {
        startDiscovery()
        onDispose {
            cancelDiscovery()
        }
    }

    OnLifecycleEvent { _, event ->
        when(event) {
            Lifecycle.Event.ON_PAUSE -> cancelDiscovery()
            else -> {}
        }
    }

    val context = LocalContext.current

    SystemBroadcastReceiver(systemAction = BluetoothDevice.ACTION_FOUND) { intent ->
        if(intent?.action == BluetoothDevice.ACTION_FOUND) {
            intent.parcelable(BluetoothDevice.EXTRA_DEVICE, BluetoothDevice::class.java)?.let { device: BluetoothDevice ->
                if(checkBluetoothConnectPermission(context)) {
                    if(!devices.any { it.macAddress == device.address }) {
                        devices.add(
                            DeviceEntity(
                                name = device.name ?: "null",
                                macAddress = device.address ?: "null",
                                category = device.bluetoothClass.majorDeviceClass
                            )
                        )
                    }
                }
            }
        }
    }

    contents(devices.toList())
}

@Composable
private fun StatelessBluetoothScanningScreen(
    isDiscovering: Boolean,
    devices: List<DeviceEntity>,
    navigateUp: () -> Unit,
    openSettings: () -> Unit,
    startDiscovery: () -> Unit,
    connectToDevice: (DeviceEntity) -> Unit,
    showHelpBottomSheet: Boolean,
    onShowHelpBottomSheetChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    AppScaffold(
        title = stringResource(id = R.string.pairing_a_device),
        modifier = modifier,
        navigateUp = {
            NavigateUpAction(navigateUp)
        },
        topBarActions = {
            RefreshAction(
                refresh = startDiscovery
            )
            HelpAction(
                showHelp = { onShowHelpBottomSheetChanged(!showHelpBottomSheet) }
            )
            SettingsAction(openSettings)
        }
    ) { innerPadding ->

        BluetoothScanningView(
            isDiscovering = isDiscovering,
            devices = devices,
            connect = connectToDevice,
            modifier = Modifier,
            innerPadding = innerPadding
        )

        HelpBottomSheet(
            showHelpBottomSheet = showHelpBottomSheet,
            onShowHelpBottomSheetChanged = onShowHelpBottomSheetChanged
        )
    }
}

@Composable
private fun BluetoothScanningView(
    isDiscovering: Boolean,
    devices: List<DeviceEntity>,
    connect: (DeviceEntity) -> Unit,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(0.dp)
) {
    DevicesListView(
        devices = devices,
        onItemClick = connect,
        modifier = modifier,
        contentPadding = innerPadding,
        topContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_standard))
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_small),
                        top = dimensionResource(id = R.dimen.padding_small)
                    )
            ) {
                if(isDiscovering || devices.isNotEmpty()) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_standard))
                    ) {
                        TextTitleTertiary(
                            text = stringResource(id = R.string.available_devices),
                        )
                        if(isDiscovering) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.5.dp
                            )
                        }
                    }
                } else {
                    TextTitleTertiary(
                        text = stringResource(id = R.string.bluetooth_pairing_device_not_found),
                        modifier = Modifier
                    )
                }
            }
        }
    )
}

@Composable
private fun HelpBottomSheet(
    showHelpBottomSheet: Boolean,
    onShowHelpBottomSheetChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    if(showHelpBottomSheet) {
        BluetoothScanningScreenHelpModalBottomSheet(
            onDismissRequest = { onShowHelpBottomSheetChanged(false) },
            modifier = modifier
        )
    }
}