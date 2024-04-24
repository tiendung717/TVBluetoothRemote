package com.atharok.btremote.ui.components.buttons

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize
import com.atharok.btremote.ui.components.CustomCard
import com.atharok.btremote.ui.components.TextRemoteNumber

@Composable
fun DialPadButton(
    text: String,
    byteArray: ByteArray,
    sendRemoteKey: (bytes: ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    CustomCard(
        shape = CircleShape,
        modifier = modifier
    ) {
        StatefulRemoteButton(
            touchDown = { sendRemoteKey(byteArray) },
            touchUp = { sendRemoteKey(byteArrayOf(0x00.toByte(), 0x00.toByte())) }
        ) {

            var boxSize by remember { mutableStateOf(Size.Zero) }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        interactionSource = it,
                        indication = LocalIndication.current,
                        onClick = {}
                    )
                    .onGloballyPositioned { layoutCoordinates -> boxSize = layoutCoordinates.size.toSize() },
                contentAlignment = Alignment.Center
            ) {
                TextRemoteNumber(
                    text = text,
                    fontSize = with(LocalDensity.current) { (0.45f * boxSize.height).toSp() }
                )
            }
        }
    }
}