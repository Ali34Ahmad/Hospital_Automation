package com.example.ui_components.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.example.model.Doctor
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.components.network_image.NetworkImage

@Composable
fun DepartmentDoctorCard(
    doctor: Doctor,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val cardColor=lerp(
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.background,
        0.6f
    )
    Row(
        modifier = modifier
            .width(MaterialTheme.sizing.extraLarge200)
            .clip(MaterialTheme.shapes.extraSmall)
            .background(
                color = cardColor,
            )
            .clickable { onClick() }
            .padding(horizontal = MaterialTheme.spacing.small8),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier.padding(vertical = MaterialTheme.spacing.small12)
        ) {
            NetworkImage(
                model = doctor.imageUrl,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(MaterialTheme.sizing.medium32)
                    .clip(CircleShape)
            )
            Spacer(
                modifier = Modifier
                    .size(MaterialTheme.sizing.extraSmall8)
                    .background(
                        color = MaterialTheme.additionalColorScheme.green,
                        shape = CircleShape,
                    )
                    .border(
                        width = MaterialTheme.sizing.extraSmall1,
                        color = cardColor,
                        shape = CircleShape,
                    )
            )
        }
        Column(
            modifier = Modifier.padding(start = MaterialTheme.spacing.small12)
        ) {
            Text(
                text = doctor.name,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Text(
                text = doctor.specialty,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.additionalColorScheme.onBackgroundVariant,
                modifier = Modifier.padding(top = MaterialTheme.spacing.extraSmall4),
            )

        }
    }
}

class SquashedOval : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path()
        val centerX = size.width / 2
        val centerY = size.height / 2
        val radius = minOf(centerX, centerY)
        val mouthAngleDegrees: Float = 40f

        // Move to the starting point on the circle
        path.moveTo(centerX, centerY - radius)

        // Draw the outer arc
        path.arcTo(
            rect = Rect(
                left = centerX - radius,
                top = centerY - radius,
                right = centerX + radius,
                bottom = centerY + radius
            ),
            startAngleDegrees = 270f + (mouthAngleDegrees / 2),
            sweepAngleDegrees = 360f - mouthAngleDegrees,
            forceMoveTo = false
        )

        // Draw a line to the center
        path.lineTo(centerX, centerY)

        // Close the path to form the "mouth"
        path.close()

        return Outline.Generic(path = path)
    }
}


fun createPacManPath(size: Size, mouthAngleDegrees: Float = 60f): Path {
    val path = Path()
    val centerX = size.width / 2
    val centerY = size.height / 2
    val radius = minOf(centerX, centerY)

    // Move to the starting point on the circle
    path.moveTo(centerX, centerY - radius)

    // Draw the outer arc
    path.arcTo(
        rect = androidx.compose.ui.geometry.Rect(
            left = centerX - radius,
            top = centerY - radius,
            right = centerX + radius,
            bottom = centerY + radius
        ),
        startAngleDegrees = 270f + (mouthAngleDegrees / 2),
        sweepAngleDegrees = 360f - mouthAngleDegrees,
        forceMoveTo = false
    )

    // Draw a line to the center
    path.lineTo(centerX, centerY)

    // Close the path to form the "mouth"
    path.close()

    return path
}

@DarkAndLightModePreview
@Composable
fun DepartmentDoctorCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            DepartmentDoctorCard(
                doctor = Doctor(
                    imageUrl = "",
                    specialty = "Ophthalmologist",
                    name = "Ali Ahmad"
                ),
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                onClick = {},
            )
        }
    }
}