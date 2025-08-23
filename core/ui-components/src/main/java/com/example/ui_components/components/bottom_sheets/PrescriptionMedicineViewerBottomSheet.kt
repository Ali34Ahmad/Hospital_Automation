package com.example.ui_components.components.bottom_sheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.model.medicine.DetailedPrescriptionMedicine
import com.example.model.medicine.MedicineData
import com.example.model.medicine.PrescriptionMedicineMainInfo
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.PrescribedMedicineViewerCard

@Composable
fun ColumnScope.PrescriptionMedicineViewerBottomSheet(
    openNoteDialog: (index: Int) -> Unit,
    navigateToFulfillingPharmacy: (index: Int) -> Unit,
    navigateToMedicineDetailsScreen: (index: Int) -> Unit,
    prescriptionMedicines: List<DetailedPrescriptionMedicine>,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.prescribed_medicines),
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium16)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(Modifier.height(MaterialTheme.spacing.medium16))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
            contentPadding = PaddingValues(bottom = MaterialTheme.spacing.large24)
        ) {
            items(prescriptionMedicines.size, key = { prescriptionMedicines[it].id }) { index ->
                PrescribedMedicineViewerCard(
                    medicineName = prescriptionMedicines[index].medicine.name,
                    titer = prescriptionMedicines[index].medicine.titer,
                    imageUrl = prescriptionMedicines[index].medicine.imageUrl,
                    hasNote = prescriptionMedicines[index].note != null,
                    onShowNote = {
                        openNoteDialog(index)
                    },
                    onNavigateToFulfillingPharmacy = {
                        navigateToFulfillingPharmacy(prescriptionMedicines[index].fulfilledBy ?: -1)
                    },
                    modifier = Modifier,
                    isFulfilled = prescriptionMedicines[index].fulfilledBy != null,
                    onClick = {
                        navigateToMedicineDetailsScreen(prescriptionMedicines[index].medicine.id)
                    },
                )
            }
        }
        Spacer(Modifier.height(MaterialTheme.spacing.large24))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BottomSheetContentPreview() {
    Hospital_AutomationTheme {
        BottomSheetScaffold(
            sheetPeekHeight = MaterialTheme.spacing.sheetPeekHeight,
            sheetContent = {
                PrescriptionMedicineViewerBottomSheet(
                    prescriptionMedicines = medicines,
                    openNoteDialog = { },
                    navigateToFulfillingPharmacy = { },
                    modifier = Modifier,
                    title = stringResource(R.string.prescribed_medicines),
                    navigateToMedicineDetailsScreen = {},
                )

            },
            content = {}
        )
    }
}


val medicines = listOf(
    DetailedPrescriptionMedicine(
        id = 1,
        note = "Take it when you feel headache but only two pill allowed a day",
        medicine = PrescriptionMedicineMainInfo(
            id = 1,
            name = "Citamol",
            imageUrl = "",
            titer = 500
        ),
        fulfilledBy = 2
    ),
    DetailedPrescriptionMedicine(
        id = 1,
        medicine = PrescriptionMedicineMainInfo(
            id = 1,
            name = "Citamol",
            imageUrl = "",
            titer = 500
        ),
        note = "Take it when you feel headache but only two pill allowed a day",
        fulfilledBy = 2
    ),
    DetailedPrescriptionMedicine(
        id = 1,
        medicine = PrescriptionMedicineMainInfo(
            id = 1,
            name = "Citamol",
            imageUrl = "",
            titer = 500
        ),
        note = "Take it when you feel headache but only two pill allowed a day",
        fulfilledBy = 2
    ),

    )
