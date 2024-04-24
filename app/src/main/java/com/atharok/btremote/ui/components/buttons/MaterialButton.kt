package com.atharok.btremote.ui.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.atharok.btremote.R
import com.atharok.btremote.ui.components.TextStandardPrimary

@Composable
fun MaterialButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "",
    icon: ImageVector? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_standard))
        ) {
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,// Le texte sert de description
                    Modifier.padding(0.dp)
                )
            }

            TextStandardPrimary(text = text)
        }
    }
}