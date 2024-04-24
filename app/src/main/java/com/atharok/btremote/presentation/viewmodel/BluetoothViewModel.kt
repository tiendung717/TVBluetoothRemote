package com.atharok.btremote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atharok.btremote.common.extensions.capitalizeFirstChar
import com.atharok.btremote.domain.entity.DeviceEntity
import com.atharok.btremote.domain.usecases.BluetoothUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BluetoothViewModel(
    private val useCase: BluetoothUseCase
): ViewModel() {

    // ---- Bluetooth Initialization ----

    val isBluetoothSupported: Boolean = useCase.isBluetoothSupported()
    fun isBluetoothEnable(): Boolean = useCase.isBluetoothEnabled()

    // ---- Bluetooth permissions ----

    fun getPermissions(): Array<String> = useCase.getPermissions()

    fun arePermissionsGranted(): Boolean = useCase.arePermissionsGranted()

    // ---- Get Bonded Devices ----

    private val _devicesEntity: MutableStateFlow<List<DeviceEntity>> = MutableStateFlow(listOf())
    val devicesEntityObserver: StateFlow<List<DeviceEntity>> = _devicesEntity

    fun findBondedDevices() = viewModelScope.launch {
        _devicesEntity.value = useCase.getBondedDevices().sortedBy { it.name.capitalizeFirstChar() }
    }

    // ---- Discover Devices ----

    private val _isDiscovering: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isDiscovering: StateFlow<Boolean> = _isDiscovering

    private var startDiscoveryJob: Job? = null

    fun startDiscovery() {
        startDiscoveryJob?.cancel()
        useCase.cancelDiscovery()
        useCase.startDiscovery()
        _isDiscovering.value = true
        startDiscoveryJob = viewModelScope.launch {
            delay(12000L)
            cancelDiscovery()
        }
    }

    fun cancelDiscovery() {
        useCase.cancelDiscovery()
        _isDiscovering.value = false
    }
}