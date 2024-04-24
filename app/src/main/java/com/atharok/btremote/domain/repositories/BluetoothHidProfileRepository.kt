package com.atharok.btremote.domain.repositories

import com.atharok.btremote.domain.entity.DeviceConnectionState
import com.atharok.btremote.domain.entity.HidConnectionResult
import com.atharok.btremote.domain.entity.keyboard.layout.KeyboardLayout
import kotlinx.coroutines.flow.StateFlow

interface BluetoothHidProfileRepository {

    fun startHidProfile(deviceAddress: String, connectionResult: (HidConnectionResult) -> Unit)

    fun stopHidProfile()

    fun getBluetoothDeviceName(): String?

    fun getDeviceConnectionState(): StateFlow<DeviceConnectionState>

    fun sendReport(id: Int, bytes: ByteArray): Boolean

    fun sendTextReport(text: String, keyboardLayout: KeyboardLayout): Boolean
}