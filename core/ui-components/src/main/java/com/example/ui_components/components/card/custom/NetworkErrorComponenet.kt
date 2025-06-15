package com.example.ui_components.components.card.custom

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.ui.theme.sizing
import com.example.ui_components.R
import com.example.ui_components.components.card.IllustrationCard

@Composable
fun ErrorComponent(
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.network_error),
    description : String = stringResource(R.string.something_went_wrong),
    @DrawableRes image: Int = R.drawable.ill_error
) {
    IllustrationCard(
        modifier = modifier
            ,
        illustration = {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.size(MaterialTheme.sizing.illustrationImageSize)
            )
        },
        title = title,
        description = description,
    )
}