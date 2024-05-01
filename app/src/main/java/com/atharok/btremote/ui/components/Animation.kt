package com.atharok.btremote.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

private const val ANIMATED_CONTENT_DURATION = 250

@Composable
fun <T> FadeAnimatedContent(
    targetState: T,
    content: @Composable (targetState: T) -> Unit
) {
    AnimatedContent(
        targetState = targetState,
        transitionSpec = {
            fadeIn(
                animationSpec = tween(ANIMATED_CONTENT_DURATION),
            ) togetherWith fadeOut(
                animationSpec = tween(ANIMATED_CONTENT_DURATION)
            )
        },
        contentAlignment = Alignment.Center,
        label = "FadeAnimatedContent"
    ) { currentTargetState: T ->
        content(currentTargetState)
    }
}

@Composable
fun <T> SlideHorizontallyAnimatedContent(
    targetState: T,
    stackPosition: (T) -> Int,
    content: @Composable (targetState: T) -> Unit
) {
    AnimatedContent(
        targetState = targetState,
        transitionSpec = {
            if(stackPosition(this.targetState) > stackPosition(this.initialState)) {
                slideInHorizontally(
                    animationSpec = tween(ANIMATED_CONTENT_DURATION),
                    initialOffsetX = { fullWidth -> fullWidth }
                ) togetherWith slideOutHorizontally(
                    animationSpec = tween(ANIMATED_CONTENT_DURATION),
                    targetOffsetX = { fullWidth -> -fullWidth }
                )
            } else {
                slideInHorizontally(
                    animationSpec = tween(ANIMATED_CONTENT_DURATION),
                    initialOffsetX = { fullWidth -> -fullWidth }
                ) togetherWith slideOutHorizontally(
                    animationSpec = tween(ANIMATED_CONTENT_DURATION),
                    targetOffsetX = { fullWidth -> fullWidth }
                )
            }
        },
        label = "SlideHorizontallyAnimatedContent"
    ) { currentTargetState: T ->
        content(currentTargetState)
    }
}

@Composable
fun FadeAnimatedVisibility(
    visible: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        content()
    }
}