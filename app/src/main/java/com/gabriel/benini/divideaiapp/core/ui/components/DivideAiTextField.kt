package com.gabriel.benini.divideaiapp.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.gabriel.benini.divideaiapp.core.ui.theme.DivideAiAppTheme
import com.gabriel.benini.divideaiapp.core.ui.theme.spacing

@Composable
fun DivideAiTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    isError: Boolean = false,
    supportingText: String? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        isError = isError,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        label = {
            Text(text = label)
        },
        placeholder = if (placeholder != null) {
            { Text(text = placeholder) }
        } else {
            null
        },
        supportingText = if (supportingText != null) {
            { Text(text = supportingText) }
        } else {
            null
        },
        shape = MaterialTheme.shapes.medium,
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.primary,
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorLabelColor = MaterialTheme.colorScheme.error,
            errorSupportingTextColor = MaterialTheme.colorScheme.error,
            errorCursorColor = MaterialTheme.colorScheme.error,
        )
    )
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
private fun DivideAiTextFieldPreview() {
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

                // Campo vazio
                DivideAiTextField(
                    value = "",
                    onValueChange = {},
                    label = "Seu nome",
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = "Digite seu nome"
                )

                // Campo preenchido
                DivideAiTextField(
                    value = "Viagem à praia",
                    onValueChange = {},
                    label = "Nome do grupo",
                    modifier = Modifier.fillMaxWidth()
                )

                // Campo com ícone
                DivideAiTextField(
                    value = "",
                    onValueChange = {},
                    label = "Seu nome",
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    }
                )

                // Campo com erro
                DivideAiTextField(
                    value = "",
                    onValueChange = {},
                    label = "Nome do grupo",
                    modifier = Modifier.fillMaxWidth(),
                    isError = true,
                    supportingText = "Campo obrigatório"
                )

                // Campo desabilitado
                DivideAiTextField(
                    value = "Gabriel",
                    onValueChange = {},
                    label = "Seu nome",
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false
                )

                // Campo somente leitura
                DivideAiTextField(
                    value = "Praia com a galera",
                    onValueChange = {},
                    label = "Grupo",
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true
                )

                // Campo monetário
                DivideAiTextField(
                    value = "150,00",
                    onValueChange = {},
                    label = "Valor da despesa",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
            }
        }
    }
}