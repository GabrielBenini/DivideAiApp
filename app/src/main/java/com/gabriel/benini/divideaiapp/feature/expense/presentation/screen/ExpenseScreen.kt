package com.gabriel.benini.divideaiapp.feature.expense.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gabriel.benini.divideaiapp.feature.expense.presentation.viewmodel.ExpenseContract
import com.gabriel.benini.divideaiapp.feature.expense.presentation.viewmodel.ExpenseViewModel

@Composable
fun ExpenseScreen(
    viewModel: ExpenseViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is ExpenseContract.Effect.ShowMessage -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(
            ExpenseContract.Event.LoadExpensesByGroupId(3L)
        )
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                uiState.errorMessage?.let { message ->
                    item {
                        Text(text = message)
                    }
                }

                uiState.summary?.let { summary ->
                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text("Resumo das despesas")
                            Text("Group ID: ${summary.groupId}")
                            Text("Total: ${summary.totalAmountInCents}")
                            Text("Expense Count: ${summary.expenseCount}")

                            HorizontalDivider()
                        }
                    }
                }

                uiState.selectedExpense?.let { expense ->
                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text("Despesa selecionada")
                            Text("ID: ${expense.id}")
                            Text("Descrição: ${expense.description}")
                            Text("Valor: ${expense.amountInCents}")
                            Text("Pago por: ${expense.paidByUserName}")
                            Text("Group ID: ${expense.groupId}")
                            Text("Criado em: ${expense.createdAt}")

                            HorizontalDivider()
                        }
                    }
                }

                uiState.expensesGroupId?.let { groupId ->

                    item {
                        Text("Despesas do grupo $groupId")
                    }

                    if (
                        uiState.expenses.isEmpty() &&
                        !uiState.isLoading
                    ) {
                        item {
                            Text("Nenhuma despesa encontrada")
                        }
                    }

                    items(
                        items = uiState.expenses,
                        key = { expense -> expense.id }
                    ) { expense ->

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text("ID: ${expense.id}")
                            Text("Group ID: ${expense.groupId}")
                            Text("Descrição: ${expense.description}")
                            Text("Valor: ${expense.amountInCents}")
                            Text("Pago por: ${expense.paidByUserName}")
                            Text("Criado em: ${expense.createdAt}")

                            HorizontalDivider()
                        }
                    }
                }
            }

            if (uiState.isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}