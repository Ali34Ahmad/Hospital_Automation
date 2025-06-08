package com.example.hospital_automation.fake_navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object FakeStartDestination


@Composable
fun FakeStartDestination(
    navigateToAddChild: () -> Unit,
    navigateToAddGuardian: ()-> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface
    ) {innerPadding->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = navigateToAddChild
            ) {
                Text("Add Child")
            }
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = navigateToAddGuardian
            ) {
                Text("Add Guardian")
            }
        }
    }
}

fun NavGraphBuilder.fakeStartDestination(
    navigateToAddChild: () -> Unit,
    navigateToAddGuardian: () -> Unit
){
    composable<FakeStartDestination>{
        FakeStartDestination(
            modifier = Modifier.fillMaxSize(),
            navigateToAddChild = navigateToAddChild,
            navigateToAddGuardian = navigateToAddGuardian
        )
    }
}