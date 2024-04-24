package com.atharok.btremote.presentation.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.atharok.btremote.common.utils.DEVICE_ADDRESS_KEY
import com.atharok.btremote.common.utils.DEVICE_NAME_KEY
import com.atharok.btremote.common.utils.KEYBOARD_REPORT_ID
import com.atharok.btremote.common.utils.MOUSE_REPORT_ID
import com.atharok.btremote.common.utils.REMOTE_REPORT_ID
import com.atharok.btremote.domain.entity.DeviceConnectionState
import com.atharok.btremote.domain.entity.DeviceEntity
import com.atharok.btremote.domain.entity.MouseInput
import com.atharok.btremote.domain.entity.keyboard.layout.KeyboardLayout
import com.atharok.btremote.domain.usecases.BluetoothHidUseCase
import com.atharok.btremote.presentation.services.BluetoothHidService
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.roundToInt

class BluetoothHidViewModel(
    private val useCase: BluetoothHidUseCase
): ViewModel() {

    fun startService(context: Context, device: DeviceEntity) {
        val serviceIntent = Intent(context, BluetoothHidService::class.java).apply {
            putExtra(DEVICE_NAME_KEY, device.name)
            putExtra(DEVICE_ADDRESS_KEY, device.macAddress)
        }
        context.startForegroundService(serviceIntent)
    }

    fun stopService(context: Context) {
        val serviceIntent = Intent(context, BluetoothHidService::class.java)
        context.stopService(serviceIntent)
    }

    fun getBluetoothDeviceName(): String? = useCase.getBluetoothDeviceName()

    fun getDeviceConnectionState(): StateFlow<DeviceConnectionState> =
        useCase.getDeviceConnectionState()

    // ---- Send ----

    fun sendRemoteKeyReport(bytes: ByteArray): Boolean = sendReport(REMOTE_REPORT_ID, bytes)

    fun sendMouseKeyReport(bytes: ByteArray): Boolean = sendReport(MOUSE_REPORT_ID, bytes)

    fun sendMouseKeyReport(
        input: MouseInput = MouseInput.NONE,
        x: Float = 0f,
        y: Float = 0f,
        wheel: Float
    ): Boolean {
        val bytes: ByteArray = when(input) {
            MouseInput.NONE ->
                byteArrayOf(0x00, x.roundToInt().toByte(), y.roundToInt().toByte(), 0x00)
            MouseInput.PAD_TAP, MouseInput.MOUSE_CLICK_LEFT ->
                byteArrayOf(0x01, x.roundToInt().toByte(), y.roundToInt().toByte(), 0x00)
            MouseInput.MOUSE_CLICK_RIGHT ->
                byteArrayOf(0x02, x.roundToInt().toByte(), y.roundToInt().toByte(), 0x00)
            MouseInput.MOUSE_CLICK_MIDDLE ->
                byteArrayOf(0x04, x.roundToInt().toByte(), y.roundToInt().toByte(), 0x00)
            MouseInput.MOUSE_WHEEL ->
                byteArrayOf(0x00, 0x00, 0x00, wheel.roundToInt().toByte())
        }
        return sendReport(MOUSE_REPORT_ID, bytes)
    }

    fun sendKeyboardKeyReport(bytes: ByteArray): Boolean = sendReport(KEYBOARD_REPORT_ID, bytes)

    fun sendTextReport(text: String, keyboardLayout: KeyboardLayout): Boolean {
        return useCase.sendTextReport(text, keyboardLayout)
    }

    private fun sendReport(id: Int, bytes: ByteArray): Boolean {
        return useCase.sendReport(id, bytes)
    }
}