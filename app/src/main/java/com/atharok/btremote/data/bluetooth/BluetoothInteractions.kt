package com.atharok.btremote.data.bluetooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import com.atharok.btremote.common.utils.checkBluetoothConnectPermission
import com.atharok.btremote.common.utils.checkBluetoothScanPermission
import com.atharok.btremote.domain.entity.DeviceEntity

class BluetoothInteractions(
    private val context: Context,
    private val adapter: BluetoothAdapter?
) {
    // ---- Bluetooth initialisation ----

    fun isBluetoothSupported(): Boolean = adapter != null

    fun isBluetoothEnabled(): Boolean = adapter?.isEnabled ?: false

    // ---- Bluetooth permissions ----

    val permissions: Array<String> by lazy {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                arrayOf(
                    Manifest.permission.BLUETOOTH_CONNECT,
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.POST_NOTIFICATIONS
                )
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                arrayOf(
                    Manifest.permission.BLUETOOTH_CONNECT,
                    Manifest.permission.BLUETOOTH_SCAN
                )
            }
            else -> {
                arrayOf(
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            }
        }
    }

    fun arePermissionsGranted(): Boolean {
        return permissions.all {
            if(it == Manifest.permission.POST_NOTIFICATIONS) // Not mandatory
                true
            else
                ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    // ---- Get Bonded Devices ----
    fun getBondedDevices(): List<DeviceEntity> {
        val deviceEntities = mutableListOf<DeviceEntity>()
        if (checkBluetoothConnectPermission(context)) {
            adapter?.bondedDevices?.forEach { device ->
                deviceEntities.add(
                    DeviceEntity(
                        name = device.name ?: "null",
                        macAddress = device.address ?: "null",
                        category = device.bluetoothClass.majorDeviceClass
                    )
                )
            }
        }
        return deviceEntities
    }

    // ---- Discovery Devices ----

    fun startDiscoveryDevices(): Boolean {
        return if (checkBluetoothScanPermission(context)) {
            adapter?.startDiscovery() ?: false
        } else false
    }

    fun cancelDiscoveryDevices(): Boolean {
        return if (checkBluetoothScanPermission(context)) {
            adapter?.cancelDiscovery() ?: false
        } else false
    }
}