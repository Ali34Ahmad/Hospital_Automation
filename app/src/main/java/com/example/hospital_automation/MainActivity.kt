package com.example.hospital_automation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.children_search.presentation.ChildrenSearchScreen
import com.example.children_search.presentation.ChildrenSearchViewModel
import com.example.guardians.presentation.GuardiansScreen
import com.example.guardians.presentation.GuardiansViewModel
import com.example.guardians_search.presentation.GuardiansSearchScreen
import com.example.guardians_search.presentation.GuardiansSearchViewModel
import com.example.ui.theme.Hospital_AutomationTheme
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
object AuthGraphRoute

@Serializable
object MainGraphRoute


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hospital_AutomationTheme {
              val viewModel = koinViewModel<ChildrenSearchViewModel>()
                ChildrenSearchScreen(
                    viewModel = viewModel,
                    onAction = viewModel::onAction
                )
            }


        }
    }
}