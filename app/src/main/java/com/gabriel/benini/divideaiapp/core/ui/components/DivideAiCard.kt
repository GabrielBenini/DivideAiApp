package com.gabriel.benini.divideaiapp.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabriel.benini.divideaiapp.core.ui.theme.DivideAiAppTheme
import com.gabriel.benini.divideaiapp.core.ui.theme.spacing

enum class CardVariant {
    Elevated,
    Filled,
    Outlined
}

@Composable
fun DivideAiCard(
    modifier: Modifier = Modifier,
    variant: CardVariant = CardVariant.Filled,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    val shape = MaterialTheme.shapes.large

    when (variant) {

        CardVariant.Elevated -> {

            val colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )

            val elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 2.dp
            )

            if (onClick != null) {
                ElevatedCard(
                    onClick = onClick,
                    enabled = enabled,
                    modifier = modifier,
                    shape = shape,
                    colors = colors,
                    elevation = elevation,
                    content = content
                )
            } else {
                ElevatedCard(
                    modifier = modifier,
                    shape = shape,
                    colors = colors,
                    elevation = elevation,
                    content = content
                )
            }
        }

        CardVariant.Filled -> {

            val colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                contentColor = MaterialTheme.colorScheme.onSurface
            )

            val elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )

            if (onClick != null) {
                Card(
                    onClick = onClick,
                    enabled = enabled,
                    modifier = modifier,
                    shape = shape,
                    colors = colors,
                    elevation = elevation,
                    content = content
                )
            } else {
                Card(
                    modifier = modifier,
                    shape = shape,
                    colors = colors,
                    elevation = elevation,
                    content = content
                )
            }
        }

        CardVariant.Outlined -> {

            val colors = CardDefaults.outlinedCardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )

            val elevation = CardDefaults.outlinedCardElevation(
                defaultElevation = 0.dp
            )

            val border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )

            if (onClick != null) {
                OutlinedCard(
                    onClick = onClick,
                    enabled = enabled,
                    modifier = modifier,
                    shape = shape,
                    colors = colors,
                    elevation = elevation,
                    border = border,
                    content = content
                )
            } else {
                OutlinedCard(
                    modifier = modifier,
                    shape = shape,
                    colors = colors,
                    elevation = elevation,
                    border = border,
                    content = content
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
private fun DivideAiCardPreview() {
    DivideAiAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.large),
                verticalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.medium
                )
            ) {

                CardVariant.entries.forEach { variant ->

                    DivideAiCard(
                        modifier = Modifier.fillMaxWidth(),
                        variant = variant
                    ) {
                        DivideAiCardPreviewContent(
                            title = "${variant.name} Card",
                            description = "Card informativo"
                        )
                    }
                }

                var clickCount by remember {
                    mutableIntStateOf(0)
                }

                DivideAiCard(
                    modifier = Modifier.fillMaxWidth(),
                    variant = CardVariant.Filled,
                    onClick = { clickCount++ }
                ) {
                    DivideAiCardPreviewContent(
                        title = "Card clicável",
                        description = "Cliques: $clickCount"
                    )
                }

                DivideAiCard(
                    modifier = Modifier.fillMaxWidth(),
                    variant = CardVariant.Filled,
                    onClick = { clickCount++ },
                    enabled = false
                ) {
                    DivideAiCardPreviewContent(
                        title = "Card desabilitado",
                        description = "Interação indisponível"
                    )
                }
            }
        }
    }
}

@Composable
private fun DivideAiCardPreviewContent(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.large),
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.extraSmall
        )
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}