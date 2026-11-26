package com.gabriel.benini.divideaiapp.feature.group.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun GroupDetailsScreen(
    groupId: Long,
    onNavigateToMembers: (Long) -> Unit,
    onNavigateToExpenses: (Long) -> Unit,
    onNavigateToAddExpenses: (Long) -> Unit,
    onNavigateToSettlement: (Long) -> Unit,
    onNavigateBack: () -> Unit,
){

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text("Group Details Screen")
        Text("Group Id: $groupId")

        Button(
            onClick = { onNavigateToMembers(groupId) }
        ) {
            Text("Membros")
        }

        Button(
            onClick = { onNavigateToExpenses(groupId) }
        ) {
            Text("Despesas")
        }

        Button(
            onClick = { onNavigateToAddExpenses(groupId) }
        ) {
            Text("Adicionar Despesa")
        }

        Button(
            onClick = { onNavigateToSettlement(groupId) }
        ) {
            Text("Fechar Conta")
        }

        Button(
            onClick = { onNavigateBack() }
        ) {
            Text("Voltar")
        }
    }

}