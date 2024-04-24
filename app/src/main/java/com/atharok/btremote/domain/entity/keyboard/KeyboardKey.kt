package com.atharok.btremote.domain.entity.keyboard

enum class KeyboardKey(val byte: Byte) {
    ROW_1_KEY_00(0x35), // `
    ROW_1_KEY_01(0x1E), // 1
    ROW_1_KEY_02(0x1F), // 2
    ROW_1_KEY_03(0x20), // 3
    ROW_1_KEY_04(0x21), // 4
    ROW_1_KEY_05(0x22), // 5
    ROW_1_KEY_06(0x23), // 6
    ROW_1_KEY_07(0x24), // 7
    ROW_1_KEY_08(0x25), // 8
    ROW_1_KEY_09(0x26), // 9
    ROW_1_KEY_10(0x27), // 0
    ROW_1_KEY_11(0x2D), // -
    ROW_1_KEY_12(0x2E), // =

    ROW_2_KEY_00(0x14), // q
    ROW_2_KEY_01(0x1A), // w
    ROW_2_KEY_02(0x08), // e
    ROW_2_KEY_03(0x15), // r
    ROW_2_KEY_04(0x17), // t
    ROW_2_KEY_05(0x1C), // y
    ROW_2_KEY_06(0x18), // u
    ROW_2_KEY_07(0x0C), // i
    ROW_2_KEY_08(0x12), // o
    ROW_2_KEY_09(0x13), // p
    ROW_2_KEY_10(0x2F), // [
    ROW_2_KEY_11(0x30), // ]

    ROW_3_KEY_00(0x04), // a
    ROW_3_KEY_01(0x16), // s
    ROW_3_KEY_02(0x07), // d
    ROW_3_KEY_03(0x09), // f
    ROW_3_KEY_04(0x0A), // g
    ROW_3_KEY_05(0x0B), // h
    ROW_3_KEY_06(0x0D), // j
    ROW_3_KEY_07(0x0E), // k
    ROW_3_KEY_08(0x0F), // l
    ROW_3_KEY_09(0x33), // ;
    ROW_3_KEY_10(0x34), // '
    ROW_3_KEY_11(0x31), // \

    ROW_4_KEY_00(0x64), // <
    ROW_4_KEY_01(0x1D), // z
    ROW_4_KEY_02(0x1B), // x
    ROW_4_KEY_03(0x06), // c
    ROW_4_KEY_04(0x19), // v
    ROW_4_KEY_05(0x05), // b
    ROW_4_KEY_06(0x11), // n
    ROW_4_KEY_07(0x10), // m
    ROW_4_KEY_08(0x36), // ,
    ROW_4_KEY_09(0x37), // .
    ROW_4_KEY_10(0x38), // /
}