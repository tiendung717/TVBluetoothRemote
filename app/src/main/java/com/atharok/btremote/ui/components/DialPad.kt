package com.atharok.btremote.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.atharok.btremote.R
import com.atharok.btremote.domain.entity.keyboard.layout.KeyboardLayout
import com.atharok.btremote.ui.components.buttons.ChannelVerticalRemoteButtons
import com.atharok.btremote.ui.components.buttons.DialPadButton

@Composable
fun DialPadLayout(
    sendRemoteKeyReport: (bytes: ByteArray) -> Unit,
    sendNumberKeyReport: (bytes: ByteArray) -> Unit,
    keyboardLayout: KeyboardLayout,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            DialButton(value = "1", byteArray = keyboardLayout.getKeyboardKey('1'), sendNumberKeyReport = sendNumberKeyReport, modifier = Modifier.weight(1f))
            DialButton(value = "4", byteArray = keyboardLayout.getKeyboardKey('4'), sendNumberKeyReport = sendNumberKeyReport, modifier = Modifier.weight(1f))
            DialButton(value = "7", byteArray = keyboardLayout.getKeyboardKey('7'), sendNumberKeyReport = sendNumberKeyReport, modifier = Modifier.weight(1f))
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            DialButton(value = "2", byteArray = keyboardLayout.getKeyboardKey('2'), sendNumberKeyReport = sendNumberKeyReport, modifier = Modifier.weight(1f))
            DialButton(value = "5", byteArray = keyboardLayout.getKeyboardKey('5'), sendNumberKeyReport = sendNumberKeyReport, modifier = Modifier.weight(1f))
            DialButton(value = "8", byteArray = keyboardLayout.getKeyboardKey('8'), sendNumberKeyReport = sendNumberKeyReport, modifier = Modifier.weight(1f))
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            DialButton(value = "3", byteArray = keyboardLayout.getKeyboardKey('3'), sendNumberKeyReport = sendNumberKeyReport, modifier = Modifier.weight(1f))
            DialButton(value = "6", byteArray = keyboardLayout.getKeyboardKey('6'), sendNumberKeyReport = sendNumberKeyReport, modifier = Modifier.weight(1f))
            DialButton(value = "9", byteArray = keyboardLayout.getKeyboardKey('9'), sendNumberKeyReport = sendNumberKeyReport, modifier = Modifier.weight(1f))
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ChannelVerticalRemoteButtons(
                sendReport = sendRemoteKeyReport,
                modifier = Modifier
                    .weight(2f)
                    .padding(dimensionResource(id = R.dimen.padding_standard))
                    .align(Alignment.End)
            )
            DialButton(value = "0", byteArray = keyboardLayout.getKeyboardKey('0'), sendNumberKeyReport = sendNumberKeyReport, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun DialButton(
    value: String,
    byteArray: ByteArray,
    sendNumberKeyReport: (bytes: ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    DialPadButton(
        text = value,
        byteArray = byteArray,
        sendRemoteKey = sendNumberKeyReport,
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_standard))
    )
}