package com.example.add_child_screen.presentation

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.theme.spacing
import com.example.ui_components.components.bottomBars.custom.SendingDataBottomBar
import com.example.ui_components.components.complex_components.slots.ChildInfoSlot
import com.example.ui_components.components.complex_components.slots.ParentInfoSlots
import com.example.ui_components.components.complex_components.slots.SelectGenderSlot
import com.example.ui_components.components.topbars.custom.AddChildTopBar

import com.example.ui_components.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddChildScreen(
    viewModel: AddChildViewModel,
    navigationActions: AddChildNavigationAction,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    AddChildScreen(
        uiState = uiState,
        modifier = modifier,
        onAction = viewModel::onAction,
        navigationActions = navigationActions
    )
}

@Composable
fun AddChildScreen(
    uiState: AddChildUIState,
    navigationActions: AddChildNavigationAction,
    onAction: (AddChildUIActions)-> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val message = uiState.toastMessage?.asString()
    LaunchedEffect(message) {
        if(message!=null){
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        bottomBar = {
            SendingDataBottomBar(
                text = stringResource(R.string.send_data),
                onButtonClick = {
                    onAction(
                        AddChildUIActions.SendData
                    )
                },
                state = uiState.sendingDataButtonState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium16),
                onSuccess = {
                    uiState.childId?.let {
                        Log.d("Add Child Screen","navigateToNextScreen $it")
                        navigationActions.navigateToNextScreen(it)
                    }
                }
            )
        },
        topBar = {
            AddChildTopBar(
                modifier = Modifier.fillMaxWidth(),
                onNavigateBack = navigationActions::navigateUp
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(MaterialTheme.spacing.medium16)
                .verticalScroll(
                    state = rememberScrollState()
                )
        ) {
            ChildInfoSlot(
                firstName = uiState.firstName,
                onFirstNameChanged = {
                    onAction(
                        AddChildUIActions.OnFirstNameChanged(it)
                    )
                },
                lastName = uiState.lastName,
                onLastNameChanged = {
                    onAction(
                        AddChildUIActions.OnLastNameChanged(it)
                    )
                },
                dateOfBirth = uiState.dateOfBirth,
                onDateOfBirthChanged = {
                    onAction(
                        AddChildUIActions.OnDateChanged(it)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                firstNameError = uiState.firstNameErrorMessage?.asString(),
                lastNameError = uiState.lastNameErrorMessage?.asString(),
                dateOfBirthError = uiState.dateOfBirthErrorMessage?.asString(),
                isDatePickerShown = uiState.isDatePickerVisible,
                onDatePickerVisibilityChanged = {
                    onAction(
                        AddChildUIActions.ChangeDatePickerVisibility(it)
                    )
                }
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
            SelectGenderSlot(
                gender = uiState.gender,
                onGenderChange = {
                    onAction(
                        AddChildUIActions.OnGenderChanged(it)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
            ParentInfoSlots(
                modifier = Modifier.fillMaxWidth(),
                fatherFirstName = uiState.fatherFirstName,
                onFatherFirstNameChange = {
                    onAction(
                        AddChildUIActions.OnFatherFirstNameChanged(it)
                    )
                },
                fatherLastName = uiState.fatherLastName,
                onFatherLastNameChanged = {
                    onAction(
                        AddChildUIActions.OnFatherLastNameChanged(it)
                    )
                },
                motherFirstName = uiState.motherFirstName,
                onMotherFirstNameChange = {
                    onAction(
                        AddChildUIActions.OnMotherFirstNameChanged(it)
                    )
                },
                motherLastName = uiState.motherLastName,
                onMotherLastNameChanged = {
                    onAction(
                        AddChildUIActions.OnMotherLastNameChanged(it)
                    )
                },
                fatherFirstNameErrorMessage = uiState.fatherFirstNameErrorMessage?.asString(),
                fatherLastNameErrorMessage = uiState.fatherLastNameErrorMessage?.asString(),
                motherFirstNameErrorMessage = uiState.motherFirstNameErrorMessage?.asString(),
                motherLastNameErrorMessage = uiState.motherLastNameErrorMessage?.asString(),
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
        }
    }
}