package com.atharok.btremote.ui.components

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun SystemBroadcastReceiver(
    systemAction: String,
    onSystemEvent: (intent: Intent?) -> Unit
) {
    val context = LocalContext.current
    val currentOnSystemEvent by rememberUpdatedState(onSystemEvent)

    DisposableEffect(context, systemAction) {
        val intentFilter = IntentFilter(systemAction)
        val broadcast = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                currentOnSystemEvent(intent)
            }
        }

        context.registerReceiver(broadcast, intentFilter)

        onDispose {
            context.unregisterReceiver(broadcast)
        }
    }
}

@Composable
fun OnLifecycleEvent(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, event ->
            eventHandler.value(owner, event)
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun CheckSinglePermission(
    permission: String,
    arePermissionGranted: () -> Boolean,
    doAfterGrantPermission: () -> Unit,
    permissionScreen: @Composable (grantPermissions: () -> Unit) -> Unit
) {
    var permissionIsGranted by remember { mutableStateOf(arePermissionGranted()) }

    if(permissionIsGranted) {
        doAfterGrantPermission()
    } else {
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isPermissionGranted: Boolean ->
                permissionIsGranted = isPermissionGranted
            }
        )

        permissionScreen {
            launcher.launch(permission)
        }
    }
}

@Composable
fun CheckMultiplePermissions(
    permissions: Array<String>,
    arePermissionsGranted: () -> Boolean,
    doAfterGrantPermissions: () -> Unit,
    permissionsScreen: @Composable (grantPermissions: () -> Unit) -> Unit
) {
    var permissionsAreGranted by remember { mutableStateOf(arePermissionsGranted()) }

    if(permissionsAreGranted) {
        doAfterGrantPermissions()
    } else {
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = { result: Map<String, Boolean> ->
                permissionsAreGranted = result.all {
                    if(it.key == Manifest.permission.POST_NOTIFICATIONS) // Not mandatory
                        true
                    else it.value
                }
            }
        )

        permissionsScreen {
            launcher.launch(permissions)
        }
    }
}