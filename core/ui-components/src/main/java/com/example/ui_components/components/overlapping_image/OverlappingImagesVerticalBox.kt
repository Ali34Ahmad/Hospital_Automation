package com.example.ui_components.components.overlapping_image

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.components.network_image.NetworkImage

@Composable
fun OverlappingImagesVerticalBox(
    imagesUrls: List<String>,
    modifier: Modifier = Modifier,
) {

    val imageModifier = Modifier
        .size(MaterialTheme.sizing.small24)
        .clip(CircleShape)
        .border(
            width = MaterialTheme.sizing.extraSmall1,
            color = MaterialTheme.colorScheme.background,
            shape = CircleShape
        )

    val secondImageModifier=
        Modifier
            .padding(top = MaterialTheme.spacing.small12)
            .then(imageModifier)

    val thirdImageModifier = Modifier
        .padding(top = MaterialTheme.spacing.large24)
        .then(imageModifier)

    Box(
        modifier,
    ) {
        Box {
            if(imagesUrls.isNotEmpty()) {
                NetworkImage(
                    imageUrl = imagesUrls[0],
                    modifier = imageModifier,
                    contentScale = ContentScale.Crop,
                )
            }
            if(imagesUrls.size>=2) {
                NetworkImage(
                    imageUrl = imagesUrls[1],
                    modifier = secondImageModifier,
                    contentScale = ContentScale.Crop,
                )
            }
        }
        if(imagesUrls.size>=3){
            NetworkImage(
                imageUrl = imagesUrls[2],
                modifier = thirdImageModifier,
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun OverlappingImagesVerticalBox1Preview() {
    Hospital_AutomationTheme {
        Surface {
            OverlappingImagesVerticalBox(
                imagesUrls = listOf(""),
                modifier = Modifier.padding(MaterialTheme.spacing.medium16)
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun OverlappingImagesVerticalBox2Preview() {
    Hospital_AutomationTheme {
        Surface {
            OverlappingImagesVerticalBox(
                imagesUrls = listOf("", ""),
                modifier = Modifier.padding(MaterialTheme.spacing.medium16)
            )
        }
    }
}


@DarkAndLightModePreview
@Composable
fun OverlappingImagesVerticalBox3Preview() {
    Hospital_AutomationTheme {
        Surface {
            OverlappingImagesVerticalBox(
                imagesUrls = listOf("", "", ""),
                modifier = Modifier.padding(MaterialTheme.spacing.medium16)
            )
        }
    }
}

