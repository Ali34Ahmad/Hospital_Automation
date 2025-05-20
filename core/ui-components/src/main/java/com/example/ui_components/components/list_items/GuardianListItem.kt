package com.example.ui_components.components.list_items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import com.example.ui_components.R

@Composable
fun GuardianListItem(
    imageUrl: String?,
    name: String,
    onClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8))
            .clickable{
                onClick
            }
            .background(MaterialTheme.colorScheme.background)
        ,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if(imageUrl.isNullOrBlank()){
            Image(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(CircleShape)
                    .size(40.dp)
                ,
                painter = painterResource(R.drawable.doctor1),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }else{
            AsyncImage(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(CircleShape)
                    .size(40.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

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