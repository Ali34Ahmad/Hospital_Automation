package com.example.ui_components.components.topbars

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_components.R
import com.example.ui_components.icons.HospitalAutomationIcons
import com.example.ui_components.theme.Hospital_AutomationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospitalAutomationTopBarWithSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onTrailingIconClick: () -> Unit,
    @StringRes placeholderText: Int,
    onNavigationIconCLick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes navigationIcon: Int = HospitalAutomationIcons.arrowBack,
    @DrawableRes trailingIcon: Int? = HospitalAutomationIcons.close,
) {
    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                IconButton(
                    modifier = Modifier.fillMaxHeight() ,
                    onClick = onNavigationIconCLick
                ) {
                    Icon(
                        painterResource(navigationIcon),
                        contentDescription = null
                    )
                }
                HospitalAutomationSearchBar(
                    modifier = Modifier.fillMaxWidth(1f)
                    ,
                    query = query,
                    onQueryChange = onQueryChange,
                    onTrailingIconClick = onTrailingIconClick,
                    placeholderText = placeholderText,
                    trailingIcon = trailingIcon,
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xffEFF1FC)
        ),
    )
}

@Preview(showSystemUi = true)
@Composable
fun HospitalAutomationTopBarWithSearchBarPreview() {
    Hospital_AutomationTheme {
        var query by remember{
            mutableStateOf("")
        }
        HospitalAutomationTopBarWithSearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = query,
            onQueryChange = {
                query = it
            },
            onTrailingIconClick = {
                query = ""
            },
            placeholderText = R.string.search_for_guardians,
            onNavigationIconCLick = {  },
        )
    }
}