package com.gabriel.benini.divideaiapp.feature.expense.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gabriel.benini.divideaiapp.feature.expense.presentation.viewmodel.ExpenseUiState
import com.gabriel.benini.divideaiapp.feature.expense.presentation.viewmodel.ExpenseViewModel

@Composable
fun ExpenseScreen(
    viewModel: ExpenseViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getExpenseByGroupId(3)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        when (val state = uiState) {

            is ExpenseUiState.Loading -> {
                CircularProgressIndicator()
            }

            is ExpenseUiState.Success -> {
                Text(text = "ID: ${state.expense.id}")
                Text(text = "Descrição: ${state.expense.description}")
                Text(text = "Valor em centavos: ${state.expense.amountInCents}")
                Text(text = "Pago por: ${state.expense.paidByUserName}")
                Text(text = "Grupo ID: ${state.expense.groupId}")
                Text(text = "Criado em: ${state.expense.createdAt}")
            }

            is ExpenseUiState.ExpenseListSuccess -> {

                if (state.expenses.isEmpty()){
                    Text("Nenhuma Despesa Encontrada")
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        items(
                            items = state.expenses,
                            key = { expenses -> expenses.id }
                        ) { expense ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ){
                                Text("Id: ${expense.id}")
                                Text("groupId: ${expense.groupId}")
                                Text("Description: ${expense.description}")
                                Text("Created At: ${expense.createdAt}")
                                Text("Amount in Cents: ${expense.amountInCents}")
                                Text("Paid By: ${expense.paidByUserName}")
                            }
                        }
                    }
                }
            }

            is ExpenseUiState.Error -> {
                Text(text = state.message)
            }
        }
    }
}