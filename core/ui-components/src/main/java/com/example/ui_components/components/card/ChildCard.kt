
package com.example.ui_components.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.constants.icons.AppIcons
import com.example.ext.toAppropriateAddressFormat
import com.example.ext.toAppropriateAgeFormat
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui_components.components.items.DetailsItem
import com.example.model.Child
import com.example.model.age.Age
import com.example.model.enums.AgeUnit
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.spacing
import com.example.ui_components.R

@Composable
fun ChildCard(
    child: Child,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(MaterialTheme.spacing.small8),
    @DrawableRes firstIcon: Int = AppIcons.Outlined.father,
    firstText: String = stringResource(R.string.father),
    secondText: String = stringResource(R.string.mother),
    @DrawableRes secondIcon: Int = AppIcons.Outlined.mother,

) {
    Column(
        modifier = modifier
            .clip(shape)
            .background(color = MaterialTheme.colorScheme.background,shape)
            .clickable { onClick() }
            .padding(
                MaterialTheme.spacing.medium16
            )
    ) {
        //top section
        Row() {
            Text(
                text = child.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier=Modifier.width(MaterialTheme.spacing.extraSmall4))
            Text(
                text = child.age.toAppropriateAgeFormat(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.additionalColorScheme.onBackgroundVariant,
                maxLines = 1,
                )
        }
        Spacer(modifier=Modifier.height(MaterialTheme.spacing.large24))
        //bottom section
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DetailsItem(
                title = firstText ,
                description = child.fatherName,
                iconRes = firstIcon,
                modifier = Modifier.weight(1f),
                isMultipleLines = false,
                contentPadding = PaddingValues(MaterialTheme.spacing.default)
            )
            DetailsItem(
                title = secondText,
                description = child.motherName,
                iconRes = secondIcon,
                modifier = Modifier.weight(1f),
                isMultipleLines = false,
                contentPadding = PaddingValues(MaterialTheme.spacing.default)
            )
        }
    }
}

@Preview()
@Composable
fun ChildCardPreview() {
    Hospital_AutomationTheme {
            ChildCard(
                child = Child(
                    id = 1,
                    name = "Ziad Mansoura",
                    age = Age(11, AgeUnit.WEEK),
                    fatherName = "Zaid Ahmad",
                    motherName = "Jasmine Ahmad"
                ),
                onClick = {}
            )

    }
}
