package com.atharok.btremote.domain.entity.keyboard.layout

// https://source.android.com/docs/core/interaction/input/keyboard-devices#hid-keyboard-and-keypad-page-0x07
abstract class KeyboardLayout {
    companion object {
        val KEYBOARD_KEY_NONE by lazy { byteArrayOf(0x00, 0x00) }
        val KEYBOARD_KEY_ENTER by lazy { byteArrayOf(0x00, 0x28) }
        val KEYBOARD_KEY_ESCAPE by lazy { byteArrayOf(0x00, 0x29) }
        val KEYBOARD_KEY_DELETE by lazy { byteArrayOf(0x00, 0x2A) }
        val KEYBOARD_KEY_SPACE_BAR by lazy { byteArrayOf(0x00, 0x2C) }
        val KEYBOARD_KEY_LEFT by lazy { byteArrayOf(0x00, 0x50) }
        val KEYBOARD_KEY_RIGHT by lazy { byteArrayOf(0x00, 0x4F) }
    }

    // KEY_NONE: 0x00
    // KEY_LEFT_SHIFT: 0x02
    // KEY_ALT_GR: 0x40

    private val keys: Map<Char, ByteArray> by lazy { keyboardKeys + extraKeys }

    protected abstract val keyboardKeys: Map<Char, ByteArray>
    protected abstract val extraKeys: Map<Char, ByteArray>

    fun getKeyboardKey(key: Char): ByteArray = keys[key] ?: KEYBOARD_KEY_NONE
}