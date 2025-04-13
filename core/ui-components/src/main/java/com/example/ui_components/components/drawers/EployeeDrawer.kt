package com.example.ui_components.components.drawers

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_components.model.DrawerButton
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui_components.R
import com.example.ui_components.icons.HospitalAutomationIcons

@Composable
fun EmployeeDrawer(
    state : DrawerState,
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    buttons: List<DrawerButton>,
    selectedIndex : Int,
    onItemSelected: (index : Int) -> Unit,
    screen : @Composable () -> Unit,
) {
    ModalNavigationDrawer(
        drawerState = state,
        modifier = modifier,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.surfaceContainerLow
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        modifier = Modifier.padding(18.dp),
                        text = stringResource(title),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    buttons.forEachIndexed {currentIndex , item ->
                        NavigationDrawerItem(
                            modifier = Modifier.padding(bottom = 8.dp),
                            selected = currentIndex == selectedIndex,
                            onClick = {
                                item.onClick
                                onItemSelected(currentIndex)
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(item.image),
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(
                                    stringResource(item.text),
                                    style = MaterialTheme.typography.labelLarge,
                                )
                            },
                            colors =  NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedTextColor =   MaterialTheme.colorScheme.onSurfaceVariant ,
                                unselectedBadgeColor =   MaterialTheme.colorScheme.onSurfaceVariant ,
                                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                selectedBadgeColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            ),
                            badge = {
                                item.badgeCount?.let{
                                    Text(
                                        it.toString(),
                                        style = MaterialTheme.typography.labelLarge,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                }
                            }
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }
            }
        },
        content = screen
    )
}

@Preview(showBackground = true)
@Composable
fun EmployeeDrawerPreview() {
    Hospital_AutomationTheme {
        val buttons = listOf<DrawerButton>(
            DrawerButton(
                text = R.string.profile,
                image = HospitalAutomationIcons.accountCircle,
                badgeCount = 24,
                onClick = {}
            ),
            DrawerButton(
                text = R.string.requests,
                image = HospitalAutomationIcons.send,
                onClick = {}
            ),
            DrawerButton(
                text = R.string.add_children,
                image = HospitalAutomationIcons.notification,
                onClick = {}
            ),
        )
        var selectedIndex by remember { mutableIntStateOf(1) }

        EmployeeDrawer(
            state = remember { DrawerState(initialValue = DrawerValue.Open) },
            title = R.string.mail,
            buttons = buttons,
            selectedIndex = selectedIndex,
            onItemSelected = {
                selectedIndex = it
            },
            screen = {},
        )
    }
}