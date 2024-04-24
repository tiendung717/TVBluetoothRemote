package com.atharok.btremote.ui.components

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.changedToDown
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.atharok.btremote.R
import com.atharok.btremote.domain.entity.MouseInput
import com.atharok.btremote.ui.components.buttons.MouseButton
import kotlin.jvm.internal.Ref.BooleanRef
import kotlin.jvm.internal.Ref.FloatRef

@Composable
fun MousePadLayout(
    mouseSpeed: Float,
    shouldInvertMouseScrollingDirection: Boolean,
    sendMouseInput: (MouseInput, Float, Float, Float) -> Unit,
    modifier: Modifier = Modifier
) {
    var mouseInput: MouseInput = MouseInput.NONE
    var mouseX = 0f
    var mouseY = 0f
    var mouseWheel = 0f

    val mouseSpeedRef = remember { FloatRef() }
    mouseSpeedRef.element = mouseSpeed

    val shouldInvertMouseScrollingDirectionRef = remember { BooleanRef() }
    shouldInvertMouseScrollingDirectionRef.element = shouldInvertMouseScrollingDirection

    Column(modifier) {

        CustomCard(
            shape = RoundedCornerShape(
                topStart = dimensionResource(id = R.dimen.card_corner_radius),
                topEnd = dimensionResource(id = R.dimen.card_corner_radius),
                bottomEnd = 0.dp,
                bottomStart = 0.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f)
                .padding(dimensionResource(id = R.dimen.padding_standard)),
            surfaceElevation = dimensionResource(id = R.dimen.elevation)
        ) {
            MousePad(
                mouseSpeed = mouseSpeedRef,
                updateMouseInput = {
                    mouseInput = it
                    sendMouseInput(mouseInput, mouseX, mouseY, mouseWheel)
                    if (it == MouseInput.PAD_TAP) {
                        mouseInput = MouseInput.NONE
                        sendMouseInput(mouseInput, mouseX, mouseY, mouseWheel)
                    }
                },
                updateTouchPosition = { x: Float, y: Float ->
                    mouseX = x
                    mouseY = y
                    sendMouseInput(mouseInput, mouseX, mouseY, mouseWheel)
                },
                updateWheel = { wheel: Float ->
                    mouseInput = MouseInput.MOUSE_WHEEL
                    mouseWheel = wheel * if(shouldInvertMouseScrollingDirectionRef.element) -1f else 1f
                    sendMouseInput(mouseInput, mouseX, mouseY, mouseWheel)
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
                .padding(
                    start = dimensionResource(id = R.dimen.padding_standard),
                    top = 0.dp,
                    end = dimensionResource(id = R.dimen.padding_standard),
                    bottom = dimensionResource(id = R.dimen.padding_standard)
                )
        ) {
            // Start
            CustomCard(
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomEnd = 0.dp,
                    bottomStart = dimensionResource(id = R.dimen.card_corner_radius)
                ),
                modifier = Modifier
                    .weight(0.38f)
                    .fillMaxHeight(),
                surfaceElevation = dimensionResource(id = R.dimen.elevation)
            ) {
                MouseButton(
                    touchDown = {
                        mouseInput = MouseInput.MOUSE_CLICK_LEFT
                        sendMouseInput(mouseInput, mouseX, mouseY, mouseWheel)
                    },
                    touchUp = {
                        mouseInput = MouseInput.NONE
                        sendMouseInput(mouseInput, mouseX, mouseY, mouseWheel)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Center
            CustomCard(
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .weight(0.24f)
                    .fillMaxHeight()
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_standard)),
                surfaceElevation = dimensionResource(id = R.dimen.elevation)
            ) {
                MouseButton(
                    touchDown = {
                        mouseInput = MouseInput.MOUSE_CLICK_MIDDLE
                        sendMouseInput(mouseInput, mouseX, mouseY, mouseWheel)
                    },
                    touchUp = {
                        mouseInput = MouseInput.NONE
                        sendMouseInput(mouseInput, mouseX, mouseY, mouseWheel)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            // End
            CustomCard(
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomEnd = dimensionResource(id = R.dimen.card_corner_radius),
                    bottomStart = 0.dp
                ),
                modifier = Modifier
                    .weight(0.38f)
                    .fillMaxHeight(),
                surfaceElevation = dimensionResource(id = R.dimen.elevation)
            ) {
                MouseButton(
                    touchDown = {
                        mouseInput = MouseInput.MOUSE_CLICK_RIGHT
                        sendMouseInput(mouseInput, mouseX, mouseY, mouseWheel)
                    },
                    touchUp = {
                        mouseInput = MouseInput.NONE
                        sendMouseInput(mouseInput, mouseX, mouseY, mouseWheel)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun MousePad(
    mouseSpeed: FloatRef,
    updateMouseInput: (input: MouseInput) -> Unit,
    updateTouchPosition: (Float, Float) -> Unit,
    updateWheel: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .pointerInput(Unit) {
                awaitEachGesture {
                    while (true) {
                        val event = awaitPointerEvent()
                        val inputChanges = event.changes

                        when (inputChanges.size) {

                            // 1 finger
                            1 -> {
                                val inputChange = inputChanges.first()

                                moveMouse(
                                    mouseSpeed = mouseSpeed.element,
                                    inputChange = inputChange,
                                    updateTouchPosition = updateTouchPosition
                                )
                                doTap(
                                    inputChange = inputChange,
                                    updateMouseInput = updateMouseInput
                                )
                            }

                            // 2 fingers
                            2 -> {
                                doWheel(
                                    inputChanges = inputChanges,
                                    updateMouseInput = updateMouseInput,
                                    updateWheel = updateWheel
                                )
                            }
                        }
                    }
                }
            }
    )
}

private fun moveMouse(
    mouseSpeed: Float,
    inputChange: PointerInputChange,
    updateTouchPosition: (Float, Float) -> Unit
) {
    updateTouchPosition(
        (inputChange.position.x - inputChange.previousPosition.x) * mouseSpeed,
        (inputChange.position.y - inputChange.previousPosition.y) * mouseSpeed
    )
}

private var tapTimestamp: Long = 0L
private fun doTap(
    inputChange: PointerInputChange,
    updateMouseInput: (input: MouseInput) -> Unit
) {
    val currentTime = System.currentTimeMillis()

    when {
        inputChange.changedToDown() -> tapTimestamp = currentTime
        inputChange.changedToUp() -> {
            val position = inputChange.position
            val previousPosition= inputChange.previousPosition
            if(currentTime - tapTimestamp < 200 && position.x == previousPosition.x && position.y == previousPosition.y) {
                updateMouseInput(MouseInput.PAD_TAP)
            }
            tapTimestamp = 0L
        }
    }
}

private fun doWheel(
    inputChanges: List<PointerInputChange>,
    updateMouseInput: (input: MouseInput) -> Unit,
    updateWheel: (Float) -> Unit
) {
    var posY = 0f
    inputChanges.forEach {
        posY += it.position.y - it.previousPosition.y
    }

    when {
        posY != 0f -> updateWheel(posY / 10f)
        else -> updateMouseInput(MouseInput.NONE)
    }

    for (inputChange in inputChanges) {
        if(inputChange.changedToUp()) {
            updateMouseInput(MouseInput.NONE)
            break
        }
    }
}