package com.example.ui_components.components.flow_row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.model.doctor.appointment.AppointmentTypeData
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.components.tag.OutlinedTagItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsFlowRow(
    tagsList: List<String>,
    onTagClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
        modifier = modifier,
    ) {
        tagsList.forEachIndexed { index, tag ->
            OutlinedTagItem(
                text = tag,
                onClick = { onTagClick(index) }
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TagsFlowRowPreview(){
    Hospital_AutomationTheme{
        Surface{
            TagsFlowRow(
                tagsList = emptyList(),
                onTagClick = {},
                modifier = Modifier.fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16),
            )
        }
    }
}