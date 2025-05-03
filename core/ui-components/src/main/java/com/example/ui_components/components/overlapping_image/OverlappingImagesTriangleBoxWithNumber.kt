package com.example.ui_components.components.overlapping_image

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing

@Composable
fun OverlappingImagesTriangleBoxWithNumber(
    imagesUrls: List<String>,
    number:Int=0,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OverlappingImagesTriangleBox(
            imagesUrls=imagesUrls,
        )
        if(number>0) {
            Text(
                text = "+${number}",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun OverlappingImagesTriangleBoxWithText1Preview() {
    Hospital_AutomationTheme {
        Surface {
            OverlappingImagesTriangleBoxWithNumber(
                imagesUrls = listOf(""),
                number = 1,
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun OverlappingImagesTriangleBoxWithText2Preview() {
    Hospital_AutomationTheme {
        Surface {
            OverlappingImagesTriangleBoxWithNumber(
                imagesUrls = listOf("", ""),
                number = 1,
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
            )
        }
    }
}


@DarkAndLightModePreview
@Composable
fun OverlappingImagesTriangleBoxWithText3Preview() {
    Hospital_AutomationTheme {
        Surface {
            OverlappingImagesTriangleBoxWithNumber(
                imagesUrls = listOf("", "", "","",),
                number = 1,
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
            )
        }
    }
}

