package com.example.ui_components.components.custom_file

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.spacing

@Composable
fun PdfFileRectangle(
    color: Color = MaterialTheme.additionalColorScheme.file,
    innerFoldedColor: Color = MaterialTheme.additionalColorScheme.fileFold,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .background(Color.Transparent)
    ) {
        val width = size.width
        val height = size.height
        val foldSize = width * 0.2f // Adjust this for the fold size

        val mainPath = Path().apply {
            moveTo(0f, 0f)
            lineTo(width-foldSize, 0f)
            lineTo(width, foldSize)
            lineTo(width, height)
            lineTo(0f, height)
            close()
        }
        drawPath(path = mainPath, color = color)

        val innerFoldedPath = Path().apply {
            moveTo(width - foldSize, 0f)
            lineTo(width - foldSize, foldSize)
            lineTo(width, foldSize)
            close()
        }

        drawPath(path = innerFoldedPath, color = innerFoldedColor)


    }
}


@DarkAndLightModePreview
@Composable
fun FoldedCornerSquarePreview() {
    Hospital_AutomationTheme {
        Surface {
            PdfFileRectangle(
                modifier = Modifier
                    .height(100.dp)
                    .width(90.dp)
                    .padding(MaterialTheme.spacing.medium16)
            )
        }
    }
}