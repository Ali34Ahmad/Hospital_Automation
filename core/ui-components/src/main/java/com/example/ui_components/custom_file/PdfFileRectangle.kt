package com.example.ui_components.custom_file

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
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
import com.example.ui.theme.adjustColorByTheme
import com.example.ui.theme.spacing

@Composable
fun PdfFileRectangle(
    color: Color=getFileIconColor(),
    outerFoldedColor: Color=MaterialTheme.colorScheme.background,
    innerFoldedColor: Color=getFileFoldColor(),
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val foldSize = width * 0.2f // Adjust this for the fold size

        val mainPath = Path().apply {
            moveTo(0f, 0f)
            lineTo(width, 0f)
            lineTo(width, height)
            lineTo(0f, height)
            close()
        }
        drawPath(path = mainPath, color = color)

        val outerFoldedPath = Path().apply {
            moveTo(width, 0f)
            lineTo(width - foldSize, 0f)
            lineTo(width, foldSize)
            lineTo(width, 0f) // Close the triangle
            close()
        }

        drawPath(path = outerFoldedPath, color = outerFoldedColor)

        val innerFoldedPath = Path().apply {
            moveTo(width-foldSize, 0f)
            lineTo(width - foldSize, foldSize)
            lineTo(width, foldSize)
            close()
        }

        drawPath(path = innerFoldedPath, color = innerFoldedColor)


    }
}

@Composable
private fun getFileIconColor(): Color {
    return if (!isSystemInDarkTheme()) {
        MaterialTheme.additionalColorScheme.fileLight
    } else {
        MaterialTheme.additionalColorScheme.fileDark
    }
}

@Composable
private fun getFileFoldColor(): Color {
    return if (!isSystemInDarkTheme()) {
        MaterialTheme.additionalColorScheme.fileFoldLight
    } else {
        MaterialTheme.additionalColorScheme.fileFoldDark
    }
}



@DarkAndLightModePreview
@Composable
fun FoldedCornerSquarePreview() {
    Hospital_AutomationTheme{
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