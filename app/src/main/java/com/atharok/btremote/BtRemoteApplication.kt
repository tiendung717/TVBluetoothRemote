package com.atharok.btremote

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import com.atharok.btremote.common.injections.appModules
import com.atharok.btremote.common.utils.NOTIFICATION_CHANNEL_ID
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BtRemoteApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BtRemoteApplication)
            modules(appModules)
        }
        configureNotificationChannel()
    }

    private fun configureNotificationChannel() {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            getString(R.string.notification_channel_name),
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}