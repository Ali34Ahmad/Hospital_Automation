package com.example.ui_components.components.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.constants.icons.AppIcons
import com.example.model.tab.TabItemWithIcon
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.util.UiText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabLayoutWithIcons(
    tabs: List<TabItemWithIcon>,
    selectedTabIndex: Int,
    onTabClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    PrimaryTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        tabs.forEachIndexed { index, tabItem ->
            LeadingIconTab(
                selected = index == selectedTabIndex, // Determine if this tab is selected
                onClick = { onTabClick(index) },
                text = {
                    Text(text = tabItem.title.asString())
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(tabItem.iconRes),
                        contentDescription = tabItem.title.asString()
                    )
                },
                enabled = true,
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TabLayoutWithIconsPreview() {
    Hospital_AutomationTheme {
        Surface {
            TabLayoutWithIcons(
                tabs = listOf(
                    TabItemWithIcon(
                        title = UiText.StringResource(R.string.show_all),
                        iconRes = AppIcons.Outlined.list
                    ),
                    TabItemWithIcon(
                        title = UiText.StringResource(R.string.new_),
                        iconRes = AppIcons.Outlined.add
                    ),
                ),
                selectedTabIndex = 0,
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.background
                    )
                    .padding(MaterialTheme.spacing.medium16),
                onTabClick = {},
            )
        }
    }
}