package com.atharok.btremote.data.repositories

import com.atharok.btremote.data.bluetooth.BluetoothInteractions
import com.atharok.btremote.domain.entity.DeviceEntity
import com.atharok.btremote.domain.repositories.BluetoothRepository

class BluetoothRepositoryImpl(
    private val bluetoothInteractions: BluetoothInteractions
): BluetoothRepository {
    override fun isBluetoothSupported(): Boolean = bluetoothInteractions.isBluetoothSupported()

    override fun isBluetoothEnabled(): Boolean = bluetoothInteractions.isBluetoothEnabled()

    override fun getPermissions(): Array<String> = bluetoothInteractions.permissions

    override fun arePermissionsGranted(): Boolean = bluetoothInteractions.arePermissionsGranted()

    override fun getBondedDevices(): List<DeviceEntity> = bluetoothInteractions.getBondedDevices()

    override fun startDiscovery(): Boolean = bluetoothInteractions.startDiscoveryDevices()

    override fun cancelDiscovery(): Boolean = bluetoothInteractions.cancelDiscoveryDevices()
}