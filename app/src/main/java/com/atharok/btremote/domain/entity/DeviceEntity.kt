package com.atharok.btremote.domain.entity

import android.bluetooth.BluetoothClass.Device
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bluetooth
import androidx.compose.material.icons.rounded.Computer
import androidx.compose.material.icons.rounded.DeviceUnknown
import androidx.compose.material.icons.rounded.HealthAndSafety
import androidx.compose.material.icons.rounded.MusicVideo
import androidx.compose.material.icons.rounded.Print
import androidx.compose.material.icons.rounded.Router
import androidx.compose.material.icons.rounded.Smartphone
import androidx.compose.material.icons.rounded.Toys
import androidx.compose.material.icons.rounded.Usb
import androidx.compose.material.icons.rounded.Watch
import androidx.compose.ui.graphics.vector.ImageVector

data class DeviceEntity(
    val name: String,
    val macAddress: String,
    private val category: Int
) {
    val imageVector: ImageVector = when(category) {
        Device.Major.COMPUTER -> Icons.Rounded.Computer
        Device.Major.PHONE -> Icons.Rounded.Smartphone
        Device.Major.NETWORKING -> Icons.Rounded.Router
        Device.Major.AUDIO_VIDEO -> Icons.Rounded.MusicVideo
        Device.Major.PERIPHERAL -> Icons.Rounded.Usb
        Device.Major.IMAGING -> Icons.Rounded.Print
        Device.Major.WEARABLE -> Icons.Rounded.Watch
        Device.Major.TOY -> Icons.Rounded.Toys
        Device.Major.HEALTH -> Icons.Rounded.HealthAndSafety
        Device.Major.UNCATEGORIZED -> Icons.Rounded.DeviceUnknown
        else -> Icons.Rounded.Bluetooth
    }
}
