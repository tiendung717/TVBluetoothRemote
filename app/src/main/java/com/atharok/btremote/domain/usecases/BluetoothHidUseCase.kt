package com.atharok.btremote.domain.usecases

import com.atharok.btremote.domain.entity.DeviceConnectionState
import com.atharok.btremote.domain.entity.keyboard.layout.KeyboardLayout
import com.atharok.btremote.domain.repositories.BluetoothHidProfileRepository
import kotlinx.coroutines.flow.StateFlow

class BluetoothHidUseCase(private val repository: BluetoothHidProfileRepository) {

    fun getBluetoothDeviceName(): String? = repository.getBluetoothDeviceName()

    fun getDeviceConnectionState(): StateFlow<DeviceConnectionState> {
        return repository.getDeviceConnectionState()
    }

    fun sendReport(id: Int, bytes: ByteArray): Boolean {
        return repository.sendReport(id, bytes)
    }

    fun sendTextReport(text: String, keyboardLayout: KeyboardLayout): Boolean {
        return repository.sendTextReport(text, keyboardLayout)
    }
}