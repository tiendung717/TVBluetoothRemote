package com.atharok.btremote.ui.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.atharok.btremote.presentation.viewmodel.BluetoothHidViewModel
import com.atharok.btremote.presentation.viewmodel.BluetoothViewModel
import com.atharok.btremote.presentation.viewmodel.SettingsViewModel
import com.atharok.btremote.ui.components.OnLifecycleEvent
import com.atharok.btremote.ui.navigation.AppNavDestination
import com.atharok.btremote.ui.navigation.AppNavHost
import com.atharok.btremote.ui.screens.BluetoothNotSupportScreen
import com.atharok.btremote.ui.screens.BluetoothPermissionsScreen
import com.atharok.btremote.ui.screens.MainScreen
import com.atharok.btremote.ui.screens.SettingsScreen
import com.atharok.btremote.ui.screens.ThirdLibrariesScreen
import com.atharok.btremote.ui.theme.BtRemoteTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainActivityRootView(
    navController: NavHostController = rememberNavController(),
    settingsViewModel: SettingsViewModel = koinViewModel(),
    bluetoothViewModel: BluetoothViewModel = koinViewModel(),
    hidViewModel: BluetoothHidViewModel = koinViewModel(),
    openSettings: () -> Unit = {
        navController.navigate(AppNavDestination.SettingsDestination.route) {
            this.launchSingleTop = true
        }
    }
) {
    BtRemoteTheme(settingsViewModel) {
        Surface(modifier = Modifier.fillMaxSize()) {
            if(!bluetoothViewModel.isBluetoothSupported) {
                BluetoothNotSupportScreen()
            } else {

                // ---- NavHost ----

                AppNavHost(
                    navController = navController,
                    bluetoothPermissionsScreen = {
                        BluetoothPermissionsScreen(
                            permissions = bluetoothViewModel.getPermissions(),
                            arePermissionsGranted = {
                                bluetoothViewModel.arePermissionsGranted()
                            },
                            doAfterGrantPermissions = {
                                navController.navigate(AppNavDestination.MainDestination.route) {
                                    popUpTo(0) {
                                        this.saveState = false
                                    }
                                }
                            },
                            openSettings = openSettings,
                            modifier = Modifier
                        )
                    },
                    mainScreen = {
                        MainScreen(
                            openSettings = openSettings,
                            bluetoothViewModel = bluetoothViewModel,
                            bluetoothHidViewModel = hidViewModel,
                            settingsViewModel = settingsViewModel,
                            modifier = Modifier
                        )
                    },
                    settingsScreen = {
                        SettingsScreen(
                            navigateUp = { navController.navigateUp() },
                            openThirdLibrariesScreen = {
                                navController.navigate(AppNavDestination.ThirdLibrariesDestination.route)
                            },
                            settingsViewModel = settingsViewModel,
                            modifier = Modifier
                        )
                    },
                    thirdLibrariesScreen = {
                        ThirdLibrariesScreen(
                            navigateUp = { navController.navigateUp() },
                            modifier = Modifier
                        )
                    }
                )

                // ---- Lifecycle ----

                OnLifecycleEvent { _, event ->
                    when(event) {
                        Lifecycle.Event.ON_RESUME -> {
                            // Si une permission a été retirée par l'utilisateur pendant l'utilisation de l'application, on affiche l'écran de permissions
                            if(!bluetoothViewModel.arePermissionsGranted() && navController.currentDestination?.route != AppNavDestination.BluetoothPermissionsDestination.route) {
                                navController.navigate(AppNavDestination.BluetoothPermissionsDestination.route) {
                                    popUpTo(0) {
                                        this.saveState = false
                                    }
                                }
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}