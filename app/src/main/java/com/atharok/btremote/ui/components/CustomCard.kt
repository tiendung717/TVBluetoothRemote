package com.atharok.btremote.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.atharok.btremote.R

@Composable
fun CustomCard(
    shape: Shape,
    modifier: Modifier = Modifier,
    surfaceElevation: Dp = dimensionResource(id = R.dimen.elevation),
    shadowElevation: Dp = dimensionResource(id = R.dimen.shadow_elevation),
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.shadow(
            elevation = shadowElevation,
            shape = shape
        ),
        shape = shape,
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            tonalElevation = surfaceElevation
        ) {
            content()
        }
    }
}