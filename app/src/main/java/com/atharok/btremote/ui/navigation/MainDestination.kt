package com.atharok.btremote.ui.navigation

import java.io.Serializable

sealed class MainDestination(val position: Int, var args: Any? = null): Serializable {
    data object BluetoothActivationDestination: MainDestination(position = 0)
    data object DevicesSelectionDestination: MainDestination(position = 1)
    data object BluetoothScanningDestination: MainDestination(position = 2)
    data object ConnectingLoadingDestination: MainDestination(position = 3)
    data object DisconnectingLoadingDestination: MainDestination(position = 4)
    data object RemoteDestination: MainDestination(position = 5)

    protected fun readResolve(): Any = this
}