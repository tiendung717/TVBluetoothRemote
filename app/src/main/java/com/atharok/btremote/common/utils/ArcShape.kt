package com.atharok.btremote.common.utils

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class ArcShape(
    private val startAngle: Float,
    private val sweepAngle: Float
): Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val centerX = size.width / 2
        val centerY = size.height / 2
        val radius = size.width / 2

        val path = Path().apply {
            arcTo(
                rect = Rect(
                    centerX - radius,
                    centerY - radius,
                    centerX + radius,
                    centerY + radius
                ),
                startAngleDegrees = startAngle,
                sweepAngleDegrees = sweepAngle,
                forceMoveTo = false
            )
            lineTo(centerX, centerY)
            close()
        }
        return Outline.Generic(path)
    }
}