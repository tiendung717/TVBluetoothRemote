package com.atharok.btremote.ui.navigation

sealed class AppNavDestination(val route: String) {
    data object BluetoothPermissionsDestination: AppNavDestination(route = "bluetooth_permissions_route")
    data object MainDestination: AppNavDestination(route = "main_route")
    data object SettingsDestination: AppNavDestination(route = "settings_route")
    data object ThirdLibrariesDestination: AppNavDestination(route = "third_libraries_route")
}