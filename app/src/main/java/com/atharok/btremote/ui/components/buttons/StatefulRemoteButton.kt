package com.atharok.btremote.ui.components.buttons

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback

@Composable
fun StatefulRemoteButton(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    content: @Composable (interactionSource: MutableInteractionSource) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val interactions = remember { mutableStateListOf<Interaction>() }
    val haptic = LocalHapticFeedback.current

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    interactions.add(interaction)
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                }
                is PressInteraction.Release -> {
                    interactions.remove(interaction.press)
                }
                is PressInteraction.Cancel -> {
                    interactions.remove(interaction.press)
                }
            }
        }
    }

    val isPressed by interactionSource.collectIsPressedAsState()
    if(isPressed) touchDown() else touchUp()
    content(interactionSource)
}