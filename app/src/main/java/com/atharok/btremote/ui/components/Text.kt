package com.atharok.btremote.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.atharok.btremote.ui.theme.Typography

@Composable
fun TextRemoteNumber(
    text: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(fontSize = fontSize),
        fontWeight = FontWeight.SemiBold,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

@Composable
fun TextTitlePrimary(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    maxLines: Int = 1,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        style = Typography.titleLarge,
        fontWeight = FontWeight.SemiBold,
        overflow = TextOverflow.Ellipsis,
        maxLines = maxLines,
        textAlign = textAlign
    )
}

@Composable
fun TextTitleSecondary(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = Typography.titleMedium,
        fontWeight = FontWeight.Black,
        textAlign = textAlign
    )
}

@Composable
fun TextTitleTertiary(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        style = Typography.titleSmall,
        fontWeight = FontWeight.Medium,
        textAlign = textAlign
    )
}

@Composable
fun TextTitleSettings(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary,
        style = Typography.titleSmall,
        fontWeight = FontWeight.Black,
        textAlign = textAlign
    )
}

@Composable
fun TextStandardPrimary(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    style: TextStyle = Typography.bodyMedium,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        style = style,
        fontWeight = FontWeight.Medium,
        textAlign = textAlign
    )
}

@Composable
fun TextStandardSecondary(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null
) {
    TextStandardPrimary(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        style = Typography.bodySmall,
        textAlign = textAlign
    )
}

@Composable
fun TextLink(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary,
        style = Typography.titleSmall,
        fontWeight = FontWeight.Medium,
        textDecoration = TextDecoration.Underline,
        textAlign = textAlign
    )
}