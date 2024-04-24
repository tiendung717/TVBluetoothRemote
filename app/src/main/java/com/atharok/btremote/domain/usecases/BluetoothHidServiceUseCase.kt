package com.atharok.btremote.domain.usecases

import com.atharok.btremote.domain.entity.HidConnectionResult
import com.atharok.btremote.domain.repositories.BluetoothHidProfileRepository

class BluetoothHidServiceUseCase(private val repository: BluetoothHidProfileRepository) {

    fun startHidProfile(deviceAddress: String, connectionResult: (HidConnectionResult) -> Unit) {
        repository.startHidProfile(deviceAddress, connectionResult)
    }

    fun stopHidProfile() {
        repository.stopHidProfile()
    }
}