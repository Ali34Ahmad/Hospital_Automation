package com.example.ui_components.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.model.Doctor
import com.example.ui.fake.createDoctorList
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.components.card.DepartmentDoctorCard

@Composable
fun DepartmentDoctorsLazyRow(
    doctors: List<Doctor>,
    onItemClick:(Int)->Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.medium16),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
    ) {
        itemsIndexed(doctors) { index,doctor ->
            DepartmentDoctorCard(
                doctor = doctor,
                onClick = {
                    onItemClick(index)
                },
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun DepartmentDoctorsLazyRowPreview() {
    Hospital_AutomationTheme {
        Surface {
            DepartmentDoctorsLazyRow(
                doctors = createDoctorList(),
                onItemClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.medium16)
            )
        }
    }
}