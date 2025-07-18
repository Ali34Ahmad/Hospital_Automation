package com.example.medicines_search.presentation.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.constants.icons.AppIcons
import com.example.model.medicine.MedicineData
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.PrescribedMedicineCard

@Composable
fun ColumnScope.BottomSheetContent(
    onClear: () -> Unit,
    openNoteDialog: (Int)-> Unit,
    selectedMedicines: List<MedicineData>,
    onMedicineDeleted: (MedicineData) -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.prescribed_medicines),
    @DrawableRes cleanIcon: Int = AppIcons.Outlined.clean
) {
    Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium16)
        ) {
        if (selectedMedicines.isEmpty()) {
            Icon(
                painterResource(AppIcons.Outlined.pill_off),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline
            )
            Spacer(Modifier.height(MaterialTheme.spacing.small8))
            Text(
                stringResource(R.string.no_medicines_selected),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(MaterialTheme.spacing.extraSmall4))
            Text(
                stringResource(R.string.select_medicine),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline,
                textAlign = TextAlign.Center
            )
        }
        else {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(Modifier.weight(1f))
                IconButton(
                    modifier= Modifier
                        .size(
                            MaterialTheme.sizing.small24
                        )
                        ,
                    onClick = onClear,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer
                    )
                ) {
                    Icon(
                        modifier = Modifier.padding(MaterialTheme.spacing.extraSmall4),
                        painter = painterResource(cleanIcon),
                        contentDescription = null,
                    )
                }
            }
            Spacer(Modifier.height(MaterialTheme.spacing.medium16))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8)
            ){
                items(selectedMedicines, key = {it.medicineId}) { medicine->
                    PrescribedMedicineCard(
                        medicineName = medicine.name,
                        drug = medicine.pharmaceuticalTiter,
                        imageUrl = medicine.medImageUrl,
                        hasNote = false,
                        onAddNote = {
                            openNoteDialog(medicine.medicineId)
                        },
                        onEditNote ={
                            openNoteDialog(medicine.medicineId)
                        },
                        onTrailingIconClick = {
                            onMedicineDeleted(medicine)
                        },
                    )
                }
            }
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
        }
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
                BottomSheetContent(
                    onClear = {
                    },
                    selectedMedicines = medicines,
                    onMedicineDeleted = {},
                    openNoteDialog={}
                )

            },
            content = {}
        )
    }
}

val medicines = listOf(
    MedicineData(
        medicineId = 1,
        name = "Paracetamol",
        pharmaceuticalTiter = 1000,
        pharmaceuticalIndications = "for head, back pains",
        pharmaceuticalComposition = "",
        companyName = "Fake Company",
        price = 10000,
        isAllowedWithoutPrescription = true,
        barcode = "10342",
        medImageUrl = "",
        numberOfPharmacies = 2
    ),MedicineData(
        medicineId = 2,
        name = "Antispa",
        pharmaceuticalTiter = 500,
        pharmaceuticalIndications = "for kolonge and kidney",
        pharmaceuticalComposition = "",
        companyName = "Fake Company",
        price = 5200,
        isAllowedWithoutPrescription = true,
        barcode = "10242",
        medImageUrl = "",
        numberOfPharmacies = 3
    ),
)