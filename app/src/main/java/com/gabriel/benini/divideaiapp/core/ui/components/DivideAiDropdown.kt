package com.gabriel.benini.divideaiapp.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gabriel.benini.divideaiapp.core.ui.theme.DivideAiAppTheme
import com.gabriel.benini.divideaiapp.core.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DivideAiDropdown(
    options: List<T>,
    selectedOption: T?,
    onOptionSelected: (T) -> Unit,
    optionLabel: (T) -> String,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    supportingText: String? = null,
    placeholder: String? = null
) {
    var expanded by remember(enabled) { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded && enabled,
        onExpandedChange = { newExpanded ->
            if (enabled) {
                expanded = newExpanded
            }
        },
        modifier = modifier
    ) {

        DivideAiTextField(
            value = selectedOption?.let(optionLabel).orEmpty(),
            onValueChange = {},
            readOnly = true,
            label = label,
            enabled = enabled,
            isError = isError,
            supportingText = supportingText,
            placeholder = placeholder,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(
                    type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                    enabled = enabled
                ),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded && enabled
                )
            }
        )

        ExposedDropdownMenu(
            expanded = expanded && enabled,
            onDismissRequest = { expanded = false }
        ) {

            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(text = optionLabel(option))
                    },
                    onClick = {
                        expanded = false
                        onOptionSelected(option)
                    }
                )
            }
        }
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun DivideAiDropdownPreview() {
    DivideAiAppTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.large),
                verticalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.medium
                )
            ) {
                var selectedParticipant by remember {
                    mutableStateOf<String?>(null)
                }

                val participants = listOf(
                    "Gabriel",
                    "Pedro",
                    "Mariana",
                    "Lucas",
                )

                DivideAiDropdown(
                    options = participants,
                    selectedOption = selectedParticipant,
                    onOptionSelected = {
                        selectedParticipant = it
                    },
                    optionLabel = { it },
                    label = "Quem pagou?",
                    placeholder = "Selecionar"
                )

                DivideAiDropdown(
                    options = participants,
                    selectedOption = "Gabriel",
                    onOptionSelected = {},
                    optionLabel = { it },
                    label = "Quem pagou?",
                )

                DivideAiDropdown(
                    options = participants,
                    selectedOption = null,
                    onOptionSelected = {},
                    optionLabel = { it },
                    label = "Quem pagou?",
                    isError = true,
                    supportingText = "Selecione um participante"
                )

                DivideAiDropdown(
                    options = participants,
                    selectedOption = null,
                    onOptionSelected = {},
                    optionLabel = { it },
                    label = "Quem pagou?",
                    placeholder = "Gabriel",
                    enabled = false
                )

            }
        }
    }
}