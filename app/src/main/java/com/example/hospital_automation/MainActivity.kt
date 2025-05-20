package com.example.hospital_automation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.add_child_screen.AddChildScreen
import com.example.add_child_screen.AddChildViewModel
import com.example.child_profile.ChildProfileScreen
import com.example.child_profile.ChildProfileViewModel
import com.example.children_search.ChildrenSearchScreen
import com.example.children_search.ChildrenSearchViewModel
import com.example.hospital_automation.ui.theme.Hospital_AutomationTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hospital_AutomationTheme {
                val viewModel = koinViewModel<AddChildViewModel>()
                AddChildScreen(
                    viewModel = viewModel,
                    onAction = viewModel::onAction
                )
            }
        }
    }
}
