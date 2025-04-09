package com.example.hospital_automation.core.components.card

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.hospital_automation.R
import com.example.hospital_automation.ui.helper.DarkAndLightModePreview
import com.example.hospital_automation.ui.theme.Hospital_AutomationTheme
import com.example.hospital_automation.ui.theme.spacing

@Composable
fun FileUploadingCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    IllustrationCard(
        modifier = modifier,
        title = title,
        titleColor = MaterialTheme.colorScheme.onBackground,
        description = description,
        actionButtonsSection = {
            //FileComponent()
            Spacer(modifier=Modifier.height(MaterialTheme.spacing.large24))
            //ActionButtonsComponent()
        }
    )
}

@DarkAndLightModePreview
@Composable
fun FileUploadingCardPreview(){
    Hospital_AutomationTheme{
        Surface{
            FileUploadingCard(
                modifier=Modifier.fillMaxWidth(),
                title = stringResource(R.string.child_birth_certification_is_required),
                description = stringResource(R.string.upload_file_description)
            )
        }
    }
}