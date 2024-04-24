package com.atharok.btremote.data.repositories

import com.atharok.btremote.data.bluetooth.BluetoothHidProfile
import com.atharok.btremote.domain.entity.DeviceConnectionState
import com.atharok.btremote.domain.entity.HidConnectionResult
import com.atharok.btremote.domain.entity.keyboard.layout.KeyboardLayout
import com.atharok.btremote.domain.repositories.BluetoothHidProfileRepository
import kotlinx.coroutines.flow.StateFlow

class BluetoothHidProfileRepositoryImpl(private val hidProfile: BluetoothHidProfile): BluetoothHidProfileRepository {

    override fun startHidProfile(deviceAddress: String, connectionResult: (HidConnectionResult) -> Unit) {
        hidProfile.startBluetoothHidProfile(deviceAddress, connectionResult)
    }

    override fun stopHidProfile() {
        hidProfile.stopBluetoothHidProfile()
    }

    override fun getBluetoothDeviceName(): String? = hidProfile.getBluetoothDeviceName()

    override fun getDeviceConnectionState(): StateFlow<DeviceConnectionState> {
        return hidProfile.deviceConnectionState
    }

    override fun sendReport(id: Int, bytes: ByteArray): Boolean {
        return hidProfile.sendReport(id, bytes)
    }

    override fun sendTextReport(text: String, keyboardLayout: KeyboardLayout): Boolean {
        return hidProfile.sendTextReport(text, keyboardLayout)
    }
}