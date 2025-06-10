package com.example.ui_components.components.list_items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui_components.R
import com.example.ui_components.components.network_image.NetworkImage
import com.example.ui_components.components.network_image.NetworkImageError
import com.example.ui_components.components.network_image.NetworkImageLoader

@Composable
fun GuardianListItem(
    imageUrl: String?,
    name: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(MaterialTheme.sizing.small8))
            .clickable{
                onClick()
            }
            .background(MaterialTheme.colorScheme.background)
        ,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
            NetworkImage(
                model = imageUrl,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(MaterialTheme.sizing.small16)
                    .size(MaterialTheme.sizing.medium40)
                    .clip(CircleShape),
                loading = {
                    NetworkImageLoader(
                        modifier=Modifier
                            .clip(CircleShape)
                            .padding(MaterialTheme.sizing.small16)
                            .size(MaterialTheme.sizing.medium40)
                    )
                },
                errorCompose = {
                    NetworkImageError()
                },
            )

        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GuardianListItemPreview() {
    Hospital_AutomationTheme {
        GuardianListItem(
            modifier = Modifier.width(380.dp),
            imageUrl = "",
            name = "Mariam Saoud",
            onClick = {}
        )
    }
}