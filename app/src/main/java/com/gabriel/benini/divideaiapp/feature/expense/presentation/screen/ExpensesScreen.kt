package com.gabriel.benini.divideaiapp.feature.expense.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ExpensesScreen(
    groupId: Long,
    onNavigateToEditExpense: (Long) -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text("Expenses Screen")
        Text("Group ID - $groupId")

        Button(
            onClick = { onNavigateToEditExpense(42) }
        ) {
            Text("Editar Despesa")
        }
    }

}