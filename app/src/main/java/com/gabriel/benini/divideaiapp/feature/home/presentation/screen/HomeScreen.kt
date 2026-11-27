package com.gabriel.benini.divideaiapp.feature.home.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gabriel.benini.divideaiapp.core.ui.theme.financialColors

@Composable
fun HomeScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToCreateGroup: () -> Unit,
    onNavigateToGroupDetails: (Long) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {



        Text("Home Screen")

        Button(
            onClick = onNavigateToProfile
        ) {
            Text("Perfil")
        }

        Button(
            onClick = onNavigateToCreateGroup
        ) {
            Text("Criar Grupo")
        }

        Button(
            onClick = { onNavigateToGroupDetails(3) }
        ) {
            Text("Praia com a galera")
        }

        Text(
            text = "+ R$ 150,00",
            color = MaterialTheme.financialColors.positive
        )

        Text(
            text = "- R$ 80,00",
            color = MaterialTheme.financialColors.negative
        )

        Text(
            text = "R$ 0,00",
            color = MaterialTheme.financialColors.neutral
        )
    }
}