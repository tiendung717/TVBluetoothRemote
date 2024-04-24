package com.atharok.btremote.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Backspace
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.rounded.KeyboardReturn
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.atharok.btremote.R
import com.atharok.btremote.domain.entity.keyboard.layout.KeyboardLayout
import com.atharok.btremote.ui.components.buttons.StatefulRemoteButton

@Composable
fun KeyboardView(
    mustClearInputField: Boolean,
    sendKeyboardKeyReport: (ByteArray) -> Unit,
    sendTextReport: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    StatefulKeyboardView { focusRequester, textState ->
        StatelessKeyboardView(
            mustClearInputField = mustClearInputField,
            focusRequester = focusRequester,
            text = textState.value,
            onTextChange = {
                textState.value = it
            },
            sendKeyboardKeyReport = sendKeyboardKeyReport,
            sendTextReport = sendTextReport,
            modifier = modifier
        )
    }
}

@Composable
private fun StatefulKeyboardView(
    content: @Composable (FocusRequester, textState: MutableState<String>) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val textState = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    content(focusRequester, textState)
}

@Composable
private fun StatelessKeyboardView(
    mustClearInputField: Boolean,
    focusRequester: FocusRequester,
    text: String,
    onTextChange: (String) -> Unit,
    sendKeyboardKeyReport: (ByteArray) -> Unit,
    sendTextReport: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_standard)),
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                value = text,
                onValueChange = onTextChange,
                modifier = Modifier.weight(1f).focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        sendTextReport(text)
                        sendKeyboardKeyReport(KeyboardLayout.KEYBOARD_KEY_ENTER)
                        sendKeyboardKeyReport(KeyboardLayout.KEYBOARD_KEY_NONE)
                        if(mustClearInputField) {
                            onTextChange("")
                        }
                    }
                ),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius)),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent
                )
            )
            IconButton(
                onClick = {
                    sendTextReport(text)
                    if(mustClearInputField) {
                        onTextChange("")
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.Send,
                    contentDescription = stringResource(id = R.string.send)
                )
            }
        }

        AdditionalKeyboardKeys(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_standard)),
            sendKeyboardKeyReport = sendKeyboardKeyReport
        )
    }
}

@Composable
fun AdditionalKeyboardKeys(
    sendKeyboardKeyReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        KeyboardKey(
            image = Icons.AutoMirrored.Rounded.Backspace,
            contentDescription = stringResource(id = R.string.delete),
            key = KeyboardLayout.KEYBOARD_KEY_DELETE,
            sendKeyboardKey = sendKeyboardKeyReport
        )

        KeyboardKey(
            image = Icons.AutoMirrored.Rounded.KeyboardReturn,
            contentDescription = stringResource(id = R.string.enter),
            key = KeyboardLayout.KEYBOARD_KEY_ENTER,
            sendKeyboardKey = sendKeyboardKeyReport
        )

        KeyboardKey(
            image = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
            contentDescription = stringResource(id = R.string.left),
            key = KeyboardLayout.KEYBOARD_KEY_LEFT,
            sendKeyboardKey = sendKeyboardKeyReport
        )

        KeyboardKey(
            image = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = stringResource(id = R.string.right),
            key = KeyboardLayout.KEYBOARD_KEY_RIGHT,
            sendKeyboardKey = sendKeyboardKeyReport
        )
    }
}

@Composable
private fun KeyboardKey(
    image: ImageVector,
    contentDescription: String,
    key: ByteArray,
    sendKeyboardKey: (ByteArray) -> Unit,
) {
    StatefulRemoteButton(
        touchDown = { sendKeyboardKey(key) },
        touchUp = { sendKeyboardKey(KeyboardLayout.KEYBOARD_KEY_NONE) }
    ) {
        IconButton(
            onClick = {},
            interactionSource = it
        ) {
            Icon(
                imageVector = image,
                contentDescription = contentDescription
            )
        }
    }
}