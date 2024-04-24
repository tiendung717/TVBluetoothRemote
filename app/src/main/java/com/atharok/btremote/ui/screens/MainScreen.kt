package com.atharok.btremote.ui.screens

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothHidDevice
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atharok.btremote.R
import com.atharok.btremote.domain.entity.DeviceConnectionState
import com.atharok.btremote.domain.entity.DeviceEntity
import com.atharok.btremote.presentation.viewmodel.BluetoothHidViewModel
import com.atharok.btremote.presentation.viewmodel.BluetoothViewModel
import com.atharok.btremote.presentation.viewmodel.SettingsViewModel
import com.atharok.btremote.ui.components.SystemBroadcastReceiver
import com.atharok.btremote.ui.navigation.MainDestination
import com.atharok.btremote.ui.navigation.MainScreenNavigation
import com.atharok.btremote.ui.screens.mainScreens.BluetoothActivationScreen
import com.atharok.btremote.ui.screens.mainScreens.BluetoothScanningScreen
import com.atharok.btremote.ui.screens.mainScreens.ConnectingScreen
import com.atharok.btremote.ui.screens.mainScreens.DevicesSelectionScreen
import com.atharok.btremote.ui.screens.mainScreens.RemoteScreen

@Composable
fun MainScreen(
    openSettings: () -> Unit,
    bluetoothViewModel: BluetoothViewModel,
    bluetoothHidViewModel: BluetoothHidViewModel,
    settingsViewModel: SettingsViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val destination: MutableState<MainDestination> = rememberSaveable {
        mutableStateOf(
            if(bluetoothViewModel.isBluetoothEnable()) {
                if(bluetoothHidViewModel.getDeviceConnectionState().value.state == BluetoothHidDevice.STATE_CONNECTED) {
                    MainDestination.RemoteDestination
                } else {
                    MainDestination.DevicesSelectionDestination.apply { args = null }
                }
            } else {
                bluetoothHidViewModel.stopService(context)
                MainDestination.BluetoothActivationDestination.apply { args = null }
            }
        )
    }

    // ---- Navigation ----

    MainScreenNavigation(targetState = destination.value) {
        when(it) {
            MainDestination.BluetoothActivationDestination -> {
                BluetoothActivationScreen(
                    openSettings = openSettings,
                    modifier = modifier
                )
            }
            MainDestination.DevicesSelectionDestination -> {
                val devices: List<DeviceEntity> by bluetoothViewModel.devicesEntityObserver.collectAsStateWithLifecycle()
                DevicesSelectionScreen(
                    devices = devices,
                    findBondedDevices = { bluetoothViewModel.findBondedDevices() },
                    startService = { device -> bluetoothHidViewModel.startService(context, device) },
                    stopService = { bluetoothHidViewModel.stopService(context) },
                    openBluetoothPairingNewDeviceScreen = {
                        destination.value =
                            MainDestination.BluetoothScanningDestination.apply { args = null }
                    },
                    openSettings = openSettings,
                    modifier = modifier
                )
            }
            MainDestination.BluetoothScanningDestination -> {
                val isDiscovering: Boolean by bluetoothViewModel.isDiscovering.collectAsStateWithLifecycle()
                BluetoothScanningScreen(
                    navigateUp = {
                        destination.value =
                            MainDestination.DevicesSelectionDestination.apply { args = null }
                    },
                    openSettings = openSettings,
                    isDiscovering = isDiscovering,
                    startDiscovery = { bluetoothViewModel.startDiscovery() },
                    cancelDiscovery = { bluetoothViewModel.cancelDiscovery() },
                    connectToDevice = { device -> bluetoothHidViewModel.startService(context, device) },
                    modifier = modifier
                )
            }
            MainDestination.ConnectingLoadingDestination -> {
                ConnectingScreen(
                    deviceName = destination.value.args as? String ?: "null",
                    cancelConnection = { bluetoothHidViewModel.stopService(context) },
                    modifier = Modifier.fillMaxSize()
                )
            }
            MainDestination.DisconnectingLoadingDestination -> {
                LoadingScreen(
                    message = stringResource(id = R.string.bluetooth_device_disconnecting_message, destination.value.args ?: "null"),
                    modifier = modifier.padding(dimensionResource(id = R.dimen.padding_large))
                )
            }
            MainDestination.RemoteDestination -> {
                RemoteScreen(
                    deviceName = destination.value.args as? String ?: "null",
                    openSettings = openSettings,
                    hidViewModel = bluetoothHidViewModel,
                    settingsViewModel = settingsViewModel,
                    modifier = modifier
                )
            }
        }
    }

    // ---- Handle navigation state ----
    val deviceConnectedState by bluetoothHidViewModel.getDeviceConnectionState().collectAsStateWithLifecycle()
    BluetoothDeviceConnectionState(
        deviceConnectedState = deviceConnectedState,
        connectedScreen = {
            destination.value = MainDestination.RemoteDestination.apply { args = it }
        },
        disconnectedScreen = {
            var isBluetoothEnabled: Boolean by remember { mutableStateOf(bluetoothViewModel.isBluetoothEnable()) }

            CheckBluetoothActivation {
                isBluetoothEnabled = it
            }

            if(isBluetoothEnabled) {
                if(destination.value != MainDestination.BluetoothScanningDestination) {
                    destination.value = MainDestination.DevicesSelectionDestination
                }
            } else {
                destination.value = MainDestination.BluetoothActivationDestination
            }
        },
        connectingScreen = {
            destination.value = MainDestination.ConnectingLoadingDestination.apply { args = it }
        },
        disconnectingScreen = {
            destination.value = MainDestination.DisconnectingLoadingDestination.apply { args = it }
        }
    )
}

@Composable
private fun BluetoothDeviceConnectionState(
    deviceConnectedState: DeviceConnectionState,
    connectedScreen: @Composable (deviceName: String) -> Unit,
    disconnectedScreen: @Composable (deviceName: String) -> Unit,
    connectingScreen: @Composable (deviceName: String) -> Unit,
    disconnectingScreen: @Composable (deviceName: String) -> Unit
) {
    when(deviceConnectedState.state) {
        BluetoothHidDevice.STATE_CONNECTED -> connectedScreen(deviceConnectedState.deviceName)
        BluetoothHidDevice.STATE_DISCONNECTED -> disconnectedScreen(deviceConnectedState.deviceName)
        BluetoothHidDevice.STATE_CONNECTING -> connectingScreen(deviceConnectedState.deviceName)
        BluetoothHidDevice.STATE_DISCONNECTING -> disconnectingScreen(deviceConnectedState.deviceName)
    }
}

@Composable
private fun CheckBluetoothActivation(
    update: (isBluetoothEnabled: Boolean) -> Unit
) {
    SystemBroadcastReceiver(systemAction = BluetoothAdapter.ACTION_STATE_CHANGED) { intent ->
        if(intent?.action == BluetoothAdapter.ACTION_STATE_CHANGED) {
            val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
            update(state == BluetoothAdapter.STATE_ON)
        }
    }
}