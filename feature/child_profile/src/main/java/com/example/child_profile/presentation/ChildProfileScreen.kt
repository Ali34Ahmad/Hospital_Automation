package com.example.child_profile.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.ext.toDate
import com.example.ext.toGender
import com.example.model.child.ChildFullData
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.components.buttons.custom.AddGuardianButton
import com.example.ui_components.components.card.ChildProfileCard
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.items.custom.SomeThingWentWrong
import com.example.ui_components.components.topbars.custom.ChildProfileTopBar

@Composable
fun ChildProfileScreen(
    viewModel: ChildProfileViewModel,
    onAction: (ChildProfileUIAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsState()
    val child = uiState.value.child
    ChildProfileScreen(
        modifier = modifier,
        uiState = uiState.value,
        onNavigateUpClick = {
            onAction(ChildProfileUIAction.NavigateUp)
        },
        onGuardiansTagItemClick = {
            child?.childId?.let {
                onAction(ChildProfileUIAction.NavigateToGuardiansScreen(it))
            }
        },
        onBirthCertificateItemClick = {
            child?.childId?.let {
                onAction(ChildProfileUIAction.NavigateToBirthCertificateScreen(it))
            }
        },
        onBirthCertificateItemDescriptionClick = {
            child?.employeeId?.let {
                onAction(ChildProfileUIAction.NavigateToEmployeeProfileScreen(it))
            }
        },
        onNavigateToAddGuardiansScreen = {
            child?.employeeId?.let{
                onAction(ChildProfileUIAction.NavigateToGuardiansScreen(it))
            }
        }
    )
}

@Composable
fun ChildProfileScreen(
    uiState: ChildProfileUIState,
    onBirthCertificateItemClick: () -> Unit,
    onBirthCertificateItemDescriptionClick: () -> Unit,
    onGuardiansTagItemClick:() -> Unit,
    onNavigateUpClick: () -> Unit,
    onNavigateToAddGuardiansScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val child = uiState.child
    var name = ""
    child?.let {
        name = "${it.firstName} ${it.lastName}"
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            ChildProfileTopBar(
                modifier = Modifier.fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16),
                name = name,
                onNavigateUpClick = onNavigateUpClick,
            )
        },
        bottomBar = {
            AddGuardianButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigateToAddGuardiansScreen,
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium16)
                .padding(innerPadding)
        ) {
            Crossfade (uiState.isLoading && child == null) {
                when(it){
                    true->{
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            FetchingDataItem()
                        }
                    }
                    else -> {
                        child?.let { child ->
                            ChildProfileCard(
                                fatherName = child.fatherLastName,
                                motherName = child.motherLastName,
                                gender = child.gender.toGender(),
                                dateOfBirth = child.dateOfBirth.toDate(),
                                employeeName = "no name",
                                guardiansNumber = child.numberOfGuardians?:1,
                                onBirthCertificateItemClick = onBirthCertificateItemClick,
                                onBirthCertificateItemDescriptionClick = onBirthCertificateItemDescriptionClick,
                                onGuardianTagItemClick = onGuardiansTagItemClick,
                                modifier = Modifier
                            )
                        }
                    }
                }
            }

            if(uiState.hasError){
                SomeThingWentWrong(
                    modifier.fillMaxWidth()
                )
            }
        }
    }

}

@DarkAndLightModePreview
@Composable
fun ChildProfileScreenPreview() {
    Hospital_AutomationTheme {
        var uiState by remember { mutableStateOf(ChildProfileUIState(isLoading = false)) }
        ChildProfileScreen(
            uiState = uiState,
            onBirthCertificateItemClick = {},
            onBirthCertificateItemDescriptionClick = {},
            onGuardiansTagItemClick = {},
            onNavigateUpClick = {},
            onNavigateToAddGuardiansScreen = {}
        )
    }
}

 val fakeChild =  ChildFullData(
    numberOfGuardians = 3,
    childId = 1,
    firstName = "Ali",
    lastName = "Mansoura",
    fatherFirstName = "Bassam",
    fatherLastName = "Mansoura",
    motherFirstName = "Aida",
    motherLastName = "Ghanem",
    dateOfBirth = "04-09-2002",
    birthCertificateImgUrl = "",
    gender = "male",
    employeeId = 1
)