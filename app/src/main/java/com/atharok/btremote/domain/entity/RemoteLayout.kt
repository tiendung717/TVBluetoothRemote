package com.atharok.btremote.domain.entity

// https://source.android.com/docs/core/interaction/input/keyboard-devices#hid-consumer-page-0x0c
object RemoteLayout {
    val REMOTE_KEY_NONE by lazy { byteArrayOf(0x00, 0x00) }

    // Navigation
    val REMOTE_KEY_MENU_PICK by lazy { byteArrayOf(0x41, 0x00) }
    val REMOTE_KEY_MENU_UP by lazy { byteArrayOf(0x42, 0x00) }
    val REMOTE_KEY_MENU_DOWN by lazy { byteArrayOf(0x43, 0x00) }
    val REMOTE_KEY_MENU_LEFT by lazy { byteArrayOf(0x44, 0x00) }
    val REMOTE_KEY_MENU_RIGHT by lazy { byteArrayOf(0x45, 0x00) }

    // Multimedia
    val REMOTE_KEY_PLAY_PAUSE by lazy { byteArrayOf(0xCD.toByte(), 0x00) }
    val REMOTE_KEY_PREVIOUS by lazy { byteArrayOf(0xB4.toByte(), 0x00) }
    val REMOTE_KEY_NEXT by lazy { byteArrayOf(0xB3.toByte(), 0x00) }

    // Volume
    val REMOTE_KEY_VOLUME_INC by lazy { byteArrayOf(0xE9.toByte(), 0x00) }
    val REMOTE_KEY_VOLUME_DEC by lazy { byteArrayOf(0xEA.toByte(), 0x00) }
    val REMOTE_KEY_VOLUME_MUTE by lazy { byteArrayOf(0xE2.toByte(), 0x00) }

    // Brightness
    val REMOTE_KEY_BRIGHTNESS_INC by lazy { byteArrayOf(0x6F.toByte(), 0x00) }
    val REMOTE_KEY_BRIGHTNESS_DEC by lazy { byteArrayOf(0x70.toByte(), 0x00) }

    // Channel
    val REMOTE_KEY_CHANNEL_INC by lazy { byteArrayOf(0x9C.toByte(), 0x00) }
    val REMOTE_KEY_CHANNEL_DEC by lazy { byteArrayOf(0x9D.toByte(), 0x00) }

    // Others
    val REMOTE_KEY_HOME by lazy { byteArrayOf(0x23, 0x02) }
    val REMOTE_KEY_BACK by lazy { byteArrayOf(0x24, 0x02) }
    val REMOTE_KEY_STANDBY by lazy { byteArrayOf(0x30, 0x00) }
}