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
import com.example.ui_components.components.network_image.NetworkImageLoader

@Composable
fun OverlappingImagesTriangleBox(
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

    val secondImageModifier = if (imagesUrls.size == 2) {
        Modifier
            .padding(start = MaterialTheme.spacing.small12)
            .then(imageModifier)
    } else {
        Modifier
            .padding(start = MaterialTheme.spacing.large24)
            .then(imageModifier)
    }
    val thirdImageModifier = Modifier
        .padding(top = MaterialTheme.spacing.medium16)
        .then(imageModifier)

    val secondImageLoaderModifier = if (imagesUrls.size == 2) {
        Modifier.padding(start = MaterialTheme.spacing.small12)

    } else {
        Modifier.padding(start = MaterialTheme.spacing.large24)
    }
    val thirdImageLoaderModifier = Modifier
        .padding(top = MaterialTheme.spacing.medium16)

    Box(
        modifier,
        contentAlignment = Alignment.Center,
    ) {
        Box {
            if (imagesUrls.isNotEmpty()) {
                NetworkImage(
                    model = imagesUrls[0],
                    modifier = imageModifier,
                    contentScale = ContentScale.Crop,
                    loading = {
                        NetworkImageLoader(
                            imageModifier
                        )
                    },
                    errorCompose = {
                        NetworkImageLoader(
                            imageModifier
                        )
                    }
                )
            }
            if (imagesUrls.size >= 2) {
                NetworkImage(
                    model = imagesUrls[1],
                    modifier = secondImageModifier,
                    contentScale = ContentScale.Crop,
                    loading = {
                        NetworkImageLoader(
                            imageModifier
                        )
                    },
                    errorCompose = {
                        NetworkImageLoader(
                            imageModifier
                        )
                    }
                )
            }
        }
        if (imagesUrls.size >= 3) {
            NetworkImage(
                model = imagesUrls[2],
                modifier = thirdImageModifier,
                contentScale = ContentScale.Crop,
                loading = {
                    NetworkImageLoader(
                        imageModifier
                    )
                },
                errorCompose = {
                    NetworkImageLoader(
                        imageModifier
                    )
                }
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun OverlappingImagesBox1Preview() {
    Hospital_AutomationTheme {
        Surface {
            OverlappingImagesTriangleBox(
                imagesUrls = listOf(""),
                modifier = Modifier.padding(MaterialTheme.spacing.medium16)
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun OverlappingImagesBox2Preview() {
    Hospital_AutomationTheme {
        Surface {
            OverlappingImagesTriangleBox(
                imagesUrls = listOf("", ""),
                modifier = Modifier.padding(MaterialTheme.spacing.medium16)
            )
        }
    }
}


@DarkAndLightModePreview
@Composable
fun OverlappingImagesBox3Preview() {
    Hospital_AutomationTheme {
        Surface {
            OverlappingImagesTriangleBox(
                imagesUrls = listOf("", "", ""),
                modifier = Modifier.padding(MaterialTheme.spacing.medium16)
            )
        }
    }
}

