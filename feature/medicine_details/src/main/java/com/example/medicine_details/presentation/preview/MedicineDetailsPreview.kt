package com.example.medicine_details.presentation.preview

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicine_details.presentation.MedicineDetailsNavigationActions
import com.example.medicine_details.presentation.MedicineDetailsScreen
import com.example.medicine_details.presentation.MedicineDetailsUIActions
import com.example.medicine_details.presentation.MedicineDetailsUIState
import com.example.model.enums.ScreenState
import com.example.model.medicine.MedicineDetailsData
import com.example.model.medicine.MedicineSummaryData
import com.example.ui.theme.Hospital_AutomationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MedicineDetailsPreview(){
    Hospital_AutomationTheme {
        var uiState by remember {
            mutableStateOf(
                MedicineDetailsUIState(
                    medicineId = 1,
                    screenState = ScreenState.SUCCESS,
                    data =mockMedicine,
                    isDialogShown = true
                )
            )
        }
        MedicineDetailsScreen(
            uiState = uiState,
            onAction = {
                when(it){
                    MedicineDetailsUIActions.HideDialog -> {uiState = uiState.copy(isDialogShown = false)}
                    MedicineDetailsUIActions.ShowDialog -> {uiState = uiState.copy(isDialogShown = true)}
                }
            },
            navigationActions = object : MedicineDetailsNavigationActions{
                override fun navigateBack() {
                }
            }
        )
    }
}
