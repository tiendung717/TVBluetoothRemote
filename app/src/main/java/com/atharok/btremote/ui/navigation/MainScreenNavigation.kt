package com.atharok.btremote.ui.navigation

import androidx.compose.runtime.Composable
import com.atharok.btremote.ui.components.SlideHorizontallyAnimatedContent

@Composable
fun MainScreenNavigation(
    targetState: MainDestination,
    content: @Composable (targetState: MainDestination) -> Unit
) {
    SlideHorizontallyAnimatedContent<MainDestination>(
        targetState = targetState,
        stackPosition = { it.position },
    ) { currentTargetState: MainDestination ->
        content(currentTargetState)
    }
}