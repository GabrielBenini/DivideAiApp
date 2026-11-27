package com.gabriel.benini.divideaiapp.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabriel.benini.divideaiapp.core.ui.theme.DivideAiAppTheme
import com.gabriel.benini.divideaiapp.core.ui.theme.spacing

enum class ButtonVariant {
    Primary,
    Secondary,
    Outlined,
    Text
}

@Composable
private fun DivideAiButtonContent(
    text: String,
    isLoading: Boolean,
    leadingIcon: (@Composable () -> Unit)?
) {

    if (isLoading){
        CircularProgressIndicator(
            modifier = Modifier.size(20.dp),
            strokeWidth = 2.dp,
            color = LocalContentColor.current
        )

        Spacer(
            modifier = Modifier.width(MaterialTheme.spacing.small)
        )
    } else if (leadingIcon != null){
        leadingIcon()

        Spacer(
            modifier = Modifier.width(MaterialTheme.spacing.small)
        )
    }

    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge
    )

}

@Composable
fun DivideAiButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: ButtonVariant = ButtonVariant.Primary,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIcon: (@Composable () -> Unit)? = null
) {
    val isEnabled = enabled && !isLoading

    when (variant) {
        ButtonVariant.Primary -> {
            Button(
                onClick = onClick,
                modifier = modifier
                    .heightIn(min = 48.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                enabled = isEnabled
            ) {
                DivideAiButtonContent(
                    text = text,
                    isLoading = isLoading,
                    leadingIcon = leadingIcon
                )
            }
        }

        ButtonVariant.Secondary -> {
            Button(
                onClick = onClick,
                modifier = modifier
                    .heightIn(min = 48.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
                enabled = isEnabled
            ) {
                DivideAiButtonContent(
                    text = text,
                    isLoading = isLoading,
                    leadingIcon = leadingIcon
                )
            }
        }

        ButtonVariant.Outlined -> {
            OutlinedButton(
                onClick = onClick,
                modifier = modifier
                    .heightIn(min = 48.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                enabled = isEnabled
            ) {
                DivideAiButtonContent(
                    text = text,
                    isLoading = isLoading,
                    leadingIcon = leadingIcon
                )
            }
        }

        ButtonVariant.Text -> {
            TextButton(
                onClick = onClick,
                modifier = modifier
                    .heightIn(min = 48.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                enabled = isEnabled
            ) {
                DivideAiButtonContent(
                    text = text,
                    isLoading = isLoading,
                    leadingIcon = leadingIcon
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
private fun DivideAiButtonPreview() {
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
                ButtonVariant.entries.forEach { variant ->
                    DivideAiButton(
                        text = variant.name,
                        onClick = {},
                        modifier = Modifier.fillMaxWidth(),
                        variant = variant
                    )
                }

                DivideAiButton(
                    text = "Disabled",
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false
                )

                DivideAiButton(
                    text = "Criar Grupo",
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                )

                DivideAiButton(
                    text = "Salvando...",
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = true
                )
            }
        }
    }
}
