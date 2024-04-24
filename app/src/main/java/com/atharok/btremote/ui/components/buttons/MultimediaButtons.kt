package com.atharok.btremote.ui.components.buttons

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.SkipNext
import androidx.compose.material.icons.rounded.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.domain.entity.RemoteLayout
import com.atharok.btremote.ui.components.CustomCard

@Composable
fun MultimediaButtons(
    sendRemoteKey: (bytes: ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    CustomCard(
        shape = CircleShape,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MultimediaButton(
                bytes = RemoteLayout.REMOTE_KEY_PREVIOUS,
                sendReport = sendRemoteKey,
                image = Icons.Rounded.SkipPrevious,
                contentDescription = stringResource(id = R.string.previous),
                modifier = Modifier.weight(1f)
            )
            PlayPauseButton(
                bytes = RemoteLayout.REMOTE_KEY_PLAY_PAUSE,
                sendReport = sendRemoteKey,
                modifier = Modifier.weight(1f)
            )
            MultimediaButton(
                bytes = RemoteLayout.REMOTE_KEY_NEXT,
                sendReport = sendRemoteKey,
                image = Icons.Rounded.SkipNext,
                contentDescription = stringResource(id = R.string.next),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun MultimediaButton(
    bytes: ByteArray,
    sendReport: (ByteArray) -> Unit,
    image: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        StatefulRemoteButton(
            touchDown = { sendReport(bytes) },
            touchUp = { sendReport(RemoteLayout.REMOTE_KEY_NONE) }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
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
                    modifier = Modifier.fillMaxSize(0.75f)
                )
            }
        }
    }
}

@Composable
private fun PlayPauseButton(
    bytes: ByteArray,
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        StatefulRemoteButton(
            touchDown = { sendReport(bytes) },
            touchUp = { sendReport(RemoteLayout.REMOTE_KEY_NONE) }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clipToBounds()
                    .clickable(
                        interactionSource = it,
                        indication = LocalIndication.current,
                        onClick = {}
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(0.75f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.PlayArrow,
                        contentDescription = stringResource(id = R.string.play),
                        modifier = Modifier.fillMaxHeight().weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Rounded.Pause,
                        contentDescription = stringResource(id = R.string.pause),
                        modifier = Modifier.fillMaxHeight().weight(1f)
                    )
                }
            }
        }
    }
}