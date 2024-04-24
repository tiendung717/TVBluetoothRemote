package com.atharok.btremote.ui.components.buttons

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds

@Composable
fun MouseButton(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        StatefulRemoteButton(
            touchDown = touchDown,
            touchUp = touchUp
        ) { interactionSource ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clipToBounds()
                    .clickable(
                        interactionSource = interactionSource,
                        indication = LocalIndication.current,
                        onClick = {}
                    )
            )
        }
    }
}