package com.atharok.btremote.ui.components.buttons

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.VolumeDown
import androidx.compose.material.icons.automirrored.rounded.VolumeMute
import androidx.compose.material.icons.automirrored.rounded.VolumeUp
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.BrightnessHigh
import androidx.compose.material.icons.rounded.BrightnessLow
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ModeStandby
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.domain.entity.RemoteLayout
import com.atharok.btremote.ui.components.CustomCard

@Composable
private fun RemoteButton(
    bytes: ByteArray,
    sendReport: (ByteArray) -> Unit,
    image: ImageVector,
    contentDescription: String
) {
    StatefulRemoteButton(
        touchDown = { sendReport(bytes) },
        touchUp = { sendReport(RemoteLayout.REMOTE_KEY_NONE) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .clipToBounds()
                .clickable(
                    interactionSource = it,
                    indication = LocalIndication.current,
                    onClick = {}
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = image,
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxSize(0.50f)
            )
        }
    }
}

// ---- Single Button ---->

@Composable
private fun SingleRemoteButton(
    bytes: ByteArray,
    sendReport: (ByteArray) -> Unit,
    image: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    CustomCard(
        shape = CircleShape,
        modifier = modifier
    ) {
        RemoteButton(
            bytes = bytes,
            sendReport = sendReport,
            image = image,
            contentDescription = contentDescription
        )
    }
}

@Composable
fun BackRemoteButton(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    SingleRemoteButton(
        bytes = RemoteLayout.REMOTE_KEY_BACK,
        sendReport = sendReport,
        image = Icons.AutoMirrored.Rounded.ArrowBack,
        contentDescription = stringResource(id = R.string.back),
        modifier = modifier
    )
}

@Composable
fun HomeRemoteButton(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    SingleRemoteButton(
        bytes = RemoteLayout.REMOTE_KEY_HOME,
        sendReport = sendReport,
        image = Icons.Rounded.Home,
        contentDescription = stringResource(id = R.string.home),
        modifier = modifier
    )
}

@Composable
fun StandbyRemoteButton(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    SingleRemoteButton(
        bytes = RemoteLayout.REMOTE_KEY_STANDBY,
        sendReport = sendReport,
        image = Icons.Rounded.ModeStandby,
        contentDescription = stringResource(id = R.string.standby),
        modifier = modifier
    )
}

@Composable
fun MuteRemoteButton(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    SingleRemoteButton(
        bytes = RemoteLayout.REMOTE_KEY_VOLUME_MUTE,
        sendReport = sendReport,
        image = Icons.AutoMirrored.Rounded.VolumeMute,
        contentDescription = stringResource(id = R.string.mute),
        modifier = modifier
    )
}

/*@Composable
fun SwipeUpMouseButton(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    SingleRemoteButton(
        bytes = mouseWheelUp,
        sendReport = sendReport,
        image = Icons.Rounded.SwipeUp,
        contentDescription = stringResource(id = R.string.mouse_wheel_up),
        modifier = modifier
    )
}

@Composable
fun SwipeDownMouseButton(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    SingleRemoteButton(
        bytes = mouseWheelDown,
        sendReport = sendReport,
        image = Icons.Rounded.SwipeDown,
        contentDescription = stringResource(id = R.string.mouse_wheel_down),
        modifier = modifier
    )
}*/

// ---- Vertical Buttons ----

@Composable
private fun VerticalRemoteButtons(
    contentUp: @Composable () -> Unit,
    contentDown: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomCard(
        shape = CircleShape,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f, false)
            ) {
                contentUp()
            }
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f, false)
            ) {
                contentDown()
            }
        }
    }
}

@Composable
fun VolumeVerticalRemoteButtons(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    VerticalRemoteButtons(
        contentUp = {
            RemoteButton(
                bytes = RemoteLayout.REMOTE_KEY_VOLUME_INC,
                sendReport = sendReport,
                image = Icons.AutoMirrored.Rounded.VolumeUp,
                contentDescription = stringResource(id = R.string.volume_increase)
            )
        },
        contentDown = {
            RemoteButton(
                bytes = RemoteLayout.REMOTE_KEY_VOLUME_DEC,
                sendReport = sendReport,
                image = Icons.AutoMirrored.Rounded.VolumeDown,
                contentDescription = stringResource(id = R.string.volume_decrease)
            )
        },
        modifier = modifier
    )
}

@Composable
fun BrightnessVerticalRemoteButtons(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    VerticalRemoteButtons(
        contentUp = {
            RemoteButton(
                bytes = RemoteLayout.REMOTE_KEY_BRIGHTNESS_INC,
                sendReport = sendReport,
                image = Icons.Rounded.BrightnessHigh,
                contentDescription = stringResource(id = R.string.brightness_increase)
            )
        },
        contentDown = {
            RemoteButton(
                bytes = RemoteLayout.REMOTE_KEY_BRIGHTNESS_DEC,
                sendReport = sendReport,
                image = Icons.Rounded.BrightnessLow,
                contentDescription = stringResource(id = R.string.brightness_decrease)
            )
        },
        modifier = modifier
    )
}

@Composable
fun ChannelVerticalRemoteButtons(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    VerticalRemoteButtons(
        contentUp = {
            RemoteButton(
                bytes = RemoteLayout.REMOTE_KEY_CHANNEL_INC,
                sendReport = sendReport,
                image = Icons.Rounded.Add,
                contentDescription = stringResource(id = R.string.next_channel)
            )
        },
        contentDown = {
            RemoteButton(
                bytes = RemoteLayout.REMOTE_KEY_CHANNEL_DEC,
                sendReport = sendReport,
                image = Icons.Rounded.Remove,
                contentDescription = stringResource(id = R.string.previous_channel)
            )
        },
        modifier = modifier
    )
}