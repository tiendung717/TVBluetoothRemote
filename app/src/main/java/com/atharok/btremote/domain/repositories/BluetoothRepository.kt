package com.atharok.btremote.domain.repositories

import com.atharok.btremote.domain.entity.DeviceEntity

interface BluetoothRepository {
    fun isBluetoothSupported(): Boolean
    fun isBluetoothEnabled(): Boolean
    fun getPermissions(): Array<String>
    fun arePermissionsGranted(): Boolean
    fun getBondedDevices(): List<DeviceEntity>
    fun startDiscovery(): Boolean
    fun cancelDiscovery(): Boolean
}