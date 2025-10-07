package com.example.ui_components.components.dialog

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.window.Dialog
import com.example.constants.days_of_week.getDaysOfWeekOptionsList
import com.example.constants.icons.AppIcons
import com.example.model.dialog.DialogOption
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiSelectionsOptionsPickerDialog(
    modifier: Modifier = Modifier,
    onConfirm: (indexes: List<Int>) -> Unit,
    options: List<DialogOption>,
    isVisible: Boolean,
    title: String,
    onDismissRequest: () -> Unit,
    onCancel: () -> Unit,
    onOptionClick: (index: Int, isSelected: Boolean) -> Unit,
    enableConfirmButton: Boolean = true,
    confirmText: String = stringResource(R.string.add),
    cancelText: String = stringResource(R.string.cancel),
    @DrawableRes icon: Int = AppIcons.Outlined.vaccine,
    mainColor: Color = MaterialTheme.colorScheme.primary,
    loadingState: ScreenState = ScreenState.SUCCESS,
    errorMessage: String = stringResource(R.string.network_error),
    errorDescription: String = stringResource(R.string.something_went_wrong),
    retryText: String = stringResource(R.string.retry),
    onRetry:()->Unit={},
    enableRetryButton: Boolean=true,
) {
    if (isVisible) {
        Dialog(
            onDismissRequest = onDismissRequest,
        ) {
            Card(
                modifier = modifier,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                ),
                shape = RoundedCornerShape(MaterialTheme.sizing.small24)
            ) {
                Column(
                    verticalArrangement = Arrangement.Top
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(MaterialTheme.sizing.small12)
                            .background(
                                mainColor
                            )
                    )
                    Spacer(Modifier.height(MaterialTheme.spacing.medium16))
                    if (loadingState != ScreenState.ERROR) {
                        Row(
                            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.large24),
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small12),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(MaterialTheme.spacing.large32),
                                painter = painterResource(icon),
                                contentDescription = null,
                                tint = mainColor
                            )
                            Text(
                                text = title,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    val contentPadding = PaddingValues(
                        start = MaterialTheme.spacing.large24,
                        end = MaterialTheme.spacing.large24,
                        top = MaterialTheme.spacing.medium16,
                        bottom = MaterialTheme.spacing.small8
                    )
                    val contentModifier = Modifier
                        .fillMaxWidth()
                        .padding(contentPadding)
                    when (loadingState) {
                        ScreenState.SUCCESS ->
                            LazyColumn(
                                contentPadding = contentPadding,
                                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall4)
                            ) {
                                items(options.size) { index ->
                                    val optionBackgroundColor =
                                        if (options[index].isSelected)
                                            MaterialTheme.colorScheme.primaryContainer
                                        else Color.Transparent
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(
                                                MaterialTheme.shapes.extraSmall
                                            )
                                            .background(
                                                color = optionBackgroundColor
                                            )
                                            .clickable {
                                                onOptionClick(index, options[index].isSelected)
                                            }
                                            .padding(
                                                vertical = MaterialTheme.spacing.small8,
                                                horizontal = MaterialTheme.spacing.small8,
                                            )
                                    ) {
                                        Text(
                                            options[index].title,
                                            style = MaterialTheme.typography.bodyMedium,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1,
                                            modifier = Modifier.padding(end = MaterialTheme.spacing.medium16)
                                        )
                                        val trailingText = options[index].trailingText
                                        trailingText?.let {
                                            Text(
                                                trailingText,
                                                style = MaterialTheme.typography.bodySmall
                                            )
                                        }
                                    }

                                }
                            }

                        ScreenState.LOADING -> Box(
                            contentAlignment = Alignment.Center,
                            modifier = contentModifier,
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(MaterialTheme.sizing.circularProgressIndicatorSize24),
                                color = MaterialTheme.colorScheme.primary,
                                strokeWidth = MaterialTheme.sizing.extraSmall2
                            )
                        }

                        ScreenState.ERROR -> Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = contentModifier,
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ill_error),
                                contentDescription = null,
                                modifier = Modifier.size(MaterialTheme.sizing.illustrationImageSize)
                            )
                            Text(
                                text = errorMessage,
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
                            Text(
                                text = errorDescription,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                textAlign = TextAlign.Center
                            )
                        }

                        else -> null

                    }

                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            TextButton(
                                modifier = Modifier
                                    .padding(
                                        top = MaterialTheme.spacing.medium16,
                                        bottom = MaterialTheme.spacing.medium16,
                                    ),
                                onClick = onCancel,
                            ) {
                                Text(
                                    text = cancelText,
                                    style = MaterialTheme.typography.labelLarge,
                                    color = mainColor
                                )
                            }
                            if (loadingState == ScreenState.SUCCESS) {
                                TextButton(
                                    modifier = Modifier
                                        .padding(
                                            end = MaterialTheme.spacing.medium16,
                                            top = MaterialTheme.spacing.medium16,
                                            bottom = MaterialTheme.spacing.medium16,
                                        ),
                                    onClick = {
                                        onConfirm(options.filter { it.isSelected }
                                            .mapIndexed { index, option -> index })
                                    },
                                    enabled = enableConfirmButton,
                                ) {
                                    Text(
                                        text = confirmText,
                                        style = MaterialTheme.typography.labelLarge,
                                    )
                                }
                            }


                            if (loadingState == ScreenState.ERROR) {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.CenterEnd,
                                ) {
                                    TextButton(
                                        modifier = Modifier
                                            .padding(
                                                end = MaterialTheme.spacing.medium16,
                                                top = MaterialTheme.spacing.medium16,
                                                bottom = MaterialTheme.spacing.medium16,
                                            ),
                                        onClick = {
                                            onRetry()
                                        },
                                        enabled = enableRetryButton,
                                    ) {
                                        Text(
                                            text = retryText,
                                            style = MaterialTheme.typography.labelLarge,
                                        )
                                    }
                                }
                            }

                        }

                }
            }
        }
    }
}


@DarkAndLightModePreview
@Composable
fun MultiDaysOfWeekPickerDialogPreview() {
    val selectedDays = remember { mutableListOf<Int>() }

    Hospital_AutomationTheme {
        Surface {
            MultiSelectionsOptionsPickerDialog(
                onConfirm = { selectedDaysIndexes ->
                    selectedDaysIndexes.forEach { index ->
                        selectedDays.add(
                            index
                        )
                    }
                },
                options = getDaysOfWeekOptionsList(),
                isVisible = true,
                title = "Choose Vaccines",
                onDismissRequest = {},
                onCancel = {},
                onOptionClick = { _, _ -> },
            )
        }
    }
}
