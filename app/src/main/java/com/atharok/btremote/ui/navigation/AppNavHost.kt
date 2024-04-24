package com.atharok.btremote.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

private val enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInHorizontally(
        initialOffsetX = { fullWidth -> fullWidth }
    )
}

private val exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutHorizontally (
        targetOffsetX = { fullWidth -> -fullWidth }
    )
}

private val popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInHorizontally(
        initialOffsetX = { fullWidth -> -fullWidth }
    )
}

private val popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutHorizontally (
        targetOffsetX = { fullWidth -> fullWidth }
    )
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    bluetoothPermissionsScreen: @Composable () -> Unit,
    mainScreen: @Composable () -> Unit,
    settingsScreen: @Composable () -> Unit,
    thirdLibrariesScreen: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppNavDestination.BluetoothPermissionsDestination.route,
        modifier = modifier
    ) {

        composable(
            route = AppNavDestination.BluetoothPermissionsDestination.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            bluetoothPermissionsScreen()
        }

        composable(
            route = AppNavDestination.MainDestination.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            mainScreen()
        }

        composable(
            route = AppNavDestination.SettingsDestination.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            settingsScreen()
        }

        composable(
            route = AppNavDestination.ThirdLibrariesDestination.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            thirdLibrariesScreen()
        }
    }
}

fun NavHostController.navigateWithCustomOptions(
    route: String,
    saveState: Boolean = true,
    launchSingleTop: Boolean = true,
    restoreState: Boolean = true
) {
    this.navigate(route) {
        popUpTo(0) {
            this.saveState = saveState
        }
        this.launchSingleTop = launchSingleTop
        this.restoreState = restoreState
    }
}