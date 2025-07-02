package com.example.medicines_search.presentation.composable

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.constants.icons.AppIcons
import com.example.medicines_search.presentation.preview.MedicinesSearchUIAction
import com.example.model.enums.BottomBarState
import com.example.model.medicine.MedicineData
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.bottomBars.custom.MedicinesSearchBottomBar
import com.example.ui_components.components.card.EditableAppointmentTypeCard

@Composable
fun ColumnScope.BottomSheetContent(
    onFinish: ()-> Unit,
    onClear: () -> Unit,
    clearButtonState: BottomBarState,
    finishButtonState: BottomBarState,
    selectedMedicines: List<MedicineData>,
    onMedicineDeleted: (MedicineData) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
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
            //TODO : design a lazy row
        }
        MedicinesSearchBottomBar(
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.large24),
            onFirstButtonClick = onClear,
            onSecondButtonClick = onFinish,
            firstButtonState = clearButtonState,
            secondButtonState = finishButtonState,
        )

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
                    onFinish = {},
                    onClear = { },
                    clearButtonState = BottomBarState.IDLE,
                    finishButtonState = BottomBarState.IDLE,
                    selectedMedicines = medicines,
                    onMedicineDeleted = {},
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
        medicineId = 1,
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