package com.example.ui_components.components.complex_components.slots

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.constants.enums.Gender
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.OptionButton
import com.example.constants.icons.AppIcons

@Composable
fun SelectGenderSlot(
    gender: Gender,
    onGenderChange:(Gender)-> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.gender),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(MaterialTheme.spacing.medium16))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16)
        ) {
            OptionButton(
                text = R.string.male,
                isSelected = gender == Gender.MALE,
                onClick = { onGenderChange(Gender.MALE) },
                icon = AppIcons.Outlined.man,
                modifier = Modifier.weight(1f)
            )
            OptionButton(
                text = R.string.female,
                isSelected = gender == Gender.FEMALE,
                onClick = { onGenderChange(Gender.FEMALE) },
                icon = AppIcons.Outlined.female,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun SelectGenderSlotPreview() {
    Hospital_AutomationTheme {
        Surface {
            var gender by remember{ mutableStateOf(Gender.MALE) }
            SelectGenderSlot(
                gender = gender,
                onGenderChange = {
                    gender = it
                },
                modifier = Modifier.padding(MaterialTheme.spacing.medium16)
            )
        }
    }
}