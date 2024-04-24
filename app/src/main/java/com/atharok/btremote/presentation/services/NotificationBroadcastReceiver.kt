package com.atharok.btremote.presentation.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action) {
            BluetoothHidService.ACTION_DISCONNECT_DEVICE -> {
                context?.stopService(Intent(context, BluetoothHidService::class.java))
            }
        }
    }
}