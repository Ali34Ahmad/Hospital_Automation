package com.example.ui_components.components.list

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.example.constants.icons.AppIcons
import com.example.model.doctor.appointment.AppointmentTypeData
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.AppointmentTypeCard
import com.example.ui_components.components.items.NoItemsText

@Composable
fun AppointmentTypesList(
    appointmentTypes: List<AppointmentTypeData>,
    onEdit: (Int)-> Unit,
    onDelete: (Int)-> Unit,
    modifier: Modifier = Modifier,
    onAddButtonClick: ()-> Unit,
    @StringRes title: Int = R.string.appointment_types,
    @DrawableRes addIcon: Int = AppIcons.Outlined.add,
    titleStyle: TextStyle = MaterialTheme.typography.titleSmall,
    titleColor: Color = MaterialTheme.colorScheme.onBackground,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
        horizontalAlignment = Alignment.Start
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(title),
                style = titleStyle,
                color = titleColor
            )
            IconButton(
                modifier = Modifier
                    .size(
                        MaterialTheme.sizing.small24
                    )
                    .clip(CircleShape),
                onClick = onAddButtonClick,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
            ) {
                Icon(
                    painter = painterResource(addIcon),
                    contentDescription = null
                )
            }
        }
        if (appointmentTypes.isEmpty()){
            NoItemsText(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }else{
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall4),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(appointmentTypes) { index,appointmentType->
                    AppointmentTypeCard(
                        index = index+1,
                        title = appointmentType.name,
                        subtitle = "${appointmentType.standardDurationInMinutes} min",
                        onDelete = {
                            onDelete(appointmentType.id)
                        },
                        description = appointmentType.description,
                        onEdit = {
                            onEdit(appointmentType.id)
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
        
    }
}

@DarkAndLightModePreview
@Composable
fun AppointmentTypesListPreview() {
    Hospital_AutomationTheme {
        Surface {
            AppointmentTypesList(
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                appointmentTypes = listOf(
                    AppointmentTypeData(
                        id = 1,
                        name = "Check Up",
                        standardDurationInMinutes =15,
                        description ="A Review uses a special type of energy to create pictures of the inside of your body. It's like taking a photograph, but instead of light, it uses invisible rays to see what's happening inside. A Review uses a special type of energy to create pictures of the inside of your body. It's like taking a photograph, but instead of light, it uses invisible rays to see what's happening inside.\n",
                        doctorId = 2
                    ),AppointmentTypeData(
                        id = 2,
                        name = "Check Up",
                        standardDurationInMinutes =30,
                        description ="A Review uses a special type of energy to create pictures of the inside of your body. It's like taking a photograph, but instead of light, it uses invisible rays to see what's happening inside. A Review uses a special type of energy to create pictures of the inside of your body. It's like taking a photograph, but instead of light, it uses invisible rays to see what's happening inside.\n",
                        doctorId = 2
                    ),AppointmentTypeData(
                        id = 3,
                        name = "Check Up",
                        standardDurationInMinutes =20,
                        description ="A Review uses a special type of energy to create pictures of the inside of your body. It's like taking a photograph, but instead of light, it uses invisible rays to see what's happening inside. A Review uses a special type of energy to create pictures of the inside of your body. It's like taking a photograph, but instead of light, it uses invisible rays to see what's happening inside.\n",
                        doctorId = 2
                    ),
                ),
                onEdit = {},
                onDelete = {},
                onAddButtonClick = {},
            )
        }
    }
}