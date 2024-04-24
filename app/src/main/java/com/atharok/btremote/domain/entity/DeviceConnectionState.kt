package com.atharok.btremote.domain.entity

import android.bluetooth.BluetoothHidDevice

data class DeviceConnectionState(
    val state: Int = BluetoothHidDevice.STATE_DISCONNECTED,
    val deviceName: String = ""
)