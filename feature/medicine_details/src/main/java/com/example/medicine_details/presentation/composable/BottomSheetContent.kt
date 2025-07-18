package com.example.medicine_details.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.model.medicine.MedicineSummaryData
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.PrescribedMedicineCard


@Composable
fun ColumnScope.BottomSheetContent(
    selectedMedicines: List<MedicineSummaryData>,
    onMedicineSelected: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.medicines_alternatives),
    subtitle: String = stringResource(R.string.alternatives_subtitle)
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium16)
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(MaterialTheme.spacing.extraSmall4))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.outline
        )
            Spacer(Modifier.height(MaterialTheme.spacing.medium16))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8)
            ){
                items(selectedMedicines, key = {it.id}) { medicine->
                    PrescribedMedicineCard(
                        onClick = {
                            onMedicineSelected(medicine.id)
                        },
                        medicineName = medicine.name,
                        drug = medicine.pharmaceuticalTiter,
                        imageUrl = medicine.imageUrl,
                        hasNote = false,
                        canEdit = false,
                    )
                }
            }
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BottomSheetContentPreview() {
    Hospital_AutomationTheme {
        ModalBottomSheet(
            onDismissRequest = {},
        ) {
            BottomSheetContent(
                selectedMedicines = listOf(
                    MedicineSummaryData(
                        id = 1,
                        name = "Vitamine C",
                        pharmaceuticalTiter = 1000,
                        imageUrl = null
                    ),
                    MedicineSummaryData(
                        id = 2,
                        name = "Carntine",
                        pharmaceuticalTiter = 500,
                        imageUrl = null
                    ),
                    MedicineSummaryData(
                        id = 3,
                        name = "Aspirin",
                        pharmaceuticalTiter = 500,
                        imageUrl = null
                    ),
                ),
                onMedicineSelected = {id->},
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}